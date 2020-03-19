package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Left: Skystone Detector")
public class LeftSkystoneDetector extends LinearOpMode {
    NewDriving driver = new NewDriving(this);
    int position;

    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        driver.resetEncoders();
        Path[] pos_1_paths = {
                new Path(M.DRIVE, D.BACKWARD, 0.25, 2),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.3),
                new Path(M.STRAFE, D.FORWARD, 0.4, 5),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 36),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.4, 61),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 5),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.STRAFE, D.FORWARD, 0.4, 5),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 61),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.4, 9),
                new Path(M.STRAFE, D.BACKWARD, 0.3, 9)

        };
        Path[] pos_2_paths = {
                new Path(M.DRIVE, D.FORWARD, 0.25, 2),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.3),
                new Path(M.STRAFE, D.FORWARD, 0.4, 5),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 44),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.4, 68),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 7),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.STRAFE, D.FORWARD, 0.4, 7),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 68),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.4, 9),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 12)
        };
        Path[] pos_3_paths = {
                new Path(M.DRIVE, D.FORWARD, 0.4, 7.5),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.STRAFE, D.FORWARD, 0.4, 5),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 52),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.4, 36),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 7),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.2),
                new Path(M.STRAFE, D.FORWARD, 0.4, 7),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 36),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.4, 10),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 12)
        };
        driver.startAngles();
        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();
        driver.skystonePos(0.2);
        //driver.strafeTillLimit(-0.4, -8);
        driver.gyrostrafe(-0.4, -26);
        sleep(100);
        position = driver.getSkystonePosition("left");
        telemetry.addData("Position", position);
        telemetry.update();
        if(position == 1) {
            driver.parseMoves(pos_1_paths);
        } else if(position == 2) {
            driver.parseMoves(pos_2_paths);
        } else if(position == 3) {
            driver.parseMoves(pos_3_paths);
        }
    }
}
