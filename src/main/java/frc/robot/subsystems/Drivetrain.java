// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  private  TalonSRX frontRightMotor;
  private  TalonSRX frontLeftMotor;
  private  TalonSRX backRightMotor;
  private  TalonSRX backLeftMotor;

  public Drivetrain() {
    frontRightMotor = new TalonSRX(RobotMap.mapDriveTrain.FRONT_RIGHT_MOTOR);
    backRightMotor = new TalonSRX(RobotMap.mapDriveTrain.BACK_RIGHT_MOTOR);
    frontLeftMotor = new TalonSRX(RobotMap.mapDriveTrain.FRONT_LEFT_MOTOR);
    backLeftMotor = new TalonSRX(RobotMap.mapDriveTrain.BACK_LEFT_MOTOR);
  }

  /**
   * Sets the velocity of the drivetrain motors.
   * 
   * @param forwardVelocity The velocity to set for the forward movement of the
   *                        drivetrain.
   * @param rotationSpeed   The rotation speed to apply to the drivetrain.
   * @param slowModeActivated A boolean indicating whether slow mode is activated or not.
   *                          If true, the speed will be divided by 2.
   */
  public void setDrivetrainSpeed(double forwardVelocity, double rotationSpeed, boolean slowModeActivated) {
    // Divide speed in half if slow mode is activated

    if (slowModeActivated) {
      forwardVelocity = forwardVelocity * Constants.constDrivetrain.SLOW_MODE_MULTIPLIER;
      rotationSpeed = rotationSpeed * Constants.constDrivetrain.SLOW_MODE_MULTIPLIER;
    }

    // Set right velocity
    frontRightMotor.set(TalonSRXControlMode.PercentOutput, forwardVelocity - rotationSpeed);
    backRightMotor.set(TalonSRXControlMode.PercentOutput, forwardVelocity - rotationSpeed);
    // Set left velocity
    frontLeftMotor.set(TalonSRXControlMode.PercentOutput, forwardVelocity + rotationSpeed);
    backLeftMotor.set(TalonSRXControlMode.PercentOutput, forwardVelocity + rotationSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
