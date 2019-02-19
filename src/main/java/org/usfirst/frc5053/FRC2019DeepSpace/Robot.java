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

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.naming.InitialContext;

import org.usfirst.frc5053.FRC2019DeepSpace.commands.*;
import org.usfirst.frc5053.FRC2019DeepSpace.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static driveTrain driveTrain;
    public static shoulderLift shoulderLift;
    public static wristRotation wristRotation;
    public static intake intake;
    public static claw claw;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**********************************
     * Create a static variable to store the last pressed button on the
     * LabView customized dashboard.
     **********************************/
    public static final int INITIAL_AND_ERROR_BUTTON = 9999;
    public static final int UNPROGRAMMED_ARM_PARAMETER = 0;
    public static final int ARM_PARAMETERS_SHOULDER_INDEX = 0;
    public static final int ARM_PARAMETERS_WRIST_INDEX = 0;
    public static final int ARM_PARAMETERS_DISTANCE_INDEX = 0;

    public static int lastDashboardButton = INITIAL_AND_ERROR_BUTTON;      // No Button Pressed
    /**********************************
     * {shoulder, wrist, distance}
     * NOTE that HATCH Rocket Low = HATCH Feeder = HATCH Cargo Ship
     * TODO: Discuss the other commands with Grant
     * TODO: Hatch ==> Feeder (Human Player)
     * TODO: Cargo ==> Rocket High from Back of Robot
     * TODO: Cargo ==> Ground Pickup
     * TODO: Hatch ==> Transport
     * TODO: Cowboy (Control Wrist/Shoulder with Joystick) ==> Is there a benefit to 
     *       Allowing cowboy on one or both?
     * 
     */
    public double[][] operateArmParameters =
    {
        {},
        {671, 2421, 0},             // Cargo ==> Rocket High
//        {1422, 2239, 0},            // Cargo ==> Rocket Middle
        {1321, 2239, 0},            // Cargo ==> Rocket Middle
//        {2197, 1749, 0},            // Cargo ==> Rocket Low
        {2096, 1749, 0},            // Cargo ==> Rocket Low
        {537, 2864, 0},             // Hatch ==> Rocket High
        {1607, 2166, 0},            // Hatch ==> Rocket Middle
        {2605, 1341, 0},            // Hatch ==> Rocket Low
        {2605, 1341, 0},            // Hatch ==> Feeder (Human Player)
        {2605, 1341, 0},            // Hatch ==> Cargo Ship
//        {1796, 2026, 0},            // Cargo ==> Cargo Ship
        {1632, 2321, 0},            // Cargo ==> Cargo Ship
        {2837, 1447, 0},            // Cargo ==> Ground Pickup
        {1738, 2048, 0},            // Cargo ==> Feeder (Human Player)
        {106, 1710, 0},             // Cargo ==> Rocket High (from back of Robot)
        {}                          // Hatch ==> Transport Position
    };

    //UsbCamera camera1;
    //camera1=
    
    VideoSink server;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        /*********************
         * 20190216 : Scott Andrews
         * Allow video to be streamed from camera attached to the RoboRIO USB port
         */
        CameraServer.getInstance().startAutomaticCapture(); 

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new driveTrain();
        shoulderLift = new shoulderLift();
        wristRotation = new wristRotation();
        intake = new intake();
        claw = new claw();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
     /*****************************
      * Act on the function requested by the operator from the LabView customized
      * Dashboard
      *****************************/
        double inputFromLabViewDashBoard = INITIAL_AND_ERROR_BUTTON;
        int convertedInputFromLabViewDashBoard = INITIAL_AND_ERROR_BUTTON;
        Command commandToExecute;

        try {
            inputFromLabViewDashBoard = SmartDashboard.getNumber("armHeightEnum", INITIAL_AND_ERROR_BUTTON);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
            inputFromLabViewDashBoard = INITIAL_AND_ERROR_BUTTON;
        }

        convertedInputFromLabViewDashBoard = (int) inputFromLabViewDashBoard;

        if ((convertedInputFromLabViewDashBoard == lastDashboardButton) ||
            (convertedInputFromLabViewDashBoard == INITIAL_AND_ERROR_BUTTON) ||
            (convertedInputFromLabViewDashBoard == UNPROGRAMMED_ARM_PARAMETER ))
        {
            // Do Nothing : The operator has not requested a new operation
        }
        else
        {
            // Act on the new request
            // TODO : Do I need to Cancel any Prior request?
            commandToExecute = new operateArm(operateArmParameters[ARM_PARAMETERS_SHOULDER_INDEX][convertedInputFromLabViewDashBoard],
                                              operateArmParameters[ARM_PARAMETERS_WRIST_INDEX][convertedInputFromLabViewDashBoard]);
            commandToExecute.start();
            lastDashboardButton = convertedInputFromLabViewDashBoard;
        }
        /*****************************/

        Scheduler.getInstance().run();
    }
}
