package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Movement1")
public class movement extends LinearOpMode {

    DcMotor motor;
    DcMotor motor1;
    DcMotor RFMotor; // Right Front motor
    DcMotor LFMotor; // Left Front motor
    DcMotor RBMotor; // Right Back motor
    DcMotor LBMotor; // Left Back motor
    CRServo Roller;
    Servo RightIntake;
    Servo LeftIntake;
//    DigitalChannel touchSensor;

    public void motorDriveTrain(){
        double x = gamepad1.left_stick_y;
        double y = gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;
        double theta = Math.atan2(y, x);
        double power = Math.hypot(x, y);
        double sin = Math.sin(theta - Math.PI/4), cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        LFMotor.setPower((power * cos/max + turn)/2);
        RFMotor.setPower((power * sin/max - turn)/2);
        LBMotor.setPower((power * sin/max + turn)/2);
        RBMotor.setPower((power * cos/max - turn)/2);
        if((power + Math.abs(turn)) > 1){
            LFMotor.setPower(LFMotor.getPower() / (power+turn));
            RFMotor.setPower(RFMotor.getPower() / (power+turn));
            RBMotor.setPower(RBMotor.getPower() / (power+turn));
            LBMotor.setPower(LBMotor.getPower() / (power+turn));
        }
        if(gamepad1.b){
            LFMotor.setPower((power * cos/max + turn));
            RFMotor.setPower((power * sin/max - turn));
            LBMotor.setPower((power * sin/max + turn));
            RBMotor.setPower((power * cos/max - turn));
        }
        telemetry.addData("LFMotor Power", LFMotor.getPower());
        telemetry.addData("RFMotor Power", RFMotor.getPower());
        telemetry.addData("LBMotor Power", LBMotor.getPower());
        telemetry.addData("RBMotor Power", RBMotor.getPower());

    }



    public void LiftFunc(){
        double power = gamepad2.right_stick_y;
        motor.setPower(power);
        motor1.setPower(-power);
//        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if (power<=0.25){
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }

//        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void Intake(){
        if(gamepad2.right_bumper){
            Roller.setPower(1);
        }
        else if(gamepad2.left_bumper){
            Roller.setPower(-1);
        }else{
            Roller.setPower(0);
        }
    }
    public void IntakeArm(){
        if(gamepad1.right_bumper){
            LeftIntake.setPosition(0.75);
            RightIntake.setPosition(0.75);
            telemetry.addData("Status", "Yes");

        }
        else if(gamepad1.left_bumper){
            LeftIntake.setPosition(0);
            RightIntake.setPosition(0);
            telemetry.addData("Status", "Yes1");
        }
    }

    @Override
    public void runOpMode(){
        LBMotor = hardwareMap.get(DcMotor.class, "motor");
        RBMotor = hardwareMap.get(DcMotor.class, "motor1");
        RFMotor = hardwareMap.get(DcMotor.class, "motor2");
        LFMotor = hardwareMap.get(DcMotor.class, "motor3");
        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightIntake = hardwareMap.get(Servo.class, "RightIntake");
        motor = hardwareMap.get(DcMotor.class, "lift1");
        motor1 = hardwareMap.get(DcMotor.class, "lift2");
//        touchSensor = hardwareMap.get(DigitalChannel.class, "TouchSensor");
        Roller = hardwareMap.get(CRServo.class, "CR 1");
        LeftIntake = hardwareMap.get(Servo.class, "LeftIntake");
//        touchSensor.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            motorDriveTrain();
            Intake();
            IntakeArm();
            LiftFunc();


            telemetry.addData("Something", "Initialized");
            telemetry.update();
        }
    }
}