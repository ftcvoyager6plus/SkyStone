package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.io.FileOutputStream;
import java.io.IOException;


@TeleOp(name="Skystone Detection Demo", group="Demo")
public class Skystone extends LinearOpMode {
    private static final String VUFORIA_KEY = "Admc99H/////AAABmaxDjBlaGUq0qVJSjAQ7l/wZn5JuZKVqMNSwSlTe0tQVVO3wVcoREgVPLXxHIUWLYUoXMmnkUDaL6ROYlzVZO4hfPJ2Res2HwV7Qz3rAMtwgtH6KGLzPB537sF3pkgtBKO0BLl55+ETvKByf94oRiejdKgurUIartflh38W8bbHjjW61+TrYCJkWAT7HYmR5yVqCmHs32Rk2ZcBUOvQ40ldPQdKN2Nymn8pIkvkSmPO0Rl3xDagABQ6WN/I2mgaxexKZTEaxEz6to15ggXr8ORUcZSMomXDArR66y7Yaj3cQeDsjpeXWyZ2Se5Vc9UWuBhwe80rLHQWYaHP5nA616H6gm0gQSrOTBrP6O4Cg0pf6";
    private Image rgb = null;
    private VuforiaLocalizer vuforia;
    @Override
    public void runOpMode() {
        initVuforia();
        telemetry.addData(">", "Press Play to start opmode");
        telemetry.update();
        waitForStart();
        if(opModeIsActive()) {
            if(rgb != null) {
                Bitmap save = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
                save.copyPixelsFromBuffer(rgb.getPixels());
                try(FileOutputStream out = new FileOutputStream("/sdcard/bruh.jpg")) {
                    save.compress(Bitmap.CompressFormat.JPEG, 100, out);
                } catch (IOException e) {

                }
            }
            while(opModeIsActive()) {
                if(rgb != null) {
                    Bitmap bn = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
                    bn.copyPixelsFromBuffer(rgb.getPixels());

                }
            }
        }
    }
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        vuforia.setFrameQueueCapacity(1);
        try {
            VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
            long numImages = frame.getNumImages();
            for(int i=0; i < numImages; i++) {
                if(frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                    rgb = frame.getImage(i);
                    break;
                }
            }

        } catch(InterruptedException e) {

        }
    }
}
