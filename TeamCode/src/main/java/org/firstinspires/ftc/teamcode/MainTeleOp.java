package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    private Robot robot;
    private float speed;

    @SuppressWarnings("RedundantThrows")
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

        speed = .25f;

        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    private void driveWheels() {
        driveFourWheel();
    }

    private void driveFourWheel() {
        robot.wheelTopLeft.setPower(-gamepad1.left_stick_y);
        robot.wheelBotLeft.setPower(-gamepad1.left_stick_y);

        robot.wheelTopRight.setPower(-gamepad1.right_stick_y);
        robot.wheelBotRight.setPower(-gamepad1.right_stick_y);
    }

    private void driveMecanum() {
        double r = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;

        final float TOP_LEFT = 1.570796f;
        final float TOP = 0.785398f;
        final float TOP_RIGHT = 0;
        final float RIGHT = -0.785398f;
        final float BOT_RIGHT = -1.570796f;
        final float BOT = -2.35619f;
        final float BOT_LEFT = -3.1415926f;
        final float LEFT = 2.35619f;

        if (robotAngle < TOP_LEFT && robotAngle > TOP_RIGHT) {
            robotAngle = TOP;
        } else if (robotAngle < TOP_RIGHT && robotAngle > BOT_RIGHT) {
            robotAngle = RIGHT;
        } else if (robotAngle < BOT_RIGHT && robotAngle > BOT_LEFT) {
            robotAngle = BOT;
        } else {
            robotAngle = LEFT;
        }

        robot.wheelTopLeft.setPower((r * Math.cos(robotAngle) + rightX) * speed);
        robot.wheelTopRight.setPower((r * Math.sin(robotAngle) - rightX) * speed);
        robot.wheelBotLeft.setPower((r * Math.sin(robotAngle) + rightX) * speed);
        robot.wheelBotRight.setPower((r * Math.cos(robotAngle) - rightX) * speed);
    }
}
