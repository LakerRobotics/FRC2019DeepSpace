// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5053.FRC2019DeepSpace;

import org.usfirst.frc5053.FRC2019DeepSpace.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc5053.FRC2019DeepSpace.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton driverOpenClaw;
    public JoystickButton driverCloseClaw;
    public Joystick xbox360;
    public JoystickButton operatorEnableCowboy;
    public Joystick logitech;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

        /**********************************
     * Create a static variable to store the last pressed button on the
     * LabView customized dashboard.
     **********************************/
    public static final int INITIAL_AND_ERROR_BUTTON = 9999;
    public static final int UNPROGRAMMED_ARM_PARAMETER = 0;
    public static final int ARM_PARAMETERS_SHOULDER_INDEX = 0;
    public static final int ARM_PARAMETERS_WRIST_INDEX = 1;
    public static final int ARM_PARAMETERS_RIGHT_DISTANCE_INDEX = 2;
    public static final int ARM_PARAMETERS_LEFT_DISTANCE_INDEX = 3;
    public static final int NUMBER_OF_ARM_POSITIONS = 15;

    public static int lastDashboardButton = INITIAL_AND_ERROR_BUTTON;      // No Button Pressed
    /**********************************
     * {shoulder, wrist, right distance, left distance}
     * NOTE that HATCH Rocket Low = HATCH Feeder = HATCH Cargo Ship
     **********************************/
    public double[][] operateArmParameters =
    {
        {0, 0, 0, 0},                  // 0: Not Used
        {671, 2421, 1, 2},             // 1: Cargo ==> Rocket High
        {1325, 2494, 3, 4},            // 2: Cargo ==> Rocket Middle
        {2083, 1980, 5, 6},            // 3: Cargo ==> Rocket Low
        {820, 2572, 7, 8},             // 4: Hatch ==> Rocket High
        {1650, 2142, 9, 10},           // 5: Hatch ==> Rocket Middle
        {2605, 1341, 11, 12},          // 6: Hatch ==> Rocket Low
        {2605, 1341, 13, 14},          // 7: Hatch ==> Cargo Ship
        {1632, 2321, 15, 16},          // 8: Cargo ==> Cargo Ship
        {2605, 1341, 17, 18},          // 9: Hatch ==> Feeder (Human Player)
        {1738, 2048, 19, 20},          // 10: Cargo ==> Feeder (Human Player)
        {2931, 500, 21, 22},           // 11: Hatch ==> Transport Position
        {2837, 1447, 23, 24},          // 12: Cargo ==> Ground Pickup
        {273, 1486, 25, 26},           // 13: Cargo ==> Rocket High (from back of Robot)
        {332,1130, 27, 29},            // 14: Hatch ==> Rocket High (from back of Robot)
        {2319, 2555, 30, 31}           // 15: CLIMB ==> Use arm to raise front wheels to platform
    };
    public String[] operateArmNames =
    {
        "Not Used",                     // 0: Not Used
        "CARGO : Rocket High",          // 1: Cargo ==> Rocket High
        "CARGO : Rocket Middle",        // 2: Cargo ==> Rocket Middle
        "CARGO : Rocket Low",           // 3: Cargo ==> Rocket Low
        "HATCH : Rocket High",          // 4: Hatch ==> Rocket High
        "HATCH : Rocket Middle",        // 5: Hatch ==> Rocket Middle
        "HATCH : Rocket Low",           // 6: Hatch ==> Rocket Low
        "HATCH : Cargo Ship",           // 7: Hatch ==> Cargo Ship
        "CARGO : Cargo Ship",           // 8: Cargo ==> Cargo Ship
        "HATCH : Human Player",         // 9: Hatch ==> Feeder (Human Player)
        "CARGO : Human Player",         // 10: Cargo ==> Feeder (Human Player)
        "HATCH : Transport",            // 11: Hatch ==> Transport Position
        "CARGO : Ground Pickup",        // 12: Cargo ==> Ground Pickup
        "CARGO : Rocket High Rear",     // 13: Cargo ==> Rocket High (from back of Robot)
        "HATCH : Rocket High Rear",     // 14: Hatch ==> Rocket High (from back of Robot)
        "CLIMB : Raise Front Wheels"    // 15: CLIMB ==> Use arm to raise front wheels to platform
    };


    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        logitech = new Joystick(1);
        
        operatorEnableCowboy = new JoystickButton(logitech, 1);
        operatorEnableCowboy.whileHeld(new armGroupCowboy());
        xbox360 = new Joystick(0);
        
        driverCloseClaw = new JoystickButton(xbox360, 5);
        driverCloseClaw.whenPressed(new clawOperationTimedClose(0));
        driverOpenClaw = new JoystickButton(xbox360, 6);
        driverOpenClaw.whenPressed(new clawOpenAndEjectCargo());


        // SmartDashboard Buttons
        SmartDashboard.putData("intakeIn", new intakeIn());
        SmartDashboard.putData("intakeOut", new intakeOut());
        SmartDashboard.putData("clawOpenAndEjectCargo", new clawOpenAndEjectCargo());
        SmartDashboard.putData("clawOperationTimedClose", new clawOperationTimedClose());
        SmartDashboard.putData("intakeLiftUpTimed: default", new intakeLiftUpTimed(2));
        SmartDashboard.putData("intakeLiftDownTimed: default", new intakeLiftDownTimed(2));
        SmartDashboard.putData("labViewInterface", new labViewInterface());
        SmartDashboard.putData("climbLift", new climbLift());
        SmartDashboard.putData("climbLower", new climbLower());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    for (int i=1; i <= NUMBER_OF_ARM_POSITIONS; i++)
    {
        SmartDashboard.putData(operateArmNames[i], 
                               new operateArm(operateArmParameters[i] [ARM_PARAMETERS_SHOULDER_INDEX],
                                              operateArmParameters[i] [ARM_PARAMETERS_WRIST_INDEX],
                                              operateArmParameters[i] [ARM_PARAMETERS_RIGHT_DISTANCE_INDEX],
                                              operateArmParameters[i] [ARM_PARAMETERS_LEFT_DISTANCE_INDEX]));
    }
    
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getxbox360() {
        return xbox360;
    }

    public Joystick getlogitech() {
        return logitech;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

