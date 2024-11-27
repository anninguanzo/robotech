package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.ReadWriteFile;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;

public class Calibration extends LinearOpMode {
    DcMotor RFMotor = null; // Right Front motor
    DcMotor LFMotor = null; // Left Front motor
    DcMotor RBMotor = null; // Right Back motor
    DcMotor LBMotor = null; // Left Back motor
    DcMotor leftEncoder, rightEncoder, middleEncoder;
    BNO055IMU imu;
    ElapsedTime timer = new ElapsedTime();
    static final double calibrationSpeed = .5;
    static final double TICKS_PER_REV = 8192;
    static final double WHEEL_DIAMETER = 100/25.4;
    static final double GEAR_RATIO = 1;
    static final double TICKS_PER_INCH = WHEEL_DIAMETER * Math.PI * GEAR_RATIO/TICKS_PER_REV;
    File sideWheelsSeparationFile = AppUtil.getInstance().getSettingsFile("sideWheelsSeparationFile");
    File middleTickOffsetFile = AppUtil.getInstance().getSettingsFile("middleTickOffsetFile");


    @Override
    public void runOpMode(){
        LBMotor = hardwareMap.get(DcMotor.class, "motor");
        RBMotor = hardwareMap.get(DcMotor.class, "motor1");
        RFMotor = hardwareMap.get(DcMotor.class, "motor2");
        LFMotor = hardwareMap.get(DcMotor.class, "motor3");
        leftEncoder = hardwareMap.get(DcMotor.class, "leftEncoder");
        rightEncoder = hardwareMap.get(DcMotor.class, "rightEncoder");
        middleEncoder = hardwareMap.get(DcMotor.class, "middleEncoder");
        RFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        resetOdometryEncoders();
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        telemetry.addData("Status:", "Ready!");
        telemetry.update();
        waitForStart();
        while(imu.getAngularOrientation().firstAngle < 90 && opModeIsActive()){
            RFMotor.setPower(-calibrationSpeed);
            LFMotor.setPower(-calibrationSpeed);
            RBMotor.setPower(calibrationSpeed);
            LBMotor.setPower(calibrationSpeed);
            if(imu.getAngularOrientation().firstAngle < 60){
                RFMotor.setPower(-calibrationSpeed);
                LFMotor.setPower(-calibrationSpeed);
                RBMotor.setPower(calibrationSpeed);
                LBMotor.setPower(calibrationSpeed);
            }else{
                RFMotor.setPower(-calibrationSpeed/2);
                LFMotor.setPower(-calibrationSpeed/2);
                RBMotor.setPower(calibrationSpeed/2);
                LBMotor.setPower(calibrationSpeed/2);
            }
        }
        RFMotor.setPower(0);
        LFMotor.setPower(0);
        RBMotor.setPower(0);
        LBMotor.setPower(0);
        timer.reset();
        while(timer.seconds() < 1 && opModeIsActive()){
            telemetry.addData("Status:", "Waiting");
            telemetry.update();
        }
        double angle = imu.getAngularOrientation().firstAngle;
        double encoderDifference = Math.abs(Math.abs(leftEncoder.getCurrentPosition()) - Math.abs(rightEncoder.getCurrentPosition()));
        double sideEncoderTickOffset = encoderDifference / angle;
        double sideWheelSeparation = (180 * sideEncoderTickOffset) / (TICKS_PER_INCH * Math.PI);
        double middleTickOffSet = middleEncoder.getCurrentPosition() /  Math.toRadians(imu.getAngularOrientation().firstAngle);
        ReadWriteFile.writeFile(sideWheelsSeparationFile, String.valueOf(sideWheelSeparation));
        ReadWriteFile.writeFile(middleTickOffsetFile, String.valueOf(middleTickOffSet));
    }
    void resetOdometryEncoders(){
        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middle Encoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        middleEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}