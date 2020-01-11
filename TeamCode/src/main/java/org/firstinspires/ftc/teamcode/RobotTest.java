package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Teleop 2P", group="Linear Opmode")
public class RobotTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private double leftFrontPower;
    private double leftBackPower;
    private double rightFrontPower;
    private double rightBackPower;
    private double drive; //testing
    private double strafe;
    private double rotate;
    private boolean button_a;
    private boolean button_b;
    private boolean button_du;
    private boolean button_dd;
    private boolean bumper_left;
    private boolean bumper_right;

    private static final double INCREMENT = 0.03;
    private static final int CYCLE_MS = 50;
    private static final double MAX_POS = 1.0;
    private static final double MIN_POS = 0.0;
    private double position = (MAX_POS - MIN_POS) / 2;

    private static final double BINCREMENT = 0.03;
    private static final double BMAX_POS = 1.0;
    private static final double BMIN_POS = 0.0;
    private double bposition = (BMAX_POS - BMIN_POS) / 2;

    private static final double CINCREMENT = 0.06;
    private static final double CMAX_POS = 1.0;
    private static final double CMIN_POS = 0.0;
    private double cposition = (0.4);

    private static final double DINCREMENT = 0.03;
    private static final double DMAX_POS = 1.0;
    private static final double DMIN_POS = 0.0;
    private double dposition = (0.5);
    private boolean halfspeed = false;
    VoyagerBot robot = new VoyagerBot();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {
            drive = -gamepad1.left_stick_y; //forward and backward
            strafe = 0.80 * gamepad1.right_stick_x; // side to side and diagonal
            rotate = 0.85 * gamepad1.left_stick_x; // rotate in place
            button_a = gamepad2.a;
            button_b = gamepad2.b;
            button_du = gamepad1.dpad_up;
            button_dd = gamepad1.dpad_down;
            bumper_left = gamepad2.left_bumper;
            bumper_right = gamepad2.right_bumper;
            if(gamepad1.right_stick_button) {
                rotate = 0.5 * rotate;
                drive = 0.5 * drive;
                strafe = 0.5 * strafe;
            }
            if(bumper_left) {
                robot.lift.setPower(1);
            } else {
                robot.lift.setPower(0);
            }
            if(bumper_right) {
                robot.lift.setPower(-1);
            } else {
                robot.lift.setPower(0);
            }
            if(button_a) {
                position += INCREMENT;
                if(position >= MAX_POS) {
                    position = MAX_POS;
                }
            } else if(button_b) {
                position -= INCREMENT;
                if (position <= MIN_POS) {
                    position = MIN_POS;
                }
            }
            if(gamepad1.dpad_right) {
                cposition += CINCREMENT;
                if(cposition >= CMAX_POS) {
                    cposition = CMAX_POS;
                }
            } else if(gamepad1.dpad_left) {
                cposition -= CINCREMENT;
                if (cposition <= CMIN_POS) {
                    cposition = CMIN_POS;
                }
            }
            if(button_dd) {
                bposition += BINCREMENT;
                if(bposition >= BMAX_POS) {
                    bposition = BMAX_POS;
                }
            } else if(button_du) {
                bposition -= BINCREMENT;
                if (bposition <= BMIN_POS) {
                    bposition = BMIN_POS;
                }
            }
            if(gamepad2.dpad_up) {
                robot.extension.setPower(-0.5);
                robot.extension2.setPower(0.5);
            } else {
                robot.extension.setPower(0);
                robot.extension2.setPower(0);
            }
            if(gamepad2.dpad_down) {
                robot.extension.setPower(0.5);
                robot.extension2.setPower(-0.5);
            } else {
                robot.extension.setPower(0);
                robot.extension2.setPower(0);
            }
            /* * * * * * * * * * * *
             * Left stick:
             * - up and down moves forwards and backwards
             * - left and right rotates the robot in place
             * Right stick:
             * - strafing side to side
             * apparently you can face forward while moving in a circle
             * by strafing and rotating at the same time
             */
            leftFrontPower = drive + strafe + rotate;
            leftBackPower = drive - strafe + rotate;
            rightFrontPower = drive - strafe - rotate;
            rightBackPower = drive + strafe - rotate;

            robot.leftFront.setPower(leftFrontPower);
            robot.leftBack.setPower(leftBackPower);
            robot.rightFront.setPower(rightFrontPower);
            robot.rightBack.setPower(rightBackPower);
            robot.back.setPosition(bposition);
            robot.claw.setPosition(position);
            robot.skystone.setPosition(cposition);
            sleep(CYCLE_MS);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("StoneServo",cposition);
            telemetry.addData("Motors", "left front (%.2f), left back (%.2f), right front (%.2f), right back (%.2f)", leftFrontPower, leftBackPower, rightFrontPower, rightBackPower);
            telemetry.update();
        }
    }
}
