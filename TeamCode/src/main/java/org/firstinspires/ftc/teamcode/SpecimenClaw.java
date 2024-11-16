package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class SpecimenClaw extends LinearOpMode {
    Servo servo;
    public void Claw(){
        servo.setPosition(0.5);
        if(gamepad2.y){
            servo.setPosition(0);
            telemetry.addData("Status", "Yes");
        }else if(gamepad2.b){
            servo.setPosition(1);
            telemetry.addData("Status", "Yes1");
        }
    }
    @Override
    public void runOpMode() throws InterruptedException{
        servo = hardwareMap.get(Servo.class, "Claw");
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            Claw();
            telemetry.addData("Servo: ", servo.getPosition());
            telemetry.update();
        }
    }
}
