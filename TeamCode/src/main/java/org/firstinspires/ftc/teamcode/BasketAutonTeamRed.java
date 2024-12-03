package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class BasketAutonTeamRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(12, -30, Math.toRadians(-90)));
        DcMotor liftMotor, liftMotor1;
        liftMotor = hardwareMap.get(DcMotor.class, "lift1");
        liftMotor1 = hardwareMap.get(DcMotor.class, "lift2");
        CRServo gear;
        Servo Arm;
        gear = hardwareMap.get(CRServo.class, "CR 1");
        Arm = hardwareMap.get(Servo.class, "inArm");
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
                drive.actionBuilder(new Pose2d(12,-30,Math.toRadians(-90)))
                        //Collect sample#1
                        .lineToY(24)
                        .strafeTo(new Vector2d(-24,24))
                        .turnTo(Math.toRadians(180))
                        .stopAndAdd(new BasketIntakeArmActionBlue(Arm, .75))
                        .stopAndAdd(new BasketIntakeGearActionBlue(gear, 1, 2))
                        .stopAndAdd(new BasketIntakeArmActionBlue(Arm, 0))
                        //Basket sample#1
                        .turnTo(Math.toRadians(-45))
                        .strafeTo(new Vector2d(30, 30))
                        .stopAndAdd(new BasketLiftActionBlue(liftMotor, liftMotor1, 2, 2))
                        .stopAndAdd(new BasketIntakeArmActionBlue(Arm, .75))
                        .stopAndAdd(new BasketIntakeGearActionBlue(gear, -1, 2))
                        //If there is more time I will add more to this auton
                        .build()
        );
    }
}
class BasketLiftActionRed implements Action {
    DcMotor liftMotor, liftMotor1;
    double power;
    ElapsedTime timer;
    double time;
    public BasketLiftActionRed(DcMotor liftMotor, DcMotor liftMotor1, double power, double time){
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
class BasketIntakeArmActionRed implements Action {
    Servo arm;
    double position;
    public BasketIntakeArmActionRed(Servo arm, double position){
        this.arm = arm;
        this.position = position;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket){
        arm.setPosition(position);
        return !(arm.getPosition() == position);
    }
}
class BasketIntakeGearActionRed implements Action {
    CRServo gear;
    double power;
    double time;
    ElapsedTime timer;
    public BasketIntakeGearActionRed(CRServo gear, double power, double time){
        this.gear = gear;
        this.power = power;
        this.time = time;
    }
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket){
        if(timer == null){
            timer = new ElapsedTime();
            gear.setPower(power);
        }
        return timer.seconds() < time;
    }
}


