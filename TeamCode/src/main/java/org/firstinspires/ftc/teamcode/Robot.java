package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Robot {
    DcMotor wheelTopLeft;
    DcMotor wheelTopRight;
    DcMotor wheelBotLeft;
    DcMotor wheelBotRight;

    Servo servoLeftFlipper;
    Servo servoRightFlipper;
    Servo servoGasLight;

    Robot(HardwareMap hardwareMap) {
        initializeMotors(hardwareMap);
        initializeServos(hardwareMap);
    }

    private void initializeMotors(HardwareMap hardwareMap) {
        wheelTopLeft = hardwareMap.get(DcMotor.class, "wheelTopLeft");
        wheelTopRight = hardwareMap.get(DcMotor.class, "wheelTopRight");
        wheelBotLeft = hardwareMap.get(DcMotor.class, "wheelBotLeft");
        wheelBotRight = hardwareMap.get(DcMotor.class, "wheelBotRight");

        wheelTopLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        wheelBotLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void initializeServos(HardwareMap hardwareMap) {
        servoLeftFlipper = hardwareMap.get(Servo.class, "servoLeftFlipper");
        servoRightFlipper = hardwareMap.get(Servo.class, "servoRightFlipper");
        servoGasLight = hardwareMap.get(Servo.class, "servoGasLight");
    }


    // *************** Constants *************** //
    // Servo positions
    final static float SERVO_POS_GAS_LIGHT_EXTEND = .4f;
    final static float SERVO_POS_GAS_LIGHT_RETRACT = .94f;

    final static float SERVO_POS_LEFT_FLIPPER_EXTEND = 1;
    final static float SERVO_POS_LEFT_FLIPPER_RETRACT = 0;

    final static float SERVO_POS_RIGHT_FLIPPER_EXTEND = .93f;
    final static float SERVO_POS_RIGHT_FLIPPER_RETRACT = 0;
}
