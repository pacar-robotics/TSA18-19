package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class TestServo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo leftFlipper = hardwareMap.get(Servo.class, "servoLeftFlipper");
        ElapsedTime elapsedTime = new ElapsedTime();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.dpad_up && elapsedTime.seconds() > .5f) {
                leftFlipper.setPosition(leftFlipper.getPosition() + .01f);
                elapsedTime.reset();
            } else if (gamepad1.dpad_down && elapsedTime.seconds() > .5f) {
                leftFlipper.setPosition(leftFlipper.getPosition() - .01f);
                elapsedTime.reset();
            }

            telemetry.addData("Position", leftFlipper.getPosition());
            telemetry.update();
        }
    }
}
