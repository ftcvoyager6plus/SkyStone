package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Teleop Mode", group="Linear Opmode")
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
    private boolean button_dl;
    private boolean button_dr;

    private static final double INCREMENT = 0.03;
    private static final int CYCLE_MS = 50;
    private static final double MAX_POS = 1.0;
    private static final double MIN_POS = 0.0;
    private double position = (MAX_POS - MIN_POS) / 2;

    private static final double BINCREMENT = 0.03;
    private static final double BMAX_POS = 1.0;
    private static final double BMIN_POS = 0.0;
    private double bposition = (BMAX_POS - BMIN_POS) / 2;

    private static final double CINCREMENT = 0.03;
    private static final double CMAX_POS = 0.8;
    private static final double CMIN_POS = 0.7;
    private double cposition = (0.4);

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
            strafe = 0.90 * gamepad1.right_stick_x; // side to side and diagonal
            rotate = 0.85 * gamepad1.left_stick_x; // rotate in place
            button_a = gamepad1.a;
            button_b = gamepad1.b;
            button_du = gamepad1.dpad_up;
            button_dd = gamepad1.dpad_down;
            button_dl = gamepad1.dpad_left;
            button_dr = gamepad1.dpad_right;
            bumper_left = gamepad1.left_bumper;
            bumper_right = gamepad1.right_bumper;
            if(bumper_left) {
                robot.lift.setPower(0.9);
            } else {
                robot.lift.setPower(0);
            }
            if(bumper_right) {
                robot.lift.setPower(-0.6);
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
            if(button_dr) {
                cposition += CINCREMENT;
            } else if(button_dl) {
                cposition -= CINCREMENT;
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
