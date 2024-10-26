package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Movement1")
public class Movement extends LinearOpMode {
    DcMotor RFMotor; // Right Front motor
    DcMotor LFMotor; // Left Front motor
    DcMotor RBMotor; // Right Back motor
    DcMotor LBMotor; // Left Back motor
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
    }
    @Override
    public void runOpMode(){
        LBMotor = hardwareMap.get(DcMotor.class, "motor");
        RBMotor = hardwareMap.get(DcMotor.class, "motor1");
        RFMotor = hardwareMap.get(DcMotor.class, "motor2");
        LFMotor = hardwareMap.get(DcMotor.class, "motor3");
        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            motorDriveTrain();
            if(gamepad1.b){
                double x = gamepad1.left_stick_y;
                double y = -gamepad1.left_stick_x;
                double turn = -gamepad1.right_stick_x;
                double theta = Math.atan2(y, x);
                double power = Math.hypot(x, y);
                double sin = Math.sin(theta - Math.PI/4), cos = Math.cos(theta - Math.PI/4);
                double max = Math.max(Math.abs(sin), Math.abs(cos));
                LFMotor.setPower(power * cos/max + turn);
                RFMotor.setPower(power * sin/max - turn);
                LBMotor.setPower(power * sin/max + turn);
                RBMotor.setPower(power * cos/max - turn);
            }

            telemetry.addData("Something", "Initialized");
            telemetry.update();
        }
    }
}
