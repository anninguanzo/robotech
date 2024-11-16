package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class Lift extends LinearOpMode {
    DcMotor motor;
    DcMotor motor1;
    TouchSensor touchSensor;

    public void LiftFunc(){
        double power = gamepad2.right_stick_y;
        motor.setPower(power/1.5);
        motor1.setPower(-power/1.5);
//        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("MotorPower", motor.getPower());
        telemetry.addData("Motor1Power", motor1.getPower());
    }
    @Override
    public void runOpMode(){
        motor = hardwareMap.get(DcMotor.class, "lift1");
        motor1 = hardwareMap.get(DcMotor.class, "lift2");
        touchSensor = hardwareMap.get(TouchSensor.class, "TouchSensor");
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            if(!touchSensor.isPressed()) LiftFunc();
            telemetry.addData("motor", motor.getPower());
            telemetry.update();
        }
    }
}
