package Project2;

import Project2.Result.ErrorResult;
import Project2.Result.SuccessResult;
import java.util.Stack;

public class ExpressionEvaluation {

    public Result isValidExpression(String expression) {
        if (null == expression || expression.isEmpty()) {
            return new ErrorResult(1);
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0, size = expression.length(); i < size; i++) {
            char c = expression.charAt(i);

            // expression start with operator (not supported yet)
            if (i == 0 && Constant.isOperator(c)) {
                if ("+-".indexOf(c) != -1) {
                    return new ErrorResult(5, expression, i);
                }
                return new ErrorResult(7, expression, i);
            }
            // expression has unsupported character
            if (!Character.isDigit(c) &&
                    !Constant.isOperator(c) &&
                    !Constant.isPair(c)) {
                if (c == ' ') {
                    continue;
                }
                return new ErrorResult(2, expression, i);
            }

            if (Character.isDigit(c) || Constant.isOperator(c)) {
                continue;
            }

            if (Constant.isOpenPair(c)) {
                stack.push(c);
            }

            if (Constant.isClosePair(c)) {
                // current character is close pair
                // it will be an error if
                // 1. stack is empty - 123 + 45 ).
                // 2. peek of stack is not an open pair of current character - (123 + 456}
                if (stack.isEmpty() || stack.pop() != Constant.getOpenPairByClosePair(c)) {
                    return new ErrorResult(3, expression, i);
                }
            }
        }

        // no pair in stack
        if (stack.isEmpty()) {
            return new SuccessResult("valid");
        }

        // still have some pair in stack ==> error
        return new ErrorResult(3, expression, expression.length() - 1);
    }
}
