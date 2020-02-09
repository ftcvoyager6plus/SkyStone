package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Left: Buildplate Wall")
public class NewLeftBuildplateWall extends LinearOpMode {
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
                new Path(M.STRAFE, D.FORWARD, 0.5, 9),
                new Path(M.DRIVE, D.BACKWARD, 0.4, 9.5),
                new Path(M.BACK_DOWN, D.N, 0, 0),
                new Path(M.ROTATE, D.FORWARD, 0.55, 180),
                new Path(M.DRIVE, D.BACKWARD, 0.5, 20),
                new Path(M.BACK_UP, D.N, 0, 0),
                new Path(M.STRAFE, D.FORWARD,0.7, 33),
                new Path(M.DRIVE, D.BACKWARD, 0.5, 25),
                new Path(M.STRAFE, D.FORWARD, 0.5, 18)
        };
        driver.parseMoves(paths);
    }
}
