package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous Mode", group="Autonomous")
public class AutonomousMode extends LinearOpMode {
    VoyagerBot robot = new VoyagerBot();
    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_MOTOR_REV = 134.4;
    static final double DRIVE_GEAR_REDUCTION = 19.2;
    static final double WHEEL_RADIUS_INCHES = 10 / 2.54;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (2 * WHEEL_RADIUS_INCHES * Math.PI);
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        robot.resetEncoders();
        waitForStart();
        robot.back.setPosition(1);
        // 36 rotates 180*
        autoDrive(0.4, -20, 0, 0, 20);
        autoDrive(0.4, 0, 5, 0, 20);
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
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opModeIsActive() && (runtime.seconds() < timeout) && (robot.leftFront.isBusy() && robot.leftBack.isBusy() && robot.rightFront.isBusy() && robot.rightBack.isBusy())) {
                telemetry.addData("Runtime: %7d", timeout);
                telemetry.update();
            }
            double inc = 0;
            double power = 0;
            while(inc <= Math.PI/2) {
                power = speed * Math.cos(inc);
                inc += Math.PI/24;
                robot.leftFront.setPower(Math.abs(power));
                robot.leftBack.setPower(Math.abs(power));
                robot.rightFront.setPower(Math.abs(power));
                robot.rightBack.setPower(Math.abs(power));
                sleep(15);
            }
            robot.leftFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        sleep(250);
    }
}
