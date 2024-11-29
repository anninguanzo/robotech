package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class SpecimenAutonTeamRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-12, 30, Math.toRadians(90)));
        DcMotor liftMotor, liftMotor1;
        liftMotor = hardwareMap.get(DcMotor.class, "lift1");
        liftMotor1 = hardwareMap.get(DcMotor.class, "lift2");
        waitForStart();
        /*
        ATTENTION WHO EVER IS WATCHING THIS CODE
        WHEN IT SAYS .lineToX(randomVal) OR .lineToY(randomVal)
        IT IS MOVING THE ROBOT'S CURRENT X OR Y POSITION
        TO THE randomVal GIVEN(lIKE A COORDINATE PLANE)!
        IT ISN'T ADDING THAT VALUE TO THE CURRENT X OR Y POSITION
        (I might be wrong but this is what I have been taught, if I am wrong change)
         */
        //I believe one tile on the grid is 12 in by 12 in. Double check with measuring tape.
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(-12,30,Math.toRadians(90)))
                        //Hanging Specimen #1
                        .lineToX(0)
                        .lineToY(13)
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, 2, 2))
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, -2, 2))
                        //Move sample #1 to Observation to become specimen
                        .lineToY(24)
                        .strafeTo(new Vector2d(24, 24))
                        .lineToY(6)
                        .turnTo(Math.toRadians(-180))
                        //Instead of pushing sample I might change to using intake if it is improved
                        .lineToY(30)
                        //Move sample #2 to observation while human player turns sample #1 into specimen
                        .lineToY(24)
                        .lineToX(30)
                        .turnTo(Math.toRadians(180))
                        .lineToY(6)
                        .turnTo(Math.toRadians(-180))
                        .lineToX(24)
                        .lineToY(30) /*This will push second sample to observation zone while collecting the specimen
                        assuming that specimen # 2 is placed along the grid line
                        */
                        //Hanging Specimen # 2
                        .lineToY(24)
                        .turnTo(Math.toRadians(-180))
                        .strafeTo(new Vector2d(0,24))
                        .lineToY(13)
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, 2, 2))
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, -2, 2))
                        //Retrieving Specimen # 3 from human player in observation zone
                        .lineToY(24)
                        .strafeTo(new Vector2d(24, 24))
                        .turnTo(Math.toRadians(-180))
                        .lineToY(30)
                        //Hanging Specimen # 3
                        .lineToY(24)
                        .strafeTo(new Vector2d(0, 24))
                        .turnTo(Math.toRadians(-180))
                        .lineToY(13)
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, 2, 2))
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, -2, 2))
                        //Getting last sample to observation zone
                        .lineToY(24)
                        .strafeTo(new Vector2d(30, 24))
                        .lineToY(6)
                        .lineToX(24)
                        .turnTo(Math.toRadians(-180))
                        .lineToY(30)
                        //Retrieve specimen # 4
                        .lineToY(24)
                        .lineToX(24)
                        .waitSeconds(2) // Waiting for human player(plz be quick!)
                        //Hanging specimen # 4
                        .lineToY(30)
                        .lineToY(24)
                        .strafeTo(new Vector2d(0, 24))
                        .turnTo(Math.toRadians(-180))
                        .lineToY(13)
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, 2, 2))
                        .stopAndAdd(new LiftActionRed(liftMotor, liftMotor1, -2, 2))
                        // Going to observation zone
                        .lineToY(24)
                        .strafeTo(new Vector2d(30, 24))
                        .lineToY(30)
                        // FINE TUNE MEASUREMENTS ON TUESDAY
                        .build()
        );
    }
}
class LiftActionRed implements Action {
    DcMotor liftMotor, liftMotor1;
    double power;
    ElapsedTime timer;
    double time;
    public LiftActionRed(DcMotor liftMotor, DcMotor liftMotor1, double power, double time){
        this.liftMotor = liftMotor;
        this.liftMotor1 = liftMotor1;
        this.power = power;
        this.time = time;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket){
        if(timer == null){
            timer = new ElapsedTime();
            liftMotor.setPower(power/1.5);
            liftMotor1.setPower(-power/1.5);
        }
        return timer.seconds() < time;
    }
}

