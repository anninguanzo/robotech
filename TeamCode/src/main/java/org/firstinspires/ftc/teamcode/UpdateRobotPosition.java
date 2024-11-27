package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class UpdateRobotPosition extends LinearOpMode {
    DcMotor RFMotor = null; // Right Front motor
    DcMotor LFMotor = null; // Left Front motor
    DcMotor RBMotor = null; // Right Back motor
    DcMotor LBMotor = null; // Left Back motor
    DcMotor leftEncoder, rightEncoder, middleEncoder;

    static final double TICKS_PER_REV = 8192;
    static final double WHEEL_DIAMETER = 100/25.4;
    static final double GEAR_RATIO = 1;
    static final double TICKS_PER_INCH = WHEEL_DIAMETER * Math.PI * GEAR_RATIO/TICKS_PER_REV;
    GlobalCoordinateSystem positionUpdate;
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
        waitForStart();
        positionUpdate = new GlobalCoordinateSystem(leftEncoder, rightEncoder, middleEncoder, TICKS_PER_INCH, 100);
        Thread position = new Thread(positionUpdate);
        position.start();
        while(opModeIsActive()){
            double x = gamepad1.left_stick_y;
            double y = gamepad1.left_stick_x;
            double turn = -gamepad1.right_stick_x;
            double theta = Math.atan2(y, x);
            double power = Math.hypot(x, y);
            double sin = Math.sin(theta - Math.PI/4), cos = Math.cos(theta - Math.PI/4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));
            LFMotor.setPower((power * cos/max + turn));
            RFMotor.setPower((power * sin/max - turn));
            LBMotor.setPower((power * sin/max + turn));
            RBMotor.setPower((power * cos/max - turn));
            if((power + Math.abs(turn)) > 1){
                LFMotor.setPower(LFMotor.getPower() / (power+turn));
                RFMotor.setPower(RFMotor.getPower() / (power+turn));
                RBMotor.setPower(RBMotor.getPower() / (power+turn));
                LBMotor.setPower(LBMotor.getPower() / (power+turn));
            }
            telemetry.addData("x Position", positionUpdate.returnXCoordinate() / TICKS_PER_INCH);
            telemetry.addData("y position", positionUpdate.returnYCoordinate() / TICKS_PER_INCH);
            telemetry.addData("Orientation(Degrees)", positionUpdate.returnOrientation());
            telemetry.update();
        }
        positionUpdate.stop();
    }
    void resetOdometryEncoders(){
        leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        middleEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        middleEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}