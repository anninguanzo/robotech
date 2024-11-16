package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Robot extends LinearOpMode {

    Movement driveTrain;
    Lift lift;
    SpecimenClaw specimenClaw;
    @Override
    public void runOpMode() throws InterruptedException{
        driveTrain = new Movement();
        lift = new Lift();
        specimenClaw = new SpecimenClaw();
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            driveTrain.motorDriveTrain();
            if(!lift.touchSensor.isPressed()) lift.LiftFunc();
            specimenClaw.Claw();
        }
    }
}
