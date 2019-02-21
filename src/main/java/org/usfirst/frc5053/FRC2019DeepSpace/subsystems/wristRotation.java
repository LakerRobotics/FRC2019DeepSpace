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
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class wristRotation extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX wristTalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public wristRotation() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        wristTalonSRX = new WPI_TalonSRX(1);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    final int kSlotIdx = 0;
	final int kPIDLoopIdx = 0;
	final int kTimeoutMs = 30;
	boolean kSensorPhase = false;
    boolean kMotorInvert = false;
    final double kP = 1.0;
	final double kI = 0.0;
	final double kD = 0.0;
	final double kF = 0.0;
	final int kIzone = 0;
	final double kPeakOutput = 1.0;
    
    /* Config the sensor used for Primary PID and sensor direction */ 
    wristTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
                                                  kPIDLoopIdx,
				                                  kTimeoutMs);

    wristTalonSRX.setInverted(kMotorInvert);

    /* Config the peak and nominal outputs, 12V means full */
    wristTalonSRX.configNominalOutputForward(0, kTimeoutMs);
    wristTalonSRX.configNominalOutputReverse(0, kTimeoutMs);
    wristTalonSRX.configPeakOutputForward(1, kTimeoutMs);
    wristTalonSRX.configPeakOutputReverse(-1, kTimeoutMs);

    /**
    * Config the allowable closed-loop error, Closed-Loop output will be
    * neutral within this range. See Table in Section 17.2.1 for native
    * units per rotation.
    */
    wristTalonSRX.configAllowableClosedloopError(0, kPIDLoopIdx, kTimeoutMs);

    /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
   wristTalonSRX.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
   wristTalonSRX.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
   wristTalonSRX.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
   wristTalonSRX.config_kD(kPIDLoopIdx, kD, kTimeoutMs);

    /**
    * Grab the 360 degree position of the MagEncoder's absolute
    * position, and intitally set the relative sensor to match.
    */
    int absolutePosition = wristTalonSRX.getSensorCollection().getPulseWidthPosition();

    /* Mask out overflows, keep bottom 12 bits */
    absolutePosition &= 0xFFF;
    if (kSensorPhase) { absolutePosition *= -1; }
    if (kMotorInvert) { absolutePosition *= -1; }

    /* Set the quadrature (relative) sensor to match absolute */
    wristTalonSRX.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
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
        int relPosition = wristTalonSRX.getSelectedSensorPosition(0);
        int absPosition = wristTalonSRX.getSensorCollection().getPulseWidthPosition() & 0xFFF;
        double wristSpeed = wristTalonSRX.get();

        SmartDashboard.putNumber("wristSensorPos: ", relPosition);
        SmartDashboard.putNumber("wristSensorAbs: ", absPosition);
        // SmartDashboard.putNumber("wristSpeed: ", wristSpeed);
        // SmartDashboard.putNumber("wristJoystickAxis(0): ", Robot.oi.logitech.getRawAxis(0));
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    // 20190208 SNW begin added code
    public void moveWrist(double inputSpeed) {
        wristTalonSRX.set(inputSpeed);
    }

    public void stopWrist(){
        wristTalonSRX.set(0);
    }
    // 20190208 SNW end added code

    // 20190214 SNW begin added code
    public void startMoveToPosition(double targetPosition){
      
        
        wristTalonSRX.set(ControlMode.Position, targetPosition);
        SmartDashboard.putNumber("wristTargetPosition", wristTalonSRX.getClosedLoopTarget(0));
    }
    // 20190214 SNW end added code

}
