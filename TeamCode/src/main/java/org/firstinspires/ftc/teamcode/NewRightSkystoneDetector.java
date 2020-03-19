package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="Right: Skystone Detector")
public class NewRightSkystoneDetector extends LinearOpMode {
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
                new Path(M.STRAFE, D.FORWARD, 0.5, 6),
                new Path(M.DRIVE, D.BACKWARD, 0.6, 65),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 13),
                new Path(M.BACK_HALF, D.N, 0, 0),
                new Path(M.GRIPPER_IN, D.N, 0, 0),
                new Path(M.STRAFE, D.FORWARD, 0.4, 10),
                new Path(M.DRIVE, D.FORWARD, 0.6, 92),
                new Path(M.STRAFE, D.BACKWARD, 0.5, 5),
                new Path(M.SKYSTONE_DOWN_GRIPPER_OUT, D.N, 0, 0),
                new Path(M.SKYSTONE_POS, D.N, 0, 0.3),
                new Path(M.STRAFE, D.FORWARD, 0.4, 8),
                new Path(M.DRIVE, D.BACKWARD, 0.5, 92),
                new Path(M.STRAFE, D.BACKWARD, 0.4, 10),
                new Path(M.GRIPPER_IN, D.N, 0, 0)

        };
        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();
        //driver.strafeTillLimit(-0.4, -8);
        driver.strafe(-0.5, -24);
        sleep(100);
        position = driver.getSkystonePosition("left");
        telemetry.addData("Position", position);
        telemetry.update();
        if(position == 1) {
            driver.parseMoves(pos_1_paths);
        }
    }
}
