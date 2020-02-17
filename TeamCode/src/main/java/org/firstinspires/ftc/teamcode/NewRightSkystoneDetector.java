package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="(new) Right Skystone Detector")
public class NewRightSkystoneDetector extends LinearOpMode {
    NewDriving driver = new NewDriving(this);
    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        driver.resetEncoders();
        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();
        Path[] pos_1_paths = {
                //new Path(M.STRAFE_TILL, D.BACKWARD, 0.25, 5)
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.4),
                new Path(M.STRAFE, D.FORWARD, 0.5, 6),
                new Path(M.DRIVE, D.BACKWARD, 0.5, 68),
                new Path(M.STRAFE, D.BACKWARD, 0.5, 13),
                new Path(M.BACK_HALF, D.N, 0, 0),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.STRAFE, D.FORWARD, 0.5, 5),
                new Path(M.ROTATE, D.FORWARD, 0.5, -80),
                new Path(M.DRIVE, D.BACKWARD, 0.5, 8),
                new Path(M.BACK_DOWN, D.N, 0, 0),
                new Path(M.ROTATE, D.FORWARD, 0.75, 90),
                new Path(M.DRIVE, D.BACKWARD, 0.5, 25),
                new Path(M.BACK_UP, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.5, 5),
                new Path(M.STRAFE, D.FORWARD, 0.6, 48)
        };
        driver.parseMoves(pos_1_paths);
    }
}
