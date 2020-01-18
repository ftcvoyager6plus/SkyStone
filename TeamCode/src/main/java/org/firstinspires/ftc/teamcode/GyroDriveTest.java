package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="GyroDriveTest")
public class GyroDriveTest extends LinearOpMode {
    Driving driver = new Driving(this);

    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        driver.resetEncoders();
        waitForStart();
        driver.gyroDrive(0.6, 96);
    }
}
