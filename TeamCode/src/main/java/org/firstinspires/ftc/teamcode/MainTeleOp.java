package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class MainTeleOp extends LinearOpMode {
    private Robot robot;
    private float speed;

    private ElapsedTime servoToggleDelay;

    @SuppressWarnings("RedundantThrows")
    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        telemetry.addData("Status", "Running");
        telemetry.update();

        while (opModeIsActive()) {
            driveWheels();
            toggleGasLightServo();
            toggleLeftFlipper();
            toggleRightFlipper();
        }
    }

    private void initialize() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        robot = new Robot(hardwareMap);

        gamepad1.setJoystickDeadzone(.1f);
        speed = .25f;
        servoToggleDelay = new ElapsedTime();

        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    // ************* WHEEL METHODS ************* //
    private void driveWheels() {
        driveFourWheel();
        changeSpeed();
    }

    private void driveFourWheel() {
        robot.wheelTopLeft.setPower(-gamepad1.left_stick_y * speed);
        robot.wheelBotLeft.setPower(-gamepad1.left_stick_y * speed);

        robot.wheelTopRight.setPower(-gamepad1.right_stick_y * speed);
        robot.wheelBotRight.setPower(-gamepad1.right_stick_y * speed);
    }

    @SuppressWarnings("unused")
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

    private void changeSpeed() {
        if (gamepad1.dpad_up) {
            speed = 1;
        } else if (gamepad1.dpad_left || gamepad1.dpad_right) {
            speed = .5f;
        } else if (gamepad1.dpad_down) {
            speed = .25f;
        }
    }


    // ************* SERVO METHODS ************* //
    private void toggleGasLightServo() {
        if (gamepad1.y && servoToggleDelay.seconds() > .25f) {
            if (robot.servoGasLight.getPosition() == Robot.SERVO_POS_GAS_LIGHT_RETRACT) {
                robot.servoGasLight.setPosition(Robot.SERVO_POS_GAS_LIGHT_EXTEND);
            } else {
                robot.servoGasLight.setPosition(Robot.SERVO_POS_GAS_LIGHT_RETRACT);
            }
            servoToggleDelay.reset();
        }
    }

    private void toggleLeftFlipper() {
        if (gamepad1.a && servoToggleDelay.seconds() > .25f) {
            if (robot.servoLeftFlipper.getPosition() == Robot.SERVO_POS_LEFT_FLIPPER_RETRACT) {
                robot.servoLeftFlipper.setPosition(Robot.SERVO_POS_LEFT_FLIPPER_EXTEND);
            } else {
                robot.servoLeftFlipper.setPosition(Robot.SERVO_POS_LEFT_FLIPPER_RETRACT);
            }
            servoToggleDelay.reset();
        }
    }

    private void toggleRightFlipper() {
        if (gamepad1.b && servoToggleDelay.seconds() > .25f) {
            if (robot.servoRightFlipper.getPosition() == Robot.SERVO_POS_RIGHT_FLIPPER_RETRACT) {
                robot.servoRightFlipper.setPosition(Robot.SERVO_POS_RIGHT_FLIPPER_EXTEND);
            } else {
                robot.servoRightFlipper.setPosition(Robot.SERVO_POS_RIGHT_FLIPPER_RETRACT);
            }
            servoToggleDelay.reset();
        }
    }
}
