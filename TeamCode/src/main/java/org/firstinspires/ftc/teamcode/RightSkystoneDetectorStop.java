package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous(name="Right: Skystone Stopper")
@Disabled
public class RightSkystoneDetectorStop extends LinearOpMode {
    Driving driver = new Driving(this);
    private boolean targetVisible = false;
    private OpenGLMatrix lastLocation = null;
    Orientation rotation = null;
    VectorF translation = null;
    double timing;
    double distance = -40;
    @Override
    public void runOpMode() {
        driver.initHwMap();
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        telemetry.addData("Status", "Ready");
        telemetry.update();
        driver.resetEncoders();
        waitForStart();
        driver.strafe(0.3, -23);
        timing = driver.detectorDriveStop(0.2, -60);
        telemetry.addData("Timing", timing);
        sleep(10000);
    }
}
