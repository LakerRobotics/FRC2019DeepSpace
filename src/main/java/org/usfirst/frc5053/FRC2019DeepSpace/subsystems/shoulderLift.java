// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5053.FRC2019DeepSpace.subsystems;


import org.usfirst.frc5053.FRC2019DeepSpace.Robot;
import org.usfirst.frc5053.FRC2019DeepSpace.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class shoulderLift extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX shoulderTalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public shoulderLift() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shoulderTalonSRX = new WPI_TalonSRX(2);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    final int kSlotIdx = 0;
	final int kPIDLoopIdx = 0;
	final int kTimeoutMs = 30;
	boolean kSensorPhase = false;
    boolean kMotorInvert = false;
    final double kP = 8.0; // SNW 16.0 was good, except it started oscillating at the rocket high
	final double kI = 0.0;
	final double kD = 0.0;
	final double kF = 0.0;
	final int kIzone = 0;
    final double kPeakOutput = 1.0;
    
    /* Config the sensor used for Primary PID and sensor direction */ 
    shoulderTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
                                                  kPIDLoopIdx,
				                                  kTimeoutMs);

    shoulderTalonSRX.setInverted(kMotorInvert);

    /* Config the peak and nominal outputs, 12V means full */
    shoulderTalonSRX.configNominalOutputForward(0, kTimeoutMs);
    shoulderTalonSRX.configNominalOutputReverse(0, kTimeoutMs);
    shoulderTalonSRX.configPeakOutputForward(1, kTimeoutMs);
    shoulderTalonSRX.configPeakOutputReverse(-1, kTimeoutMs);

    /**
    * Config the allowable closed-loop error, Closed-Loop output will be
    * neutral within this range. See Table in Section 17.2.1 for native
    * units per rotation.
    */
    shoulderTalonSRX.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

    /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
   shoulderTalonSRX.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
   shoulderTalonSRX.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
   shoulderTalonSRX.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
   shoulderTalonSRX.config_kD(kPIDLoopIdx, kD, kTimeoutMs);

    /**
    * Grab the 360 degree position of the MagEncoder's absolute
    * position, and intitally set the relative sensor to match.
    */
    int absolutePosition = shoulderTalonSRX.getSensorCollection().getPulseWidthPosition();

    /* Mask out overflows, keep bottom 12 bits */
    absolutePosition &= 0xFFF;
    if (kSensorPhase) { absolutePosition *= -1; }
    if (kMotorInvert) { absolutePosition *= -1; }

    /* Set the quadrature (relative) sensor to match absolute */
    System.out.println("Set Realative to Absolute");
    shoulderTalonSRX.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);

}

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        int relPosition = shoulderTalonSRX.getSelectedSensorPosition(0);
        int absPosition = shoulderTalonSRX.getSensorCollection().getPulseWidthPosition();
        double shoulderSpeed = shoulderTalonSRX.get();

        SmartDashboard.putNumber("ShoulderSensorPos: ", relPosition);
        SmartDashboard.putNumber("ShoulderSensorAbs: ", absPosition);
        // SmartDashboard.putNumber("ShoulderSpeed: ", shoulderSpeed);
        // SmartDashboard.putNumber("ShoulderJoystickAxis(1): ", Robot.oi.logitech.getRawAxis(1));
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // 20190208 SNW begin added code
    public void moveShoulder(double inputSpeed) {
        shoulderTalonSRX.set(ControlMode.PercentOutput, inputSpeed);
    }

    public void stopShoulder(){
        shoulderTalonSRX.set(ControlMode.PercentOutput, 0);
    }
    // 20190208 SNW end added code

    // 20190210 BEGIN ADDED CODE
    // Move to Set Position
    public void testSetPosition(){
        System.out.print("testSetPosition BEGIN");
        shoulderTalonSRX.set(ControlMode.Position, -1432);
        shoulderTalonSRX.set(-0.8);
        System.out.print("testSetPosition END");
    }

    public boolean testSetPositionComplete(){
        System.out.print("testSetPositionComplete BEGIN: ");
        boolean returnValue = false;
        double currentPos = shoulderTalonSRX.getSelectedSensorPosition(0);
        if ((currentPos <= -1422) && (currentPos >= -1442)) returnValue = true;
        System.out.println("DONE: " + returnValue+ " POS: " + currentPos);
        // System.out.print("testSetPositionComplete END");
        return returnValue;
    }

    public void startMoveToPosition(double targetPosition){
      
        
        shoulderTalonSRX.set(ControlMode.Position, targetPosition);
        SmartDashboard.putNumber("shoulderTargetPosition", shoulderTalonSRX.getClosedLoopTarget(0));
    }
    
    
}

