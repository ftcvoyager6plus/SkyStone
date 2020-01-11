package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class Detecting {
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = VuforiaLocalizer.CameraDirection.BACK;
    private static final boolean PHONE_IS_PORTRAIT = false;
    private static final String VUFORIA_KEY = "Admc99H/////AAABmaxDjBlaGUq0qVJSjAQ7l/wZn5JuZKVqMNSwSlTe0tQVVO3wVcoREgVPLXxHIUWLYUoXMmnkUDaL6ROYlzVZO4hfPJ2Res2HwV7Qz3rAMtwgtH6KGLzPB537sF3pkgtBKO0BLl55+ETvKByf94oRiejdKgurUIartflh38W8bbHjjW61+TrYCJkWAT7HYmR5yVqCmHs32Rk2ZcBUOvQ40ldPQdKN2Nymn8pIkvkSmPO0Rl3xDagABQ6WN/I2mgaxexKZTEaxEz6to15ggXr8ORUcZSMomXDArR66y7Yaj3cQeDsjpeXWyZ2Se5Vc9UWuBhwe80rLHQWYaHP5nA616H6gm0gQSrOTBrP6O4Cg0pf6";
    private static final float mmPerInch = 25.4f;
    private static final float mmTargetHeight = (6) * mmPerInch;

    private static final float stoneZ = 2.00f * mmPerInch;

    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;
    private static final float bridgeRotX = 180;

    private static final float halfField = 72 * mmPerInch;
    private static final float quadField = 36 * mmPerInch;

    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate = 0;
    private float phoneYRotate = 0;
    private float phoneZRotate = 0;
}
