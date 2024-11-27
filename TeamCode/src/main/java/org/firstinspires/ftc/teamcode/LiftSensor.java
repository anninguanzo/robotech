package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp
public class LiftSensor extends LinearOpMode {
    DigitalChannel touchSensor;
    @Override
    public void runOpMode(){
        touchSensor = hardwareMap.get(DigitalChannel.class, "TouchSensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            if(touchSensor.getState()){
                telemetry.addData("Button", "NotPressed");
            }
            else{
                telemetry.addData("Button", "Pressed");
            }
            telemetry.update();
        }
    }
}