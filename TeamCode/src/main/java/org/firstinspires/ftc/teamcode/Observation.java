package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Observation extends LinearOpMode {
    DcMotor LFMotor;
    DcMotor RFMotor;
    DcMotor LBMotor;
    DcMotor RBMotor;
    @Override
    public void runOpMode(){
        LBMotor = hardwareMap.get(DcMotor.class, "LBMotor"); //Motor
        RBMotor = hardwareMap.get(DcMotor.class, "RBMotor"); //Motor1
        RFMotor = hardwareMap.get(DcMotor.class, "RFMotor"); //Motor2
        LFMotor = hardwareMap.get(DcMotor.class, "LFMotor"); //Motor3

        LFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        LBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            LFMotor.setPower(1);
            RFMotor.setPower(-1);
            LBMotor.setPower(-1);
            RBMotor.setPower(1);
        }
    }
}
