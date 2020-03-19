package org.firstinspires.ftc.teamcode;

import android.media.MediaPlayer;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="Teleop 2P", group="Linear Opmode")
public class Teleop2P extends LinearOpMode {
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

    private static final double BINCREMENT = 0.05;
    private static final double BBMAX_POS = 0.65;
    private static final double BBMIN_POS = 0.0;
    private static final double BMAX_POS = 1;
    private static final double BMIN_POS = 0.35;
    private double bposition = BMAX_POS;
    private double bbposition = BBMIN_POS;

    private static final double CINCREMENT = 0.06;
    private static final double CMAX_POS = 1.0;
    private static final double CMIN_POS = 0.0;
    private double cposition = (0.4);

    private static final double DINCREMENT = 0.03;
    private static final double DMAX_POS = 1.0;
    private static final double DMIN_POS = 0.0;
    private double dposition = (DMAX_POS - DMIN_POS) / 2;
    private boolean halfspeed = false;
    private RevBlinkinLedDriver.BlinkinPattern activePattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_RAINBOW_PALETTE;
    private RevBlinkinLedDriver.BlinkinPattern passivePattern = RevBlinkinLedDriver.BlinkinPattern.RAINBOW_FOREST_PALETTE;
    //private boolean frontIsSkystone = false;
    //private boolean backIsSkystone = false;
    int front_red, front_green, front_blue, back_red, back_green, back_blue;
    VoyagerBot robot = new VoyagerBot();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.resetEncoders();
        robot.color_front.enableLed(false);
        robot.color_back.enableLed(false);
        telemetry.addData("Status", "Ready");
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
            if((drive == 0) && (strafe == 0) && (rotate == 0)) {
                robot.underglow.setPattern(passivePattern);
            } else {
                robot.underglow.setPattern(activePattern);
            }
            if(gamepad1.right_stick_button) {
                rotate = 0.5 * rotate;
                drive = 0.5 * drive;
                strafe = 0.5 * strafe;
            }
            if(bumper_left && (robot.lift.getCurrentPosition() < -500)) { //down
                robot.lift.setPower(1);
            } else if(robot.lift.getCurrentPosition() >= -500) {
                robot.lift.setPower(0);
            }
            if(bumper_right && (robot.lift.getCurrentPosition() > -13300)) { //up
                robot.lift.setPower(-1);
            } else if(robot.lift.getCurrentPosition() <= -13300) {
                robot.lift.setPower(0);
            }
            if(!bumper_left && !bumper_right) {
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
                bbposition -= BINCREMENT;
                if(bposition >= BMAX_POS) {
                    bposition = BMAX_POS;
                }
                if (bbposition <= BBMIN_POS) {
                    bbposition = BBMIN_POS;
                }
            } else if(button_du) {
                bposition -= BINCREMENT;
                bbposition += BINCREMENT;
                if (bposition <= BMIN_POS) {
                    bposition = BMIN_POS;
                }
                if (bbposition >= BBMAX_POS) {
                    bbposition = BBMAX_POS;
                }
            }
            if(gamepad2.dpad_up) {
                robot.extension.setPower(-0.5);
            }
            if(gamepad2.dpad_down) {
                robot.extension.setPower(0.5);
            }
            if(!gamepad2.dpad_up && !gamepad2.dpad_down) {
                robot.extension.setPower(0);
            }
            if(gamepad1.y) {
                robot.yeeter.setPower(0.5);
            }
            if(gamepad1.x) {
                SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, R.raw.yeet);
                passivePattern = RevBlinkinLedDriver.BlinkinPattern.BREATH_RED;
                robot.yeeter.setPower(-0.7);
            }
            if(!gamepad1.y && !gamepad1.x) {
                robot.yeeter.setPower(0);
            }
            if(gamepad1.b) {
                dposition += DINCREMENT;
                if(dposition >= DMAX_POS) {
                    dposition = DMAX_POS;
                }
            } else if(gamepad1.a) {
                dposition -= DINCREMENT;
                if (dposition <= DMIN_POS) {
                    dposition = DMIN_POS;
                }
            }
            /*front_red = robot.color_front.red();
            front_green = robot.color_front.green();
            front_blue = robot.color_front.blue();
            back_red = robot.color_back.red();
            back_green = robot.color_back.green();
            back_blue = robot.color_back.blue();
            frontIsSkystone = (front_red + front_green) / front_blue < 2.5;
            backIsSkystone = (back_red + back_green) / back_blue < 2.5;*/
            /*telemetry.addData("Front Skystone", frontIsSkystone ? "yes" : "no");
            telemetry.addData("Back Skystone", backIsSkystone ? "yes": "no");
            telemetry.update();*/
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
            robot.back2.setPosition(bbposition);
            robot.claw.setPosition(position);
            robot.skystone.setPosition(cposition);
            robot.gripper.setPosition(dposition);
            sleep(CYCLE_MS);
        }
    }
}
