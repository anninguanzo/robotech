package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class robot extends LinearOpMode {

    movement driveTrain;
    org.firstinspires.ftc.teamcode.lift lift;
    org.firstinspires.ftc.teamcode.specimenClaw specimenClaw;
    @Override
    public void runOpMode() throws InterruptedException{
        driveTrain = new movement();
        lift = new lift();
        specimenClaw = new specimenClaw();
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            driveTrain.motorDriveTrain();
            if(!lift.touchSensor.isPressed()) lift.LiftFunc();
            specimenClaw.Claw();
        }
    }
}
