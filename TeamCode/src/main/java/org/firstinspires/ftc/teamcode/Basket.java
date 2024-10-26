package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Basket extends LinearOpMode {
    DcMotor RFMotor; // Right Front motor
    DcMotor LFMotor; // Left Front motor
    DcMotor RBMotor; // Right Back motor
    DcMotor LBMotor; // Left Back motor
    @Override
    public void runOpMode() throws InterruptedException {
        LFMotor = hardwareMap.get(DcMotor.class, "motor");
        RBMotor = hardwareMap.get(DcMotor.class, "motor1");
        RFMotor = hardwareMap.get(DcMotor.class, "motor2");
        LBMotor = hardwareMap.get(DcMotor.class, "motor3");
        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            LFMotor.setPower(0.25);
            RFMotor.setPower(0.25);
            LBMotor.setPower(0.25);
            RBMotor.setPower(0.25);
            Thread.sleep(500);
            LFMotor.setPower(0);
            RFMotor.setPower(0);
            LBMotor.setPower(0);
            RBMotor.setPower(0);
        }
    }
}
