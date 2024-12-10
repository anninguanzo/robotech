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
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class specimenAutonTeamBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        mecanumdrive drive = new mecanumdrive(hardwareMap, new Pose2d(0,0, 0));
        DcMotor liftMotor, liftMotor1;
        Servo rightIntake, leftIntake;
        liftMotor = hardwareMap.get(DcMotor.class, "lift1");
        liftMotor1 = hardwareMap.get(DcMotor.class, "lift2");
        rightIntake = hardwareMap.get(Servo.class, "RightIntake");
        leftIntake = hardwareMap.get(Servo.class, "LeftIntake");
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
                drive.actionBuilder(new Pose2d(0,0, 0))
                        .lineToX(12)
                        .strafeTo(new Vector2d(12, 12))
                        .stopAndAdd(new LiftActionBluee(liftMotor, liftMotor1, -2, 2, leftIntake, rightIntake))
                        .lineToX(5)
                        .stopAndAdd(new LiftActionBluee(liftMotor, liftMotor1, 2, 2, leftIntake, rightIntake))
                        .build()
        );
    }
}
class LiftActionBluee implements Action {
    DcMotor liftMotor, liftMotor1;
    Servo rightIntake, leftIntake;
    double power;
    ElapsedTime timer;
    double time;
    public LiftActionBluee(DcMotor liftMotor, DcMotor liftMotor1, double power, double time, Servo leftIntake, Servo rightIntake){
        this.liftMotor = liftMotor;
        this.liftMotor1 = liftMotor1;
        this.rightIntake = rightIntake;
        this.leftIntake = leftIntake;
        this.power = power;
        this.time = time;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket){
        if(timer == null){
            timer = new ElapsedTime();
            if(leftIntake.getPosition() != 0 && rightIntake.getPosition() != 0){
                leftIntake.setPosition(0);
                rightIntake.setPosition(0);
            }
        }
        while(timer.seconds() < time){
            liftMotor.setPower(power/1.5);
            liftMotor1.setPower(-power/1.5);
        }
        return timer.seconds() < time;
    }
}
class IntakeActionBlue implements Action {
    Servo RightIntake;
    Servo LeftIntake;
    double time;

    public IntakeActionBlue(Servo rightArm, Servo leftArm){
        /*this.RightIntake = rightArm;
        this.LeftIntake = leftArm;*/
    }
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket){
        RightIntake.setPosition(0);
        LeftIntake.setPosition(0);
        return true;
    }
}