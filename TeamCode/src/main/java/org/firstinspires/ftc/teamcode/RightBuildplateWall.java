package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="Right: Buildplate Wall", group="Autonomous")
public class RightBuildplateWall extends LinearOpMode {
    Driving driver = new Driving(this);
    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        driver.resetEncoders();
        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();
        driver.drive(0.3, -20);
        driver.strafe(0.3, -9);
        driver.drive(0.2, -10.5);
        driver.backServosDown();
        driver.gyroturn(-0.35,-180);
        driver.drive(0.3,-21);
        driver.backServosUp();
        // remove past here
        driver.strafe(0.3, -33);
        driver.drive(0.3, -25);
        driver.strafe(0.3, -18);
    }
}
