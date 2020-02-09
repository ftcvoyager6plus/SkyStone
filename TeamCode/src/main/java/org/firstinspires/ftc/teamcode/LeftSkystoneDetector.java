package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name="Left: Skystone Detector")
public class LeftSkystoneDetector extends LinearOpMode {
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
        position = driver.detectorDrive(0.2, 60);
        telemetry.addData("Position", position);
        telemetry.update();
        driver.drive(0.2, 7);
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
        driver.drive(0.4, distance);
        driver.skystoneUp();
        sleep(50);
        distance = -distance + 24; //back same plus 24
        if(position != 3) {
            driver.drive(0.5, distance);
            //move this out
            driver.strafe(0.2, -11);
            driver.skystoneDown();
            driver.strafe(0.2, 11);
            driver.drive(0.5, -(distance+4));
            driver.skystoneUp();
            //driver.northeast(1, 24);
            driver.drive(1, 15);
        } else {
            driver.drive(0.4, 39);
            driver.strafe(0.2, -11);
            driver.skystoneDown();
            driver.strafe(0.2, 10);
            driver.drive(0.6, -41);
            driver.skystoneUp();
            //driver.northeast(1, 24);
            driver.drive(1, 15);
        }
        // First position: 44
        // Second position: 52
        // Third position: 60
    }
}
