// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5053.FRC2019DeepSpace.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc5053.FRC2019DeepSpace.OI;
import org.usfirst.frc5053.FRC2019DeepSpace.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import jdk.internal.vm.annotation.DontInline;
/**
 *
 */
public class setupRobotVariables extends Command {
    private int m_deliveryPosition;
    private operateArm m_OperateArm;
    private cmdGroundPickupGroup m_GroundPickup;
    private cmdRocketHigh m_RocketHIgh;
    private cmdHatchHumanPlayer m_HatchHumanPlayer;
    private intakeLiftDownTimed m_IntakeLiftDownTimed;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public setupRobotVariables(int deliveryPosition) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    m_deliveryPosition = deliveryPosition;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        SmartDashboard.putString("DEBUG: ", "setupRobotVAriables.initialize");
        OI.SELECTED_DELIVERY_POSITION = m_deliveryPosition;

        if (Robot.oi.logitech.getRawAxis(3) < 0.5) OI.SELECTED_GAME_PIECE = OI.GAME_PIECE_CARGO;
        else OI.SELECTED_GAME_PIECE = OI.GAME_PIECE_HATCH;

        SmartDashboard.putNumber("selected game piece: ", OI.SELECTED_GAME_PIECE);
        SmartDashboard.putNumber("selected delivery location: ", OI.SELECTED_DELIVERY_POSITION);

        SmartDashboard.putString("Operator Selection: ", Robot.oi.getTargetName());
        SmartDashboard.putNumber("Shoulder Encoder Target: ", Robot.oi.getShoulderTarget());
        SmartDashboard.putNumber("Wrist Encoder Target: ", Robot.oi.getWristTarget());
        SmartDashboard.putNumber("LeftTargetDistance: ", Robot.oi.getLeftUltrasonicTarget());
        SmartDashboard.putNumber("RightTargetDistance: ", Robot.oi.getRightUltrasonicTarget());

        SmartDashboard.putString("SHOULDER ACTION: ", "");
        SmartDashboard.putString("WRIST ACTION: ", "");
        SmartDashboard.putString("INTAKE ACTION: ", "");
        SmartDashboard.putString("CLAW ACTION: ", "");

        m_GroundPickup = new cmdGroundPickupGroup(Robot.oi.getShoulderTarget(),
                                                  Robot.oi.getWristTarget(),
                                                  Robot.oi.getRightUltrasonicTarget(),
                                                  Robot.oi.getLeftUltrasonicTarget());
        m_OperateArm = new operateArm(Robot.oi.getShoulderTarget(),
                                      Robot.oi.getWristTarget(),
                                      Robot.oi.getRightUltrasonicTarget(),
                                      Robot.oi.getLeftUltrasonicTarget());
        m_RocketHIgh = new cmdRocketHigh(Robot.oi.getShoulderTarget(),
                                         Robot.oi.getWristTarget(),
                                         Robot.oi.getRightUltrasonicTarget(),
                                         Robot.oi.getLeftUltrasonicTarget()); 
        m_HatchHumanPlayer = new cmdHatchHumanPlayer(Robot.oi.getShoulderTarget(),
                                                     Robot.oi.getWristTarget(),
                                                     Robot.oi.getRightUltrasonicTarget(),
                                                     Robot.oi.getLeftUltrasonicTarget());
                                    
        m_IntakeLiftDownTimed = new intakeLiftDownTimed(0);
    }

    

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        SmartDashboard.putString("DEBUG: ", "setupRobotVAriables.execute");
        if (OI.SELECTED_DELIVERY_POSITION == OI.GROUND_AND_TRANSPORT &&
            OI.SELECTED_GAME_PIECE == OI.GAME_PIECE_CARGO)
            {   // Cargo Ground Pickup, Intake IN, Open Claw
                // m_IntakeLiftDownTimed.start();
                Scheduler.getInstance().add(new intakeLiftDownTimed(0));
                // m_GroundPickup.start();
                Scheduler.getInstance().add(new cmdGroundPickupGroup(Robot.oi.getShoulderTarget(),
                                                                     Robot.oi.getWristTarget(),
                                                                     Robot.oi.getRightUltrasonicTarget(),
                                                                    Robot.oi.getLeftUltrasonicTarget()));
            }
        else
            {

            if (OI.SELECTED_DELIVERY_POSITION == OI.ROCKET_HIGH)
                {   // Rocket High (Cargo or Hatch), Raise Intake
            
                    Scheduler.getInstance().add(new cmdRocketHigh(Robot.oi.getShoulderTarget(),
                                                                  Robot.oi.getWristTarget(),
                                                                  Robot.oi.getRightUltrasonicTarget(),
                                                                  Robot.oi.getLeftUltrasonicTarget()));
                    //m_RocketHIgh.start();
            
                }
            else
                {
                    if (OI.SELECTED_DELIVERY_POSITION == OI.HUMAN_PLAYER &&
                        OI.SELECTED_GAME_PIECE == OI.GAME_PIECE_HATCH)
                        {   // Hatch Human Player, Close Claw
                            Scheduler.getInstance().add(new intakeLiftDownTimed(0));
                            //m_IntakeLiftDownTimed.start();
                            Scheduler.getInstance().add(new cmdHatchHumanPlayer(Robot.oi.getShoulderTarget(),
                                                                                Robot.oi.getWristTarget(),
                                                                                Robot.oi.getRightUltrasonicTarget(),
                                                                                Robot.oi.getLeftUltrasonicTarget()));
                            //m_HatchHumanPlayer.start();
                        }
                    else
                    {
                        if (OI.SELECTED_DELIVERY_POSITION == OI.GROUND_AND_TRANSPORT &&
                            OI.SELECTED_GAME_PIECE == OI.GAME_PIECE_HATCH)
                            {
                                // THIS WILL BREAK THE ROBOT
                                // DON'T DO IT!!!!!
                            }
                        else
                            {
                            Scheduler.getInstance().add(new intakeLiftDownTimed(0));
                            //m_IntakeLiftDownTimed.start();
                            Scheduler.getInstance().add(new operateArm(Robot.oi.getShoulderTarget(),
                                                                       Robot.oi.getWristTarget(),
                                                                       Robot.oi.getRightUltrasonicTarget(),
                                                                       Robot.oi.getLeftUltrasonicTarget()));
                            //m_OperateArm.start();
                            }
                    }
                }
            }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        SmartDashboard.putString("DEBUG: ", "setupRobotVAriables.isFinished");
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        SmartDashboard.putString("DEBUG: ", "setupRobotVAriables.end");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        SmartDashboard.putString("DEBUG: ", "setupRobotVAriables.interrupted");
    }
}
