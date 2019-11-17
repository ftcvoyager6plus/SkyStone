package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class VoyagerBot {
    public DcMotor leftFront = null;
    public DcMotor leftBack = null;
    public DcMotor rightFront = null;
    public DcMotor rightBack = null;
    public Servo claw = null;
    public Servo back = null;
    //public Servo skystone = null;
    public DcMotor lift = null;
    HardwareMap hwMap = null;
    private ElapsedTime runtime = new ElapsedTime();
    static final double COUNTS_PER_MOTOR_REV = 537.6;
    static final double DRIVE_GEAR_REDUCTION = 19.2;
    static final double WHEEL_RADIUS_INCHES = 10 / 2.54;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_RADIUS_INCHES * Math.PI);
    public VoyagerBot() {

    }
    public void resetEncoders() {
        this.leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        leftFront = hwMap.get(DcMotor.class, "left_front");
        leftBack = hwMap.get(DcMotor.class, "left_back");
        rightFront = hwMap.get(DcMotor.class, "right_front");
        rightBack = hwMap.get(DcMotor.class, "right_back");
        claw = hwMap.get(Servo.class, "claw");
        back = hwMap.get(Servo.class, "back");
        lift = hwMap.get(DcMotor.class, "lift_motor");
        //extension = hwMap.get(Servo.class, "extension");
        //skystone = hwMap.get(Servo.class, "stone");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        claw.setPosition(0);
        back.setPosition(0);
        //extension.setPosition(0);
        //skystone.setPosition(0);
    }
    @Deprecated
    public void drive(double speed, int time) {
        this.leftFront.setPower(speed);
        this.leftBack.setPower(speed);
        this.rightFront.setPower(speed);
        this.rightBack.setPower(speed);
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {

        }
        this.leftFront.setPower(0);
        this.leftBack.setPower(0);
        this.rightFront.setPower(0);
        this.rightBack.setPower(0);
        try {
            Thread.sleep(350);
        } catch(InterruptedException e) {

        }
    }
    @Deprecated
    public void turn(double speed, int time) {
        this.leftFront.setPower(speed);
        this.leftBack.setPower(speed);
        this.rightFront.setPower(-speed);
        this.rightBack.setPower(-speed);
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {

        }
        this.leftFront.setPower(0);
        this.leftBack.setPower(0);
        this.rightFront.setPower(0);
        this.rightBack.setPower(0);
        try {
            Thread.sleep(350);
        } catch(InterruptedException e) {

        }
    }
    @Deprecated
    public void strafe(double speed, int time) {
        this.leftFront.setPower(speed);
        this.leftBack.setPower(-speed);
        this.rightFront.setPower(-speed);
        this.rightBack.setPower(speed);
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {

        }
        this.leftFront.setPower(0);
        this.leftBack.setPower(0);
        this.rightFront.setPower(0);
        this.rightBack.setPower(0);
        try {
            Thread.sleep(350);
        } catch(InterruptedException e) {

        }
    }
}
