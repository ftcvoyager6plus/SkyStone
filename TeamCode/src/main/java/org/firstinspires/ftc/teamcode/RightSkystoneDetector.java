package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name="Right: Skystone Detector")
public class RightSkystoneDetector extends LinearOpMode {
    Driving driver = new Driving(this);
    private boolean targetVisible = false;
    private OpenGLMatrix lastLocation = null;
    Orientation rotation = null;
    VectorF translation = null;
    int position = 1;
    double distance = -40;
    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        driver.resetEncoders();
        telemetry.addData("Status", "Ready");
        telemetry.update();
        waitForStart();
        driver.strafe(0.3, -23);
        position = driver.detectorDriveRight(0.2, -60);
        telemetry.addData("Position", position);
        telemetry.update();
        driver.drive(0.2, 8);
        driver.strafe(0.2, -6.75);
        driver.skystoneDown();
        driver.strafe(0.2, 8);
        if(position == 1) {
            distance = -40;
        } if(position == 2) {
            distance = -48;
        } if(position == 3) {
            distance = -56; // add 24 on the way back
        }
        driver.drive(0.4, -distance);
        driver.skystoneUp();
        sleep(50);
        distance = -distance + 24; //back same plus 24
        if(position != 3) {
            driver.drive(0.5, -distance);
            //move this out
            driver.strafe(0.2, -10);
            driver.skystoneDown();
            driver.strafe(0.2, 9);
            driver.drive(0.5, distance+2);
            driver.skystoneUp();
            driver.southwest(1, -24);
        } else {
            driver.drive(0.4, -39);
            driver.strafe(0.2, -8);
            driver.skystoneDown();
            driver.strafe(0.2, 8);
            driver.drive(0.7, 41);
            driver.skystoneUp();
            driver.southwest(1, -24);
        }
        // First position: 44
        // Second position: 52
        // Third position: 60
    }
}
