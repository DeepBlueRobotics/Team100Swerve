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
    
    // The actual analog encoder maxmimum positions, since 5V is not supplied to each of the analog encoders.
    public static final int FL_MAX_ANALOG = 886;
    public static final int FR_MAX_ANALOG = 882;
    public static final int BL_MAX_ANALOG = 877;
    public static final int BR_MAX_ANALOG = 880;

    // All of the gearboxes are PG71. PG71 have a gearbox reduction of 71
    // PG71 drives 48 teeth gear which drives 40 teeth gear.
    // Quadrature encoder has a pulse per revolution of 7. There are four transitions (low-to-high and high-to-low for A and B signals) per revolution
    // Gear ratio = 40/48 * 71 * 7 * 4 = 1656.666...
    public static final double FL_GEAR_RATIO = -1656.67;
    public static final double FR_GEAR_RATIO = -1656.67;
    public static final double BL_GEAR_RATIO = -1656.67;
    public static final double BR_GEAR_RATIO = -1656.67;

    // Analog encoder positions for each motor controller associated with all motor controllers facing forward.
    // All motors will be facing in the same direction toward the front of the robot.
    // Increasing the analog encoder is counter-clockwise, decreasing is clockwise.
    public static final double FL_TURN_ZERO = 758.0;
    public static final double FR_TURN_ZERO = 807.0;
    public static final double BL_TURN_ZERO = 406.0;
    public static final double BR_TURN_ZERO = 169.0;

    public static final double LEFT_JOY_Y_THRESHOLD = 0.008;
}