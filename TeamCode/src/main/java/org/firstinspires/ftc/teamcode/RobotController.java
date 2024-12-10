package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp
public class RobotController extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor Turntable = null;
    public DcMotor liftMotor, liftMotor1;
    private boolean toggle = false;
    CRServo Roller;
    Servo RightIntake;
    Servo LeftIntake;

    Orientation angles = new Orientation();

    double initYaw;
    double adjustedYaw;
    public void LiftFunc(){
        double power = gamepad2.right_stick_y;
        liftMotor.setPower(power);
        liftMotor1.setPower(-power);
//        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if (power<=0.25){
            liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            liftMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        }

//        motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void Drive(){
        adjustedYaw = angles.firstAngle-initYaw;
        double zerodYaw = -initYaw+angles.firstAngle;

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        double theta = Math.atan2(y, x) * 180/Math.PI; // aka angle

        double realTheta;

        realTheta = (360 - zerodYaw) + theta;

        double power = Math.hypot(x, y);

        double sin = Math.sin((realTheta * (Math.PI / 180)) - (Math.PI / 4));
        double cos = Math.cos((realTheta * (Math.PI / 180)) - (Math.PI / 4));
        double maxSinCos = Math.max(Math.abs(sin), Math.abs(cos));

        double leftFront = (power * cos / maxSinCos + turn);
        double rightFront = (power * sin / maxSinCos - turn);
        double leftBack = (power * sin / maxSinCos + turn);
        double rightBack = (power * cos / maxSinCos - turn);


        if ((power + Math.abs(turn)) > 1) {
            leftFront /= power + turn;
            rightFront /= power - turn;
            leftBack /= power + turn;
            rightBack /= power - turn;
        }

        leftFrontDrive.setPower(leftFront);
        rightFrontDrive.setPower(rightFront);
        leftBackDrive.setPower(leftBack);
        rightBackDrive.setPower(rightBack);
    }
    public void Intake(){
        if(gamepad2.right_bumper){
            Roller.setPower(1);
        }
        else if(gamepad2.left_bumper){
            Roller.setPower(-1);
        }else{
            Roller.setPower(0);
        }
    }
    public void IntakeArm(){
        if(gamepad1.right_bumper){
            LeftIntake.setPosition(0.75);
            RightIntake.setPosition(0.75);
            telemetry.addData("Status", "Yes");

        }
        else if(gamepad1.left_bumper){
            LeftIntake.setPosition(0);
            RightIntake.setPosition(0);
            telemetry.addData("Status", "Yes1");
        }
    }
    @Override
    public void runOpMode(){
        initYaw = angles.firstAngle;


        leftBackDrive = hardwareMap.get(DcMotor.class, "motor");
        rightBackDrive = hardwareMap.get(DcMotor.class, "motor1");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "motor2");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "motor3");
        liftMotor = hardwareMap.get(DcMotor.class, "lift1");
        liftMotor1 = hardwareMap.get(DcMotor.class, "lift2");
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // This is not shown in video, but this is to brake the motors to prevent drift. Feel free to delete this
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // toggle field/normal

        waitForStart();
        while(!isStopRequested() && opModeIsActive()){
            Drive();
            LiftFunc();
            Intake();
            IntakeArm();
        }

    }
}
