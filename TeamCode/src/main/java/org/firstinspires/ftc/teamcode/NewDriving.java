package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class NewDriving {
    private VoyagerBot robot = new VoyagerBot();
    private LinearOpMode opmode;
    static final double COUNTS_PER_MOTOR_REV = 134.4;
    static final double DRIVE_GEAR_REDUCTION = 19.2;
    static final double WHEEL_RADIUS_INCHES = 10 / 2.54;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_RADIUS_INCHES * Math.PI);
    private boolean targetVisible = false;
    private OpenGLMatrix lastLocation = null;
    //Detecting detector = new Detecting();
    Orientation lastAngles = new Orientation();
    private ElapsedTime runtime = new ElapsedTime();
    double globalAngle, correction;
    boolean armDown = false;
    public NewDriving(LinearOpMode opmode) {
        this.opmode = opmode;
    }
    public void stuff() {

    }

    public void resetEncoders() {
        robot.resetEncoders();
    }
    public void initHwMap() {
        robot.init(opmode.hardwareMap);
        opmode.telemetry.addData("[!]", "Wait for camera initialization...");
        opmode.telemetry.update();
        //detector.init(opmode.hardwareMap);
    }
    public void drive(double speed, double inches) {
        inches = inches * 24 / 59;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
            rightFrontTarget = robot.rightFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            robot.leftFront.setPower(speed);
            robot.rightFront.setPower(speed);
            robot.leftBack.setPower(speed);
            robot.rightBack.setPower(speed);
            if(inches >= 0) {
                while(opmode.opModeIsActive() && (robot.leftFront.getCurrentPosition() <= leftFrontTarget | robot.leftBack.getCurrentPosition() <= leftBackTarget | robot.rightFront.getCurrentPosition() <= rightFrontTarget | robot.rightBack.getCurrentPosition() <= rightBackTarget)) {

                }
            } else {
                while(opmode.opModeIsActive() && (robot.leftFront.getCurrentPosition() >= leftFrontTarget | robot.leftBack.getCurrentPosition() >= leftBackTarget | robot.rightFront.getCurrentPosition() >= rightFrontTarget | robot.rightBack.getCurrentPosition() >= rightBackTarget)) {

                }
            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);
        }
    }
    public void strafe(double speed, double inches) {
        inches = inches * 24 / 55;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() -  (int)(inches * COUNTS_PER_INCH);
            rightFrontTarget = robot.rightFront.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            robot.leftFront.setPower(speed);
            robot.rightFront.setPower(-speed);
            robot.leftBack.setPower(-speed);
            robot.rightBack.setPower(speed);
            if(inches >= 0) {
                while(opmode.opModeIsActive() && (robot.leftFront.getCurrentPosition() <= leftFrontTarget | robot.leftBack.getCurrentPosition() >= leftBackTarget | robot.rightFront.getCurrentPosition() >= rightFrontTarget | robot.rightBack.getCurrentPosition() <= rightBackTarget)) {

                }
            } else {
                while(opmode.opModeIsActive() && (robot.leftFront.getCurrentPosition() >= leftFrontTarget | robot.leftBack.getCurrentPosition() <= leftBackTarget | robot.rightFront.getCurrentPosition() <= rightFrontTarget | robot.rightBack.getCurrentPosition() >= rightBackTarget)) {

                }
            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);
        }
    }
    public void strafeTillLimit(double speed, double _inches) {
        double inches = Math.abs(_inches);
        if(opmode.opModeIsActive()) {
            robot.leftFront.setPower(speed);
            robot.rightFront.setPower(-speed);
            robot.leftBack.setPower(-speed);
            robot.rightBack.setPower(speed);
            while(opmode.opModeIsActive() && (robot.distance.getDistance(DistanceUnit.INCH) > inches)) {
                opmode.telemetry.addData("Distance", robot.distance.getDistance(DistanceUnit.INCH));
                opmode.telemetry.update();
            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);
        }
    }
    public void gyroturn(double speed, double degrees) {
        Orientation angles;
        float heading;
        robot.imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
        if(opmode.opModeIsActive()) {
            robot.leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            robot.leftFront.setPower(speed);
            robot.rightFront.setPower(-speed);
            robot.leftBack.setPower(speed);
            robot.rightBack.setPower(-speed);
            while(opmode.opModeIsActive()) {
                angles   = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                //heading = AngleUnit.DEGREES.normalize(angles.firstAngle);
                heading = angles.firstAngle;
                if((heading < (degrees + 1)) && (heading > (degrees - 1))) {
                    robot.leftFront.setPower(0);
                    robot.rightFront.setPower(0);
                    robot.leftBack.setPower(0);
                    robot.rightBack.setPower(0);
                    return;
                }
            }
            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public void turn(double speed, double degrees) {
        double inches = degrees * 35.6 / 360;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
            rightFrontTarget = robot.rightFront.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightBack.getCurrentPosition() - (int)(inches * COUNTS_PER_INCH);
            robot.leftFront.setPower(speed);
            robot.rightFront.setPower(speed);
            robot.leftBack.setPower(speed);
            robot.rightBack.setPower(speed);
            if(inches >= 0) {
                while(opmode.opModeIsActive() && (robot.leftFront.getCurrentPosition() <= leftFrontTarget | robot.leftBack.getCurrentPosition() <= leftBackTarget | robot.rightFront.getCurrentPosition() >= rightFrontTarget | robot.rightBack.getCurrentPosition() >= rightBackTarget)) {

                }
            } else {
                while(opmode.opModeIsActive() && (robot.leftFront.getCurrentPosition() >= leftFrontTarget | robot.leftBack.getCurrentPosition() >= leftBackTarget | robot.rightFront.getCurrentPosition() <= rightFrontTarget | robot.rightBack.getCurrentPosition() <= rightBackTarget)) {

                }
            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);
        }
    }
    public void parseMoves(Path[] paths) {
        for(Path path: paths) {
            if(path.move == M.DRIVE) {
                drive(path.speed, path.arg);
            } else if(path.move == M.STRAFE) {
                strafe(path.speed, path.arg);
            } else if(path.move == M.ROTATE) {
                gyroturn(path.speed, path.arg);
            } else if(path.move == M.BACK_DOWN) {
                backServosDown();
            } else if(path.move == M.BACK_UP) {
                backServosUp();
            } else if(path.move == M.SKYSTONE_DOWN) {
                skystoneDown();
            } else if(path.move == M.SKYSTONE_UP) {
                skystoneUp();
            } else if(path.move == M.SKYSTONE_POS) {
                skystonePos(path.arg);
            } else if(path.move == M.BACK_UP_SKYSTONE_HALF) {
                backServosUpAndSkystoneHalf();
            } else if(path.move == M.STRAFE_TILL) {
                strafeTillLimit(path.speed, path.arg);
            } else if(path.move == M.SKYSTONE_DOWN_GRIPPER_OUT) {
                skystoneDownGripperOut();
            } else if(path.move == M.GRIPPER_IN) {
                gripperIn();
            } else if(path.move == M.BACK_HALF) {
                backHalf();
            }
            opmode.sleep(200);
        }
    }
    void backHalf() {
        robot.back.setPosition(0.675);
        robot.back2.setPosition(0.325);
    }
    void backServosUpAndSkystoneHalf() {
        robot.back.setPosition(0.35);
        robot.back2.setPosition(0.65);
        robot.skystone.setPosition(0.25);
        opmode.sleep(500);
    }
    void backServosDown() {
        robot.back.setPosition(1.0);
        robot.back2.setPosition(0.0);
        opmode.sleep(500);
    }
    void gripperIn() {
        robot.gripper.setPosition(0);
        opmode.sleep(450);
    }
    void backServosUp() {
        robot.back.setPosition(0.35);
        robot.back2.setPosition(0.65);
        opmode.sleep(500);
    }
    void skystoneDown() {
        armDown = true;
        robot.skystone.setPosition(0);
        opmode.sleep(500);
    }
    void skystoneDownGripperOut() {
        armDown = true;
        robot.skystone.setPosition(0);
        opmode.sleep(300);
        robot.gripper.setPosition(1);
        opmode.sleep(300);
    }
    void skystoneUp() {
        armDown = false;
        robot.skystone.setPosition(1);
        opmode.sleep(500);
    }
    void skystonePos(double pos) {
        robot.skystone.setPosition(Math.abs(pos));
        opmode.sleep(500);
    }
    void skystoneHalf() {
    }
}