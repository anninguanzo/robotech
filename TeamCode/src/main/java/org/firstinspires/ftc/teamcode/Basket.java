package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Basket", group="MyHardware")
public class Basket extends LinearOpMode {
    MyHardware robot = new MyHardware();
    ElapsedTime runTime = new ElapsedTime();
    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        robot.SidewaysLeft(.25f, 1.25f, telemetry, this);
        robot.stopDriving();
    }
}