/*
 * Copyright (c) 2017 Vines High School Robotics Team
 *
 *                            Permission is hereby granted, free of charge, to any person obtaining a copy
 *                            of this software and associated documentation files (the "Software"), to deal
 *                            in the Software without restriction, including without limitation the rights
 *                            to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *                            copies of the Software, and to permit persons to whom the Software is
 *                            furnished to do so, subject to the following conditions:
 *
 *                            The above copyright notice and this permission notice shall be included in all
 *                            copies or substantial portions of the Software.
 *
 *                            THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *                            IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *                            FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *                            AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *                            LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *                            OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *                            SOFTWARE.
 */
package org.vinesrobotics.sixteen.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file provides basic Telop driving for Vibøt Mark 1 robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common HardwareAlvesVibot hardware class to define the devices on the robot.
 * All device access is managed through the HardwareAlvesVibot class.
 *
 *
 */

@TeleOp(name="AlvesTeleOpTank2", group="Vines")
//@Disabled
public class AlvesTeleOpTank extends OpMode{

    /* Declare OpMode members. */
    HardwareAlvesVibot robot       = new HardwareAlvesVibot(); // use the class created to define a Vibot's hardware



    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Mr. Alves!");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    //@Override
    //public void init_loop() {
    //}

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    //@Override
    //public void start() {
   // }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    //@Override
    public void loop() {
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        right = gamepad1.left_stick_y;
        left = gamepad1.right_stick_y;
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0)
        {
            left /= max;
            right /= max;
        }

        robot.leftMotor1.setPower(left);
        robot.rightMotor1.setPower(right);
        telemetry.addData("Right Stick:", right);
        telemetry.addData("Left Stick:", left);




        // Use gamepad right bumpers and tigger spin the intake system
        if (gamepad1.right_bumper) //out
            robot.intake_motor.setPower(1);
        else if (gamepad1.right_trigger>0) //in
            robot.intake_motor.setPower(-1);
        else
            robot.intake_motor.setPower(0.0);

        // Use gamepad left bumper and trigger  to launch
        if (gamepad1.left_bumper) //down
            robot.launch_motor.setPower(robot.Launcher_Down_Max);
        else if (gamepad1.left_trigger>0) //up
            robot.launch_motor.setPower(1);
        else
            robot.launch_motor.setPower(0.0);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
