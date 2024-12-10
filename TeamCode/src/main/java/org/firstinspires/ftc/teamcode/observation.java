package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Observation", group="MyHardware")
public class observation extends LinearOpMode {
    myHardware robot = new myHardware();
    ElapsedTime runTime = new ElapsedTime();
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        robot.SidewaysRight(.25f, 1.25f, telemetry, this);
        robot.stopDriving();
    }
}