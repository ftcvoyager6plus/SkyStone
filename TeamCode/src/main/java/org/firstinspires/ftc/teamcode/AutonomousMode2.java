package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Buildplate Skybridge", group="Autonomous")
public class AutonomousMode2 extends LinearOpMode {
    VoyagerBot robot = new VoyagerBot();
    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_MOTOR_REV = 134.4;
    static final double DRIVE_GEAR_REDUCTION = 19.2;
    static final double WHEEL_RADIUS_INCHES = 10 / 2.54;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_RADIUS_INCHES * Math.PI);
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        robot.resetEncoders();
        waitForStart();
        robot.back.setPosition(1);
        //drive(0.6,-24);
        //strafe(0.6, 24);
        // 35.6 "units" is exactly 360 degrees
        robot.back.setPosition(0);
        drive(0.6, -20);
        strafe(0.4, 9);
        drive(0.1, -12);
        robot.back.setPosition(1);
        sleep(1000);
        drive(0.3, 29);
        turn(0.1,-45);
        robot.back.setPosition(0);
        sleep(500);
        strafe(0.3,-48);
        turn(0.3,20);
        drive(0.3,-15);
        //autoDrive(0.2, -12, 0, 0, 20);
    }
    private void drive(double speed, double inches) {
        inches = inches * 24 / 59;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            rightFrontTarget = robot.rightFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            robot.leftFront.setTargetPosition(leftFrontTarget);
            robot.leftBack.setTargetPosition(leftBackTarget);
            robot.rightFront.setTargetPosition(rightFrontTarget);
            robot.rightBack.setTargetPosition(rightBackTarget);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                telemetry.addData("Current Position", "%d %d %d %d", robot.leftFront.getCurrentPosition(), robot.leftBack.getCurrentPosition(), robot.rightFront.getCurrentPosition(), robot.rightBack.getCurrentPosition());
                telemetry.update();
            }

            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        sleep(250);
    }
    private void strafe(double speed, double inches) {
        inches = inches * 24 / 55;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
            rightFrontTarget = robot.rightFront.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            robot.leftFront.setTargetPosition(leftFrontTarget);
            robot.leftBack.setTargetPosition(leftBackTarget);
            robot.rightFront.setTargetPosition(rightFrontTarget);
            robot.rightBack.setTargetPosition(rightBackTarget);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                telemetry.addData("Current Position", "%d %d %d %d", robot.leftFront.getCurrentPosition(), robot.leftBack.getCurrentPosition(), robot.rightFront.getCurrentPosition(), robot.rightBack.getCurrentPosition());
                telemetry.update();
            }

            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        sleep(250);
    }
    private void turn(double speed, double degrees) {
        double inches = degrees * 35.6 / 360;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            rightFrontTarget = robot.rightFront.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightBack.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
            robot.leftFront.setTargetPosition(leftFrontTarget);
            robot.leftBack.setTargetPosition(leftBackTarget);
            robot.rightFront.setTargetPosition(rightFrontTarget);
            robot.rightBack.setTargetPosition(rightBackTarget);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                telemetry.addData("Current Position", "%d %d %d %d", robot.leftFront.getCurrentPosition(), robot.leftBack.getCurrentPosition(), robot.rightFront.getCurrentPosition(), robot.rightBack.getCurrentPosition());
                telemetry.update();
            }

            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        sleep(250);
    }
    public void autoDrive(double speed, double driveInches, double strafeInches, double rotateInches, double timeout) {
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        int drive = (int)(driveInches * COUNTS_PER_INCH);
        int rotate = (int)(rotateInches * COUNTS_PER_INCH);
        int strafe = (int)(strafeInches * COUNTS_PER_INCH);
        int leftFrontPower = drive + strafe + rotate;
        int leftBackPower = drive - strafe + rotate;
        int rightFrontPower = drive - strafe - rotate;
        int rightBackPower = drive + strafe - rotate;
        if(opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + leftFrontPower;
            leftBackTarget = robot.leftBack.getCurrentPosition() + leftBackPower;
            rightFrontTarget = robot.leftBack.getCurrentPosition() + rightFrontPower;
            rightBackTarget = robot.rightBack.getCurrentPosition() + rightBackPower;

            robot.leftFront.setTargetPosition(leftFrontTarget);
            robot.leftBack.setTargetPosition(leftBackTarget);
            robot.rightFront.setTargetPosition(rightFrontTarget);
            robot.rightBack.setTargetPosition(rightBackTarget);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();

            robot.leftFront.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));

            while(opModeIsActive() && (runtime.seconds() < timeout) && (robot.leftFront.isBusy() && robot.leftBack.isBusy() && robot.rightFront.isBusy() && robot.rightBack.isBusy())) {
                telemetry.addData("Runtime: %7d", timeout);
                telemetry.update();
            }
            robot.leftFront.setPower(0);
            robot.rightBack.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        sleep(250);
    }
}
