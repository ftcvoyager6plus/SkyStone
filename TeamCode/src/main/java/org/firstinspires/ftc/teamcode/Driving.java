package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.CameraDevice;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

public class Driving {
    private VoyagerBot robot = new VoyagerBot();
    private LinearOpMode opmode;
    static final double COUNTS_PER_MOTOR_REV = 134.4;
    static final double DRIVE_GEAR_REDUCTION = 19.2;
    static final double WHEEL_RADIUS_INCHES = 10 / 2.54;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_RADIUS_INCHES * Math.PI);
    private boolean targetVisible = false;
    private OpenGLMatrix lastLocation = null;
    Detecting detector = new Detecting();
    Orientation lastAngles = new Orientation();
    private ElapsedTime runtime = new ElapsedTime();
    double globalAngle, correction;
    boolean armDown = false;
    public Driving(LinearOpMode opmode) {
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
        detector.init(opmode.hardwareMap);
    }
    public void northeast(double speed, double inches) {
        inches = inches * 24 / 59;
        int leftFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            robot.leftBack.setTargetPosition(leftFrontTarget);
            robot.rightFront.setTargetPosition(rightBackTarget);

            robot.leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftBack.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftBack.isBusy() || robot.rightFront.isBusy())) {

            }
            robot.leftBack.setPower(0);
            robot.rightFront.setPower(0);

            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        opmode.sleep(100);
    }
    public void southwest(double speed, double inches) {
        inches = inches * 24 / 59;
        int leftFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            rightBackTarget = robot.rightBack.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            robot.leftFront.setTargetPosition(leftFrontTarget);
            robot.rightBack.setTargetPosition(rightBackTarget);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftFront.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {

            }
            robot.leftFront.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        opmode.sleep(100);
    }
    public void drive(double speed, double inches) {
        inches = inches * 24 / 59;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
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

            /*if(armDown) {
                robot.leftFront.setPower(1.02 * Math.abs(speed));
                robot.leftBack.setPower(1.02 * Math.abs(speed));
            } else {
                robot.leftFront.setPower(Math.abs(speed));
                robot.leftBack.setPower(Math.abs(speed));
            }*/
            robot.leftFront.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {

            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        opmode.sleep(100);
    }
    private void resetAngle() {
        lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        globalAngle = 0;
    }
    private double getAngle() {
        Orientation angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;
        if(deltaAngle < -180) {
            deltaAngle += 360;
        } else if(deltaAngle > 180) {
            deltaAngle -= 360;
        }
        globalAngle += deltaAngle;
        lastAngles = angles;
        return globalAngle;
    }
    private double checkDirection() {
        double correction, angle, gain = 0.09;
        angle = getAngle();
        if(angle == 0) {
            correction = 0;
        } else {
            correction = -angle;
        }
        correction = correction * gain;
        return correction;
    }
    public void gyroDrive(double speed, double inches) {
        resetAngle();
        inches = inches * 24 / 59;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
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
            robot.rightFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                correction = checkDirection();
                robot.leftFront.setPower(Math.abs(speed) - correction);
                robot.leftBack.setPower(Math.abs(speed) - correction);
                robot.rightFront.setPower(Math.abs(speed) + correction);
                robot.rightBack.setPower(Math.abs(speed) + correction);
            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        opmode.sleep(100);
    }
    public int detectorDrive(double speed, double inches) {
        CameraDevice.getInstance().setFlashTorchMode(true);
        inches = inches * 24 / 59;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        float Y = 0;
        double ret = 0;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
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
            runtime.reset();
            robot.leftFront.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                opmode.telemetry.addData("Runtime", runtime.toString());
                opmode.telemetry.update();
                if (((VuforiaTrackableDefaultListener) detector.stoneTarget.getListener()).isVisible()) {
                    targetVisible = true;
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) detector.stoneTarget.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                }
                if(runtime.seconds() >= 5.4/2) {
                    CameraDevice.getInstance().setFlashTorchMode(false);
                    return 3;
                }
                if (targetVisible) {
                    /*VectorF translation = lastLocation.getTranslation();
                    telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                            translation.get(0) / detector.mmPerInch, translation.get(1) / detector.mmPerInch, translation.get(2) / detector.mmPerInch);
                    Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                    telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);*/
                    VectorF translation = lastLocation.getTranslation();
                    Y = translation.get(1) / detector.mmPerInch;
                    opmode.telemetry.addData("Y value", Y);
                    opmode.telemetry.update();
                    if((Y < 0.3) && (Y > -0.3)) {
                        ret = runtime.seconds();
                        CameraDevice.getInstance().setFlashTorchMode(false);
                        break;
                    }
                }
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
        opmode.sleep(100);
        if(ret < 2.25/2) {
            return 1;
        } else if((ret >= 2.25/2) && (ret <= 4.25/2)) {
            return 2;
        } else {
            return 3;
        }
    }
    public int detectorDriveRight(double speed, double inches) {
        CameraDevice.getInstance().setFlashTorchMode(true);
        inches = inches * 24 / 59;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        float Y = 0;
        double ret = 0;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
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
            runtime.reset();
            robot.leftFront.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                opmode.telemetry.addData("Runtime", runtime.toString());
                opmode.telemetry.update();
                if (((VuforiaTrackableDefaultListener) detector.stoneTarget.getListener()).isVisible()) {
                    targetVisible = true;
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) detector.stoneTarget.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                }
                if(runtime.seconds() >= 3.1) {
                    CameraDevice.getInstance().setFlashTorchMode(false);
                    return 3;
                }
                if (targetVisible) {
                    /*VectorF translation = lastLocation.getTranslation();
                    telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                            translation.get(0) / detector.mmPerInch, translation.get(1) / detector.mmPerInch, translation.get(2) / detector.mmPerInch);
                    Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                    telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);*/
                    VectorF translation = lastLocation.getTranslation();
                    Y = translation.get(1) / detector.mmPerInch;
                    opmode.telemetry.addData("Y value", Y);
                    opmode.telemetry.update();
                    if((Y < 0.3) && (Y > -0.3)) {
                        ret = runtime.seconds();
                        CameraDevice.getInstance().setFlashTorchMode(false);
                        break;
                    }
                }
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
        opmode.sleep(100);
        if(ret < 1.73) {
            return 1;
        } else if((ret >= 1.73) && (ret <= 2.6)) {
            return 2;
        } else {
            return 3;
        }
    }
    public double detectorDriveStop(double speed, double inches) {
        CameraDevice.getInstance().setFlashTorchMode(true);
        inches = inches * 24 / 59;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        float Y = 0;
        double ret = 0;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
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
            runtime.reset();
            robot.leftFront.setPower(Math.abs(speed));
            robot.rightFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                opmode.telemetry.addData("Runtime", runtime.toString());
                opmode.telemetry.update();
                if (((VuforiaTrackableDefaultListener) detector.stoneTarget.getListener()).isVisible()) {
                    targetVisible = true;
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) detector.stoneTarget.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                }
                opmode.telemetry.addData("Live Runtime", runtime.seconds());
                opmode.telemetry.update();
                if (targetVisible) {
                    /*VectorF translation = lastLocation.getTranslation();
                    telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                            translation.get(0) / detector.mmPerInch, translation.get(1) / detector.mmPerInch, translation.get(2) / detector.mmPerInch);
                    Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                    telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);*/
                    VectorF translation = lastLocation.getTranslation();
                    Y = translation.get(1) / detector.mmPerInch;
                    if((Y < 0.3) && (Y > -0.3)) {
                        ret = runtime.seconds();
                        CameraDevice.getInstance().setFlashTorchMode(false);
                        break;
                    }
                }
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
        opmode.sleep(100);
        return ret;
    }
    public void strafe(double speed, double inches) {
        inches = inches * 24 / 55;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() -  (int)(inches * COUNTS_PER_INCH);
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
            robot.rightFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                stuff();
            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        opmode.sleep(100);
    }
    public void turn(double speed, double degrees) {
        double inches = degrees * 35.6 / 360;
        int leftFrontTarget, leftBackTarget, rightFrontTarget, rightBackTarget;
        if(opmode.opModeIsActive()) {
            leftFrontTarget = robot.leftFront.getCurrentPosition() + (int)(inches * COUNTS_PER_INCH);
            leftBackTarget = robot.leftBack.getCurrentPosition() +  (int)(inches * COUNTS_PER_INCH);
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
            robot.rightFront.setPower(Math.abs(speed));
            robot.leftBack.setPower(Math.abs(speed));
            robot.rightBack.setPower(Math.abs(speed));

            while(opmode.opModeIsActive() && (robot.leftFront.isBusy() || robot.leftBack.isBusy() || robot.rightFront.isBusy() || robot.rightBack.isBusy())) {
                stuff();
            }
            robot.leftFront.setPower(0);
            robot.rightFront.setPower(0);
            robot.leftBack.setPower(0);
            robot.rightBack.setPower(0);

            robot.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        opmode.sleep(100);
    }
    public void gyroturn(double speed, double degrees) {
        Orientation angles;
        float heading;
        robot.imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
        if(opmode.opModeIsActive()) {
            //rightFront +
            //leftBack +
            //rightFront -
            //rightBack -
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
        opmode.sleep(100);
    }
    void backServosDown() {
        robot.back.setPosition(1.0);
        robot.back2.setPosition(0.0);
        opmode.sleep(600);
    }
    void backServosUp() {
        robot.back.setPosition(0.35);
        robot.back2.setPosition(0.65);
        opmode.sleep(600);
    }
    void skystoneDown() {
        armDown = true;
        robot.skystone.setPosition(0);
        opmode.sleep(500);
    }
    void skystoneUp() {
        armDown = false;
        robot.skystone.setPosition(1);
        opmode.sleep(500);
    }
}