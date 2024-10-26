package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Lift extends LinearOpMode {
    DcMotor motor;
    DcMotor motor1;
    public void LiftFunc(){
        double power = gamepad1.right_stick_y;
        motor.setPower(power/2);
        motor1.setPower(-power/2);
    }
    @Override
    public void runOpMode(){
        motor = hardwareMap.get(DcMotor.class, "lift1");
        motor1 = hardwareMap.get(DcMotor.class, "lift2");
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            LiftFunc();
            telemetry.addData("motor", motor.getPower());
            telemetry.update();
        }
    }
}
