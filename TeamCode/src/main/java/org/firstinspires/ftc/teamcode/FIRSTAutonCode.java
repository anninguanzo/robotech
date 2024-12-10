package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class FIRSTAutonCode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        mecanumdrive drive = new mecanumdrive(hardwareMap, new Pose2d(0, 0, 0));
        waitForStart();
        /*
        ATTENTION WHO EVER IS WATCHING THIS CODE
        WHEN IT SAYS .lineToX(INCHES) OR .lineToY(INCHES)
        IT IS MOVING THE ROBOT'S CURRENT X OR Y POSITION
        TO THE INCHES GIVEN(lIKE A COORDINATE PLANE)!
        IT ISN'T ADDING THAT VALUE TO THE CURRENT X OR Y POSITION
        (I might be wrong but this is what I have been taught, if I am wrong change)
         */
        //I believe one tile on the grid is 12 in by 12 in. Double check with measuring tape.

    }
}
