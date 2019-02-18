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
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

    /**
 *
 */
public class intake extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private VictorSP intakeRightVictorSP;
    private VictorSP intakeLeftVictorSP;
    private DoubleSolenoid intakeLiftSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public intake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        intakeRightVictorSP = new VictorSP(3);
        addChild("intakeRightVictorSP",intakeRightVictorSP);
        intakeRightVictorSP.setInverted(true);
        
        intakeLeftVictorSP = new VictorSP(4);
        addChild("intakeLeftVictorSP",intakeLeftVictorSP);
        intakeLeftVictorSP.setInverted(false);
        
      //  intakeLiftSolenoid = new DoubleSolenoid(0, 3, 4);
      //  addChild("intakeLiftSolenoid",intakeLiftSolenoid);
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
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

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void moveIntakeOut(){
        Robot.intake.intakeRightVictorSP.set(.5);
        Robot.intake.intakeLeftVictorSP.set(.5);
    }

    public void moveIntakeIn(){
        Robot.intake.intakeRightVictorSP.set(-.5);
        Robot.intake.intakeLeftVictorSP.set(-.5);
    }

    public void stop(){
        Robot.intake.intakeRightVictorSP.set(0);
        Robot.intake.intakeLeftVictorSP.set(0);
    }

    public void liftIntake()
    {
        intakeLiftSolenoid.set(Value.kForward);
    }

    public void lowerIntake()
    {
        intakeLiftSolenoid.set(Value.kReverse);
    }
}

