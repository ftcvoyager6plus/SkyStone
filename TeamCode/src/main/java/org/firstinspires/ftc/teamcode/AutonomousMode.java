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
    static final double WHEEL_DIAMETER_INCHES = 10 / 2.54;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        robot.resetEncoders();
        waitForStart();
        autoDrive(0.6, 12, 0, 0, 10);
    }
    public void autoDrive(double speed, double driveInches, double strafeInches, double rotateInches, double timeout) {
        int drive = (int)(driveInches * COUNTS_PER_INCH);
        int strafe = (int)(strafeInches * COUNTS_PER_INCH);
        int rotate = (int)(rotateInches * COUNTS_PER_INCH);

        int leftFrontPower = drive + strafe + rotate;
        int leftBackPower = drive - strafe + rotate;
        int rightFrontPower = drive - strafe - rotate;
        int rightBackPower = drive + strafe - rotate;

        robot.leftFront.setTargetPosition(leftFrontPower);
        robot.leftBack.setTargetPosition(leftBackPower);
        robot.rightFront.setTargetPosition(rightFrontPower);
        robot.rightBack.setTargetPosition(rightBackPower);

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
            telemetry.addData("Path1",  "Motor Powers %d %d %d %d", leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
            telemetry.update();
        }
        robot.leftFront.setPower(0);
        robot.leftBack.setPower(0);
        robot.rightFront.setPower(0);
        robot.rightBack.setPower(0);

        robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
