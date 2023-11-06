package Project2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// Constant class contains all constant variable and some helpful method
class Constant {

    private static final String SUPPORTED_PAIR = "(){}";
    private static final String SUPPORTED_OPERATOR = "+-*/";

    // The map stores open and close pair
    public static final Map<Character, Character> PAIR = new HashMap<>();
    // The map stores precedence of operator
    public static final Map<Character, Integer> PRECEDENCE = new HashMap<>();
    // The error message map
    // key is error code
    // value is error message
    public static final Map<Integer, String> ERROR_MESSAGES = new HashMap<>();

    // Init constant data
    static {
        initPair();
        initPrecedence();
        initErrorMessages();
    }

    private Constant() {

    }

    private static void initPair() {
        PAIR.put(SUPPORTED_PAIR.charAt(0), SUPPORTED_PAIR.charAt(1));
        PAIR.put(SUPPORTED_PAIR.charAt(2), SUPPORTED_PAIR.charAt(3));
    }

    private static void initPrecedence() {
        PRECEDENCE.put(SUPPORTED_OPERATOR.charAt(0), 1); // +
        PRECEDENCE.put(SUPPORTED_OPERATOR.charAt(1), 1); // -

        PRECEDENCE.put(SUPPORTED_OPERATOR.charAt(2), 2); // *
        PRECEDENCE.put(SUPPORTED_OPERATOR.charAt(3), 2); // /

        PRECEDENCE.put(SUPPORTED_PAIR.charAt(0), 0); // (
        PRECEDENCE.put(SUPPORTED_PAIR.charAt(2), 0); // {
    }

    private static void initErrorMessages() {
        // Check valid expression errors
        ERROR_MESSAGES.put(1, "The expression must be not null or empty");
        ERROR_MESSAGES.put(2, "The expression has unsupported character");
        // Infix to Postfix possible errors
        ERROR_MESSAGES.put(3, "Incomplete expression");
        ERROR_MESSAGES.put(4, "Divide by zero error");
        ERROR_MESSAGES.put(5, "Not supported kind of expression yet");
        ERROR_MESSAGES.put(6, "Not supported kind of expression yet. Please check original expression");
        ERROR_MESSAGES.put(7, "Wrong expression");
    }


    public static boolean isOperator(char c) {
        return SUPPORTED_OPERATOR.indexOf(c) != -1;
    }

    public static boolean isPair(char c) {
        return isOpenPair(c) || isClosePair(c);
    }

    public static int getPrecedence(char c) {
        return PRECEDENCE.get(c);
    }

    public static boolean isOpenPair(char c) {
        return PAIR.containsKey(c);
    }

    public static boolean isClosePair(char c) {
        return PAIR.containsValue(c);
    }

    public static char getOpenPairByClosePair(char c) {
        for (Entry<Character, Character> entry : PAIR.entrySet()) {
            char open = entry.getKey();
            char close = entry.getValue();

            if (c == close) {
                return open;
            }
        }

        return ' ';
    }

    public static String getErrorMessage(int code) {
        return ERROR_MESSAGES.get(code);
    }
}
