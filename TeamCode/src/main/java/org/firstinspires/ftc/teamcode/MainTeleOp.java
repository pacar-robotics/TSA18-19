package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        telemetry.addData("Status", "Running");
        telemetry.update();

        while (opModeIsActive()) {
            driveWheels();
        }
    }

    private void initialize() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        robot = new Robot(hardwareMap);

        gamepad1.setJoystickDeadzone(.1f);

        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    private void driveWheels() {
        double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;

        robot.wheelTopLeft.setPower(r * Math.cos(robotAngle) + rightX);
        robot.wheelTopRight.setPower(r * Math.sin(robotAngle) - rightX);
        robot.wheelBotLeft.setPower(r * Math.sin(robotAngle) + rightX);
        robot.wheelBotRight.setPower(r * Math.cos(robotAngle) - rightX);
    }
}
