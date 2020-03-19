package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Right: Skystone Detector")
public class RightSkystoneDetector extends LinearOpMode {
    NewDriving driver = new NewDriving(this);
    int position;

    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        driver.resetEncoders();
        Path[] pos_1_paths = {
                new Path(M.DRIVE, D.FORWARD, 0.25, 1),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.STRAFE, D.FORWARD, 0.4, 5),
                new Path(M.DRIVE, D.FORWARD, 0.4, 40),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 65),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 6),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.15),
                new Path(M.STRAFE, D.FORWARD, 0.4, 6),
                new Path(M.DRIVE, D.FORWARD, 0.4, 60),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 10),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.15),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 12),
        };
        Path[] pos_2_paths = {
                new Path(M.DRIVE, D.BACKWARD, 0.25, 3),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.STRAFE, D.FORWARD, 0.4, 5),
                new Path(M.DRIVE, D.FORWARD, 0.4, 48),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 73),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 7),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.15),
                new Path(M.STRAFE, D.FORWARD, 0.4, 7),
                new Path(M.DRIVE, D.FORWARD, 0.4, 73),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 15),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.15),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 12),
        };
        Path[] pos_3_paths = {
                new Path(M.DRIVE, D.BACKWARD, 0.25, 12),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.175),
                new Path(M.STRAFE, D.FORWARD, 0.4, 5),
                new Path(M.DRIVE, D.FORWARD, 0.4, 55),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 39),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 7),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.15),
                new Path(M.STRAFE, D.FORWARD, 0.4, 7),
                new Path(M.DRIVE, D.FORWARD, 0.4, 36),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 10),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.15),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 12)
        };
        driver.startAngles();
        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();
        driver.skystonePos(0.2);
        driver.gyrostrafe(-0.4, -26);
        sleep(100);
        position = driver.getSkystonePosition("right");
        if(position == 1) {
            driver.parseMoves(pos_1_paths);
        } else if(position == 2) {
            driver.parseMoves(pos_2_paths);
        } else if(position == 3) {
            driver.parseMoves(pos_3_paths);
        }
    }
}
