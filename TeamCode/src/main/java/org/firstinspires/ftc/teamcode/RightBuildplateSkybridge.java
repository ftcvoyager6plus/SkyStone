package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name="Right: Buildplate Skybridge", group="Autonomous")
public class RightBuildplateSkybridge extends LinearOpMode {
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
        driver.drive(0.3,-16);
        driver.backServosUp();
        //driver.turn(0.3,-10);
        driver.strafe(0.3,-48);
        driver.drive(0.3,15);
    }
}
