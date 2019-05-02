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
    
    final double SHOULDER_TOLERANCE =150;
    final int kSlotIdx = 0;
    final int kPIDLoopIdx = 0;
    final int kTimeoutMs = 30;
    boolean kSensorPhase = false;
    boolean kMotorInvert = false;
    final double kP = 4.0; // SNW 16.0 was good, except it started oscillating at the rocket high
    final double kI = 0.0;
    final double kD = 0.0;
    final double kF = 0.0;
    final int kIzone = 0;
    final double kPeakOutput = 1.0;
    final int encoderMariginOfError = 10;
    final boolean kSoftLimitEnabled = true;
    final int kSoftLimitForward = 3000;
    final int kSoftLimitReverse = 100;
    final boolean kEnableCurrentLimit = true;
    final int kPeakCurrentLimit = 25; //amps`
    final int kPeakCurrentDuration = 200; //milliseconds

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX shoulderTalonSRX;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public shoulderLift() {
        // System.out.println("shoulderLift START");
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        shoulderTalonSRX = new WPI_TalonSRX(2);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS  
    
        SmartDashboard.putNumber("armHeightEnum", Robot.oi.INITIAL_AND_ERROR_BUTTON);
        configureShoulder();
        syncEncoderValues();   
        // System.out.println("shoulderLift END");
    }

    @Override
    public void initDefaultCommand() {
        // System.out.println("shoulderLift.initDefaultCommand START");
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        // System.out.println("shoulderLift.initDefaultCommand END");
    }

    @Override
    public void periodic() {
        // System.out.println("shoulderLift.periodic START");
        // Put code here to be run every loop
        int relPosition = shoulderTalonSRX.getSelectedSensorPosition(0);
        int absPosition = shoulderTalonSRX.getSensorCollection().getPulseWidthPosition();
        double shoulderSpeed = shoulderTalonSRX.get();

        SmartDashboard.putNumber("ShoulderSensorPos: ", relPosition);
        SmartDashboard.putNumber("ShoulderSensorAbs: ", absPosition);
        SmartDashboard.putBoolean("shoulderSynced", isEncoderSynced());
        SmartDashboard.putNumber("shoulderCurrent", shoulderTalonSRX.getOutputCurrent());
        // SmartDashboard.putNumber("ShoulderSpeed: ", shoulderSpeed);
        // SmartDashboard.putNumber("ShoulderJoystickAxis(1): ", Robot.oi.logitech.getRawAxis(1));
        // System.out.println("shoulderLift.periodic END");
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // 20190208 SNW begin added code
    public void moveShoulder(double inputSpeed) {
        // System.out.println("shoulderLift.moveShoulder START");
        shoulderTalonSRX.set(ControlMode.PercentOutput, inputSpeed);
        // System.out.println("shoulderLift.moveShoulder END");
    }

    public void stopShoulder(){
        // System.out.println("shoulderLift.stopShoulder START");
        shoulderTalonSRX.set(ControlMode.PercentOutput, 0.0);
        // System.out.println("shoulderLift.stopShoulder END");
    }
    // 20190208 SNW end added code

    public void startMoveToPosition(double targetPosition){
        // System.out.println("shoulderLift.startMoveToPosition START");

        if (isEncoderSynced())
        {
            // System.out.println("shoulderLift.startMoveToPosition ISSUE TALON COMMAND");
            shoulderTalonSRX.set(ControlMode.Position, targetPosition);
            SmartDashboard.putNumber("shoulderTargetPosition", shoulderTalonSRX.getClosedLoopTarget(0));
        }
        else 
        {
            System.out.println("shoulderLift.startMoveToPosition OUT OF SYNC DO NOTHING");
            SmartDashboard.putBoolean("shoulderSynced", false);
        }

        // System.out.println("shoulderLift.startMoveToPosition END");
    }
    
    public void configureShoulder(){
        // System.out.println("shoulderLift.configureShoulder START");
        // configure the shoulder encoder to have the soft limits of 100-3000
                
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

       // configures TalonSRX to prevent overrotation of the motor
       shoulderTalonSRX.configForwardSoftLimitEnable(kSoftLimitEnabled);
       shoulderTalonSRX.configForwardSoftLimitThreshold(kSoftLimitForward);
       shoulderTalonSRX.configReverseSoftLimitEnable(kSoftLimitEnabled);
       shoulderTalonSRX.configReverseSoftLimitThreshold(kSoftLimitReverse);

       // enables current limits on the motor
       shoulderTalonSRX.enableCurrentLimit(true);
       shoulderTalonSRX.configPeakCurrentLimit(kPeakCurrentLimit);
       shoulderTalonSRX.configPeakCurrentDuration(kPeakCurrentDuration);
       // System.out.println("shoulderLift.configureShoulder END");
    }
    
    public void syncEncoderValues(){
        // System.out.println("shoulderLift.syncEncoderValues START");

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
        shoulderTalonSRX.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
        //System.out.println("shoulderLift.syncEncoderValues END");
    }

    public boolean isEncoderSynced()
    {
        // System.out.println("shoulderLift.isEncoderSynced START");
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
        int relativePosition= shoulderTalonSRX.getSelectedSensorPosition(kPIDLoopIdx);

        if (relativePosition >= (absolutePosition-encoderMariginOfError) &&
            relativePosition <= (absolutePosition+encoderMariginOfError))
            {
                // System.out.println("shoulderLift.isEncoderSynced END: true");
                return true;
            }
        else 
            {
                System.out.println("shoulderLift.isEncoderSynced FALSE");
                return false;
            }
    }

    public boolean isTargetReached(){
        double targetValue = Robot.oi.getShoulderTarget();
        int absolutePosition = shoulderTalonSRX.getSensorCollection().getPulseWidthPosition();
        double shoulderError = Math.abs(targetValue-absolutePosition);
        if(shoulderError <= SHOULDER_TOLERANCE) return true;
        else return false;
    }
}

