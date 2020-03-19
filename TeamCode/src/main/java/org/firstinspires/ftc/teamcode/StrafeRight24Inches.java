package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Strafe Right 6 Inches")
public class StrafeRight24Inches extends LinearOpMode {
    NewDriving driver = new NewDriving(this);
    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        driver.resetEncoders();
        driver.startAngles();
        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();
        Path[] paths = {
                new Path(M.STRAFE, D.FORWARD, 0.3, 6)
        };
        driver.parseMoves(paths);
    }
}
