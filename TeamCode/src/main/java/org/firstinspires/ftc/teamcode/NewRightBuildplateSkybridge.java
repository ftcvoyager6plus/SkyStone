package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Right: Buildplate Skybridge")
public class NewRightBuildplateSkybridge extends LinearOpMode {
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
        Path[] paths = {
                new Path(M.DRIVE, D.BACKWARD, 0.5, 20),
                new Path(M.STRAFE, D.BACKWARD, 0.5, 9),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 5),
                new Path(M.BACK_DOWN, D.N, 0, 0),
                new Path(M.ROTATE, D.BACKWARD, 0.55, 180),
                new Path(M.DRIVE, D.BACKWARD, 0.5, 21),
                new Path(M.BACK_UP, D.N, 0, 0),
                new Path(M.DRIVE, D.FORWARD, 0.3, 5),
                new Path(M.SKYSTONE_DOWN, D.N, 0, 0),
                new Path(M.STRAFE, D.BACKWARD, 0.5, 45),
                new Path(M.DRIVE, D.FORWARD, 0.5, 15)
        };
        driver.parseMoves(paths);
    }
}
