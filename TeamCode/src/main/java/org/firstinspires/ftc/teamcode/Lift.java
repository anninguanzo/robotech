package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class Lift extends LinearOpMode {
    DcMotor motorUp;
    DcMotor motorDown;
    public void LiftFunc(){
        double power = gamepad1.right_stick_y;
        double powerDivisor = 2;
        motorUp.setPower(power/powerDivisor);
        motorDown.setPower(-power/powerDivisor);
    }
    @Override
    public void runOpMode(){
        motorUp = hardwareMap.get(DcMotor.class, "lift1");
        motorDown = hardwareMap.get(DcMotor.class, "lift2");
        waitForStart();
        while(opModeIsActive() && !isStopRequested()){
            LiftFunc();
            telemetry.addData("motor", motor.getPower());
            telemetry.update();
        }
    }
}
