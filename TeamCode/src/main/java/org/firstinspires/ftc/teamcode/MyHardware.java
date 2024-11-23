package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous
public class MyHardware {
    DcMotor RFMotor = null; // Right Front motor
    DcMotor LFMotor = null; // Left Front motor
    DcMotor RBMotor = null; // Right Back motor
    DcMotor LBMotor = null; // Left Back motor
    HardwareMap hwMap = null;
    DcMotor liftMotor = null;
    DcMotor liftMotor1 = null;
    ElapsedTime period = new ElapsedTime();
    Servo claw, IntakeA;
    CRServo intakeWheel;
    public MyHardware(){}
    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;
        LBMotor = hwMap.get(DcMotor.class, "motor");
        RBMotor = hwMap.get(DcMotor.class,   "motor1");
        RFMotor = hwMap.get(DcMotor.class, "motor2");
        LFMotor = hwMap.get(DcMotor.class, "motor3");
        liftMotor = hwMap.get(DcMotor.class, "lift1");
        liftMotor1 = hwMap.get(DcMotor.class, "lift2");
        IntakeA = hwMap.get(Servo.class, "inArm");
        claw = hwMap.get(Servo.class, "Claw");
        intakeWheel = hwMap.get(CRServo.class, "CR 1");
        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.stopDriving();
        LFMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RFMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RBMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void stopDriving(){
        LFMotor.setPower(0);
        RFMotor.setPower(0);
        LBMotor.setPower(0);
        RBMotor.setPower(0);
    }
    public void stopLift(){
        liftMotor.setPower(0);
        liftMotor1.setPower(0);
    }
    public void driveStraight(double power, float seconds, Telemetry telemetry, LinearOpMode opMode){
        LFMotor.setPower(-power);
        RFMotor.setPower(power);
        LBMotor.setPower(power);
        RBMotor.setPower(-power);
        ElapsedTime runTime = new ElapsedTime();
        runTime.reset();
        while(opMode.opModeIsActive() && (runTime.seconds() < seconds)){
            telemetry.addData("Path", "driveStraight: %2.5f S Elapsed", runTime.seconds());
            telemetry.update();
        }
    }
    public void SidewaysLeft(double power, float seconds, Telemetry telemetry, LinearOpMode opMode){
        LFMotor.setPower(-power);
        RFMotor.setPower(-power);
        LBMotor.setPower(-power);
        RBMotor.setPower(-power);
        ElapsedTime runTime = new ElapsedTime();
        runTime.reset();
        while(opMode.opModeIsActive() && (runTime.seconds() < seconds)){
            telemetry.addData("Path", "SidewaysLeft: %2.5f S Elapsed", runTime.seconds());
            telemetry.update();
        }
    }
    public void SidewaysRight(double power, float seconds, Telemetry telemetry, LinearOpMode opMode){
        LFMotor.setPower(power);
        RFMotor.setPower(power);
        LBMotor.setPower(power);
        RBMotor.setPower(power);
        ElapsedTime runTime = new ElapsedTime();
        runTime.reset();
        while(opMode.opModeIsActive() && (runTime.seconds() < seconds)){
            telemetry.addData("Path", "SidewaysRight: %2.5f S Elapsed", runTime.seconds());
            telemetry.update();
        }
    }
    public void RotationRight(double power, float seconds, Telemetry telemetry, LinearOpMode opMode){
        LFMotor.setPower(power);
        RFMotor.setPower(-power);
        LBMotor.setPower(power);
        RBMotor.setPower(-power);
        ElapsedTime runTime = new ElapsedTime();
        runTime.reset();
        while(opMode.opModeIsActive() && (runTime.seconds() < seconds)){
            telemetry.addData("Path", "RotationRight: %2.5f S Elapsed", runTime.seconds());
            telemetry.update();
        }
    }
    public void RotationLeft(double power, float seconds, Telemetry telemetry, LinearOpMode opMode){
        LFMotor.setPower(-power);
        RFMotor.setPower(power);
        LBMotor.setPower(-power);
        RBMotor.setPower(power);
        ElapsedTime runTime = new ElapsedTime();
        runTime.reset();
        while(opMode.opModeIsActive() && (runTime.seconds() < seconds)){
            telemetry.addData("Path", "RotationRight: %2.5f S Elapsed", runTime.seconds());
            telemetry.update();
        }
    }
    public void LiftUp(double power, float seconds, Telemetry telemetry, LinearOpMode opMode){
        liftMotor.setPower(-power);
        liftMotor1.setPower(power);
//        +
//
        ElapsedTime runTime = new ElapsedTime();
        runTime.reset();
        while(opMode.opModeIsActive() && (runTime.seconds() < seconds)){
            telemetry.addData("Path", "Lift: %2.5f S Elapsed", runTime.seconds());
            telemetry.update();
        }
    }
    public void LiftDown(double power, float seconds, Telemetry telemetry, LinearOpMode opMode){
        liftMotor.setPower(power);
        liftMotor1.setPower(-power);
//        +
//
        ElapsedTime runTime = new ElapsedTime();
        runTime.reset();
        while(opMode.opModeIsActive() && (runTime.seconds() < seconds)){
            telemetry.addData("Path", "Lift: %2.5f S Elapsed", runTime.seconds());
            telemetry.update();
        }
    }
    public void SpecimenClawOpen(){
        claw.setPosition(0);
    }
    public void SpecimenClawClose(){
        claw.setPosition(1);
    }
    public void IntakeArmDown(LinearOpMode opMode, Telemetry telemetry){
        IntakeA.setPosition(0.5);
        while(opMode.opModeIsActive()){
            telemetry.addData("Status", "Yes");
        }
    }
    public void IntakeArmUp(LinearOpMode opMode, Telemetry telemetry){
        IntakeA.setPosition(0);
        while(opMode.opModeIsActive()){
            telemetry.addData("Status", "Yes");
        }
    }
    public void IntakeArmWheelStop(){
        intakeWheel.setPower(0);
    }
    public void IntakeArmWheelRight(){
        intakeWheel.setPower(1);
    }
    public void IntakeArmWheelLeft(){
        intakeWheel.setPower(-1);
    }
}
/*
Lift = Motor: -.66, Motor1: .66
Sideways(To the left)
all negative
Sideways(To the right)
all positive
rotation(right)
positive, negative, positive, negative
rotation(left)
negative, positive, negative, positive
 */
