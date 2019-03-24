package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    DcMotor wheelTopLeft;
    DcMotor wheelTopRight;
    DcMotor wheelBotLeft;
    DcMotor wheelBotRight;

    Robot(HardwareMap hardwareMap) {
        wheelTopLeft = hardwareMap.get(DcMotor.class, "wheelTopLeft");
        wheelTopRight = hardwareMap.get(DcMotor.class, "wheelTopRight");
        wheelBotLeft = hardwareMap.get(DcMotor.class, "wheelBotLeft");
        wheelBotRight = hardwareMap.get(DcMotor.class, "wheelBotRight");

        wheelTopLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        wheelBotLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
