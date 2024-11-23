package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto Drive By Time", group="MyHardware")
public class AutoDriveByTime extends LinearOpMode {
    MyHardware robot = new MyHardware();
    ElapsedTime runTime = new ElapsedTime();
    @Override
    public void runOpMode(){
        /*
        Summary of Auton code
        1) Hang specimen
        2) Get sample and basket it once because of time
        3) Go to the zone where we always went in the end of TeleOp(Idk what it was called)
         */
        robot.init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        //Specimen Hanging
        robot.LiftUp(1f, 2, telemetry, this);
        robot.SidewaysLeft(.25f, .6f, telemetry, this);
        robot.stopDriving();
        robot.driveStraight(.25f, .6f, telemetry, this);
        robot.stopDriving();
        robot.IntakeArmUp(this, telemetry);
        robot.LiftDown(1f, 2, telemetry, this);
        //Specimen done, getting ready for intake & basket
//        robot.SpecimenClawOpen();
//        robot.driveStraight(-.25f, .5f, telemetry, this);
//        robot.SpecimenClawClose();
//        robot.Lift(-.5f, 1f, telemetry, this);
//        robot.IntakeArmDown(this, telemetry);
//        //Moving towards samples
//        robot.SidewaysRight(.25f, .6f, telemetry, this);
//        robot.driveStraight(.25f, .5f, telemetry, this);
//        robot.RotationRight(.25f, 1f, telemetry, this);
//        robot.IntakeArmDown(this, telemetry);
//        for(int i = 0; i<5; i++){robot.IntakeArmWheelRight();}
//        robot.IntakeArmWheelStop();
//        robot.IntakeArmUp(this, telemetry);
//        robot.driveStraight(.25f, .5f, telemetry, this);
//        robot.RotationRight(.25f, .5f, telemetry, this);
//        robot.driveStraight(.25f, 2f, telemetry, this);
//        robot.Lift(2f, 3f, telemetry, this);
//        robot.IntakeArmDown(this, telemetry);
//        for(int i = 0; i<5; i++){robot.IntakeArmWheelRight();}
//        //Going to ending place
//        robot.driveStraight(-.25f, .8f, telemetry, this);
//        robot.RotationLeft(.25f, .5f, telemetry, this);
//        robot.driveStraight(.25f, .5f, telemetry, this);
        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }
}