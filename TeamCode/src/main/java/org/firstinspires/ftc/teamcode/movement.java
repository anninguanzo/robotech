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
    CRServo rightWrist;
    CRServo leftWrist;
    Servo rightClaw, leftClaw;
//    DigitalChannel touchSensor;

    public void motorDriveTrain(){
        double x = gamepad1.left_stick_y;
        double y = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;
        double theta = Math.atan2(y, x);
        double power = Math.hypot(x, y);
        double sin = Math.sin(theta - Math.PI/4), cos = Math.cos(theta - Math.PI/4);
        double max = Math.max(Math.abs(sin), Math.abs(cos));
        LFMotor.setPower((power * cos/max + turn) * .6);
        RFMotor.setPower((power * sin/max - turn) * .6);
        LBMotor.setPower((power * sin/max + turn) * .6);
        RBMotor.setPower((power * cos/max - turn) * .6);
        if((power + Math.abs(turn)) > 1){
            LFMotor.setPower(LFMotor.getPower() / (power+turn));
            RFMotor.setPower(RFMotor.getPower() / (power+turn));
            RBMotor.setPower(RBMotor.getPower() / (power+turn));
            LBMotor.setPower(LBMotor.getPower() / (power+turn));
        }
        if(gamepad1.right_bumper){
            LFMotor.setPower((power * cos/max + turn) * .9);
            RFMotor.setPower((power * sin/max - turn) * .9);
            LBMotor.setPower((power * sin/max + turn) * .9);
            RBMotor.setPower((power * cos/max - turn) * .9);
        }
        telemetry.addData("LFMotor Power", LFMotor.getPower());
        telemetry.addData("RFMotor Power", RFMotor.getPower());
        telemetry.addData("LBMotor Power", LBMotor.getPower());
        telemetry.addData("RBMotor Power", RBMotor.getPower());

    }



    public void LiftFunc(){
        double power = gamepad2.right_stick_y;
//        double liftFeed = 0.5;
        motor.setPower(power);
        motor1.setPower(-power);

//        if(power<= 0.2) {
//            motor.setPower(liftFeed);
//            motor1.setPower(-liftFeed);
//        }
//        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


//        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void Claw(){
        if(gamepad2.right_bumper){
            //Unsure of value
            leftClaw.setPosition(1);
            rightClaw.setPosition(0);
        }
        if(gamepad2.left_bumper){
            //Unsure of value
            leftClaw.setPosition(0);
            rightClaw.setPosition(1);
        }
    }
    public void Wrist(){
        double WristPower = gamepad2.right_stick_y;
        double feed;
        feed = 0.2;
        if(WristPower<=0.1){
            leftWrist.setPower(feed);
            rightWrist.setPower(feed);
        }
        leftWrist.setPower(WristPower);
        rightWrist.setPower(WristPower);
    }

    @Override
    public void runOpMode(){
        LBMotor = hardwareMap.get(DcMotor.class, "LBMotor"); //Motor
        RBMotor = hardwareMap.get(DcMotor.class, "RBMotor"); //Motor1
        RFMotor = hardwareMap.get(DcMotor.class, "RFMotor"); //Motor2
        LFMotor = hardwareMap.get(DcMotor.class, "LFMotor"); //Motor3

        LFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LBMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");

        leftWrist = hardwareMap.get(CRServo.class, "leftWrist");
        rightWrist = hardwareMap.get(CRServo.class, "rightWrist");

        motor = hardwareMap.get(DcMotor.class, "lift1");
        motor1 = hardwareMap.get(DcMotor.class, "lift2");
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            motorDriveTrain();
            Wrist();
            LiftFunc();
            Claw();
            telemetry.addData("Something", "Initialized");
            telemetry.update();
        }
    }
}