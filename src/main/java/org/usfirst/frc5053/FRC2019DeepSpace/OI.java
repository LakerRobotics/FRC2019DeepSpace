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
import edu.wpi.first.wpilibj.buttons.Button;
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
    public JoystickButton driveToPosition;
    public JoystickButton driveWithJoysticks;
    public Joystick xbox360;
    public JoystickButton operatorEnableCowboy;
    public JoystickButton wristCowboyDrive1;
    public JoystickButton rocketLow;
    public JoystickButton groundTransport;
    public JoystickButton humanPlayer;
    public JoystickButton cargoShip;
    public JoystickButton rocketHigh;
    public JoystickButton rocketMid;
    public JoystickButton closeClaw;
    public Joystick logitech;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton btnIntakeIn;

    /* LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL
       L Constants related to the game and the 
       L position of the robot and its arm.
       LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL */

    public static final int INITIAL_AND_ERROR_BUTTON = 9999;
    public static final int UNPROGRAMMED_ARM_PARAMETER = 0;

    public static final int ARM_PARAMETERS_SHOULDER_INDEX = 0;
    public static final int ARM_PARAMETERS_WRIST_INDEX = 1;
    public static final int ARM_PARAMETERS_RIGHT_DISTANCE_INDEX = 2;
    public static final int ARM_PARAMETERS_LEFT_DISTANCE_INDEX = 3;

    public static final int NUMBER_OF_ARM_POSITIONS = 15;
    public static final int NUMBER_OF_DELIVERY_POSITIONS = 7;

    // Game Pieces
    public static final int GAME_PIECE_HATCH = 0;
    public static final int GAME_PIECE_CARGO = 1;

    // Delivery Locations
    public static final int GROUND_AND_TRANSPORT = 1;
    public static final int HUMAN_PLAYER = 2;
    public static final int CARGO_SHIP = 3;
    public static final int ROCKET_LOW = 4;
    public static final int ROCKET_MID = 5;
    public static final int ROCKET_HIGH = 6;
    public static final int ROCKET_HIGH_BACK = 7;
    
    // Keep track of the operator's destination
    public static int lastDashboardButton = INITIAL_AND_ERROR_BUTTON;
    public static int SELECTED_GAME_PIECE = GAME_PIECE_HATCH;
    public static int SELECTED_DELIVERY_POSITION = CARGO_SHIP; 

    /**********************************
     * {shoulder, wrist, right distance, left distance}
     * NOTE that HATCH Rocket Low = HATCH Feeder = HATCH Cargo Ship
     **********************************/
    public double[][] operateArmParameters =
    {
        {   0,    0,  0.0,  0.0},       // 0: Not Used
        {2931,  500,  0.0,  0.0},       // 11: Hatch ==> Transport Position
        {2509, 1532, 18.5, 18.5},       // 9: Hatch ==> Feeder (Human Player)
        {2509, 1532, 18.5, 18.5},       // 7: Hatch ==> Cargo Ship
        {2509, 1532, 18.5, 18.5},       // 6: Hatch ==> Rocket Low
        {1650, 2142, 29.0, 29.0},       // 5: Hatch ==> Rocket Middle
        { 820, 2572, 10.4, 10.4},       // 4: Hatch ==> Rocket High
        { 332, 1130,  0.0,  0.0},       // 14: Hatch ==> Rocket High (from back of Robot)
        {2837, 1447,  0.0,  0.0},       // 12: Cargo ==> Ground Pickup
        {1738, 2048, 28.0, 28.0},       // 10: Cargo ==> Feeder (Human Player)
        {1632, 2382, 18.5, 18.5},       // 8: Cargo ==> Cargo Ship
        {2083, 1980, 24.0, 24.0},       // 3: Cargo ==> Rocket Low
        {1325, 2494, 26.0, 26.0},       // 2: Cargo ==> Rocket Middle
        { 451, 2700,  6.5,  6.5},       // 1: Cargo ==> Rocket High
        { 273, 1486,  0.0,  0.0},       // 13: Cargo ==> Rocket High (from back of Robot)
        {2319, 2555,  0.0,  0.0}        // 15: CLIMB ==> Use arm to raise front wheels to platform
    };
    public String[] operateArmNames =
    {
        "Not Used",                     // 0: Not Used
        "HATCH : Transport",            // 11: Hatch ==> Transport Position
        "HATCH : Human Player",         // 9: Hatch ==> Feeder (Human Player)
        "HATCH : Cargo Ship",           // 7: Hatch ==> Cargo Ship
        "HATCH : Rocket Low",           // 6: Hatch ==> Rocket Low
        "HATCH : Rocket Middle",        // 5: Hatch ==> Rocket Middle
        "HATCH : Rocket High",          // 4: Hatch ==> Rocket High
        "HATCH : Rocket High Rear",     // 14: Hatch ==> Rocket High (from back of Robot)
        "CARGO : Ground Pickup",        // 12: Cargo ==> Ground Pickup
        "CARGO : Human Player",         // 10: Cargo ==> Feeder (Human Player)
        "CARGO : Cargo Ship",           // 8: Cargo ==> Cargo Ship
        "CARGO : Rocket Low",           // 3: Cargo ==> Rocket Low
        "CARGO : Rocket Middle",        // 2: Cargo ==> Rocket Middle
        "CARGO : Rocket High",          // 1: Cargo ==> Rocket High
        "CARGO : Rocket High Rear",     // 13: Cargo ==> Rocket High (from back of Robot)
        "CLIMB : Raise Front Wheels"    // 15: CLIMB ==> Use arm to raise front wheels to platform
    };


    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        logitech = new Joystick(1);
        
        closeClaw = new JoystickButton(logitech, 4);
        closeClaw.whenPressed(new closeClawAndStopIntake());
        //rocketMid = new JoystickButton(logitech, 10);
        //rocketMid.whenPressed(new setupRobotVariables(0));
        //rocketHigh = new JoystickButton(logitech, 8);
        //rocketHigh.whenPressed(new setupRobotVariables(0));
        //cargoShip = new JoystickButton(logitech, 7);
        //cargoShip.whenPressed(new setupRobotVariables(0));
        //humanPlayer = new JoystickButton(logitech, 9);
        //humanPlayer.whenPressed(new setupRobotVariables(0));
        //groundTransport = new JoystickButton(logitech, 11);
        //groundTransport.whenPressed(new setupRobotVariables(0));
        //rocketLow = new JoystickButton(logitech, 12);
        //rocketLow.whenPressed(new setupRobotVariables(0));
        wristCowboyDrive1 = new JoystickButton(logitech, 2);
        wristCowboyDrive1.whileHeld(new wristCowboyDrive());
        operatorEnableCowboy = new JoystickButton(logitech, 1);
        operatorEnableCowboy.whileHeld(new shoulderCowboyDrive());
        xbox360 = new Joystick(0);
        
        driveWithJoysticks = new JoystickButton(xbox360, 2);
        driveWithJoysticks.whenPressed(new driveTrainArcadeTeleOp());
        driveToPosition = new JoystickButton(xbox360, 3);
        driveToPosition.whenPressed(new driveTrainDriveToPosition());
        driverCloseClaw = new JoystickButton(xbox360, 5);
        driverCloseClaw.whenPressed(new closeClawAndStopIntake());
        driverOpenClaw = new JoystickButton(xbox360, 6);
        driverOpenClaw.whenPressed(new clawOpenAndEjectCargo());


        // SmartDashboard Buttons
        SmartDashboard.putData("intakeOut", new intakeOut());
        SmartDashboard.putData("intakeIn", new intakeIn());
        SmartDashboard.putData("clawOpenAndEjectCargo", new clawOpenAndEjectCargo());
        SmartDashboard.putData("clawOperationTimedClose", new clawOperationTimedClose());
        SmartDashboard.putData("intakeLiftUpTimed: default", new intakeLiftUpTimed(2));
        SmartDashboard.putData("climbLift", new climbLift());
        SmartDashboard.putData("climbLower", new climbLower());
        SmartDashboard.putData("intakeLiftDownTimed: default", new intakeLiftDownTimed(2));
        SmartDashboard.putData("intakeStop", new intakeStop());
        SmartDashboard.putData("closeClawAndStopIntake", new closeClawAndStopIntake());
        SmartDashboard.putData("driveTrainDriveToPosition", new driveTrainDriveToPosition());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        // The setupRobotVariables method needs the proper
        // parameter passed ===>
        // Make sure that the identical buttons get 
        // commented out in the autogenerated code
        rocketMid = new JoystickButton(logitech, 10);
        rocketMid.whenPressed(new setupRobotVariables(ROCKET_MID));
        rocketHigh = new JoystickButton(logitech, 8);
        rocketHigh.whenPressed(new setupRobotVariables(ROCKET_HIGH));
        cargoShip = new JoystickButton(logitech, 7);
        cargoShip.whenPressed(new setupRobotVariables(CARGO_SHIP));
        humanPlayer = new JoystickButton(logitech, 9);
        humanPlayer.whenPressed(new setupRobotVariables(HUMAN_PLAYER));
        groundTransport = new JoystickButton(logitech, 11);
        groundTransport.whenPressed(new setupRobotVariables(GROUND_AND_TRANSPORT));
        rocketLow = new JoystickButton(logitech, 12);
        rocketLow.whenPressed(new setupRobotVariables(ROCKET_LOW));
        btnIntakeIn = new JoystickButton(logitech, 3);
        btnIntakeIn.whenPressed(new intakeIn());

        for (int i=1; i <= NUMBER_OF_ARM_POSITIONS; i++)
        {
            SmartDashboard.putData(operateArmNames[i], 
                               new operateArm(operateArmParameters[i] [ARM_PARAMETERS_SHOULDER_INDEX],
                                              operateArmParameters[i] [ARM_PARAMETERS_WRIST_INDEX],
                                              operateArmParameters[i] [ARM_PARAMETERS_RIGHT_DISTANCE_INDEX],
                                              operateArmParameters[i] [ARM_PARAMETERS_LEFT_DISTANCE_INDEX]));
        }

        SmartDashboard.putNumber("selected game piece: ", SELECTED_GAME_PIECE);
        SmartDashboard.putNumber("selected delivery location: ", SELECTED_DELIVERY_POSITION);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getxbox360() {
        return xbox360;
    }

    public Joystick getlogitech() {
        return logitech;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

    // TODO: I DON'T THINK ANY OF THIS BELONGS HERE -- WHERE DOES IT BELONG
    private int getArmArrayIndex(){
        int arrayIndex = SELECTED_GAME_PIECE * NUMBER_OF_DELIVERY_POSITIONS + SELECTED_DELIVERY_POSITION;
        if (arrayIndex >= 0 && arrayIndex <= NUMBER_OF_ARM_POSITIONS) {
            return arrayIndex;
        }
        else
        {
            System.out.println("ERROR in OI.getArmArrayIndex");
            return -1;
        }
    }

    public double getShoulderTarget(){
        int arrayIndex = getArmArrayIndex();
        if (arrayIndex == -1)
        {
            return -1.0;
        }
        else
        {
            return operateArmParameters[arrayIndex][ARM_PARAMETERS_SHOULDER_INDEX];
        }
    }

    public double getWristTarget(){
        int arrayIndex = getArmArrayIndex();
        if (arrayIndex == -1)
        {
            return -1.0;
        }
        else
        {
            return operateArmParameters[arrayIndex][ARM_PARAMETERS_WRIST_INDEX];
        }
    }

    public double getRightUltrasonicTarget(){
        int arrayIndex = getArmArrayIndex();
        if (arrayIndex == -1)
        {
            return -1.0;
        }
        else
        {
            return operateArmParameters[arrayIndex][ARM_PARAMETERS_RIGHT_DISTANCE_INDEX];
        }
    }
 
    public double getLeftUltrasonicTarget(){
        int arrayIndex = getArmArrayIndex();
        if (arrayIndex == -1)
        {
            return -1.0;
        }
        else
        {
            return operateArmParameters[arrayIndex][ARM_PARAMETERS_LEFT_DISTANCE_INDEX];
        }
    }

    public String getTargetName(){
        int arrayIndex = getArmArrayIndex();
        if (arrayIndex == -1)
        {
            return "";
        }
        else
        {
            return operateArmNames[arrayIndex];
        }
    }
}


