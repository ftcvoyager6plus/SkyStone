package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@TeleOp(name="Skystone Detection Demo", group="Demo")
public class Skystone extends LinearOpMode {
    private static final String MODEL_FILE = "skystone.tflite";
    private static final String LABEL_FIRST = "Stone";
    private static final String LABEL_SECOND = "Skystone";
    private static final String VUFORIA_KEY = "Admc99H/////AAABmaxDjBlaGUq0qVJSjAQ7l/wZn5JuZKVqMNSwSlTe0tQVVO3wVcoREgVPLXxHIUWLYUoXMmnkUDaL6ROYlzVZO4hfPJ2Res2HwV7Qz3rAMtwgtH6KGLzPB537sF3pkgtBKO0BLl55+ETvKByf94oRiejdKgurUIartflh38W8bbHjjW61+TrYCJkWAT7HYmR5yVqCmHs32Rk2ZcBUOvQ40ldPQdKN2Nymn8pIkvkSmPO0Rl3xDagABQ6WN/I2mgaxexKZTEaxEz6to15ggXr8ORUcZSMomXDArR66y7Yaj3cQeDsjpeXWyZ2Se5Vc9UWuBhwe80rLHQWYaHP5nA616H6gm0gQSrOTBrP6O4Cg0pf6";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;
    @Override
    public void runOpMode() {
        initVuforia();
        if(ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        if(tfod != null) {
            tfod.activate();
        }
        telemetry.addData(">", "Press Play to start opmode");
        telemetry.update();
        waitForStart();
        if(opModeIsActive()) {
            while(opModeIsActive()) {
                if(tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if(updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int i = 0;
                        for(Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            //telemetry.addData(String.format("  left,top (%d)", i), "%.03f", "%.03f", recognition.getLeft(), recognition.getTop());
                            //telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f", "%.03f", recognition.getRight(), recognition.getBottom());
                        }
                        telemetry.update();
                    }
                }
            }
        }
        if(tfod != null) {
            tfod.shutdown();
        }
    }
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromFile("/sdcard/skystone.tflite", LABEL_FIRST, LABEL_SECOND);
    }
}
