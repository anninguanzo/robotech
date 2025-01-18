package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class lift extends LinearOpMode {
    DcMotor motor;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    double integralSum = 0;
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;
    ElapsedTime tier = new ElapsedTime();
    public void LiftFunc() {
        double power = gamepad2.left_stick_y;
        telemetry.addData("lift power", motor.getPower());
        telemetry.addData("lift power", motor1.getPower());
        telemetry.addData("lift power", motor2.getPower());
        telemetry.addData("lift power", motor3.getPower());
        motor.setPower(power);
        motor2.setPower(power);
        motor3.setPower(-power);
        motor1.setPower(-power);
    }
    @Override
    public void runOpMode(){
        motor = hardwareMap.get(DcMotor.class, "lift1");
        motor1 = hardwareMap.get(DcMotor.class, "lift2");
        motor2 = hardwareMap.get(DcMotor.class, "lift3");
        motor3 = hardwareMap.get(DcMotor.class, "lift4");
        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            LiftFunc();
            telemetry.addData("motor", motor.getPower());
            telemetry.update();
        }
    }
    //Pid Control
    /*
    public double PIDControl(double reference, double state){
        double error = reference - state;

    }
    */

}
