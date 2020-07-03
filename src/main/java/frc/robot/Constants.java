package frc.robot;

public class Constants {

    public static final ControlType CONTROL_TYPE = ControlType.GAMEPAD;
    public enum ControlType {JOYSTICKS, GAMEPAD}
    public enum StickType {LEFT, RIGHT}
    public enum StickDirection {X, Y}
    public static final double DRIVE_MODIFIER = 0.6;

    public static final int FL_TURN_CANID = 4;      //2
    public static final int FL_DRIVE_CANID = 0;     //2
    public static final int FR_TURN_CANID = 11;     //1
    public static final int FR_DRIVE_CANID = 15;    //1
    public static final int BL_TURN_CANID = 5;      //3
    public static final int BL_DRIVE_CANID = 1;     //3
    public static final int BR_TURN_CANID = 10;     //4
    public static final int BR_DRIVE_CANID = 14;    //4
    
    // All of the gearboxes are PG71. PG71 have a gearbox reduction of 71
    // PG71 drives 48 teeth gear which drives 40 teeth gear.
    // Quadrature encoder has a pulse per revolution of 7. There are four transitions (low-to-high and high-to-low for A and B signals) per revolution
    // Gear ratio = 40/48 * 71 * 7 * 4 = 1656.666...
    public static final double FL_GEAR_RATIO = -1656.67;
    public static final double FR_GEAR_RATIO = -1656.67;
    public static final double BL_GEAR_RATIO = -1656.67;
    // There is a n unknown issue with the BR gearbox that makes the pulse per revolution equal to 3.
    // Gear ratio = 40/48 * 71 * 3 * 4 = 710
    public static final double BR_GEAR_RATIO = -710;

    // Analog encoder positions for each motor controller associated with all motor controllers facing forward.
    // All motors will be facing in the same direction toward the front of the robot.
    public static final double FL_TURN_ZERO = 758.0;
    public static final double FR_TURN_ZERO = 807.0;
    public static final double BL_TURN_ZERO = 406.0;
	public static final double BR_TURN_ZERO = 772.0;
}