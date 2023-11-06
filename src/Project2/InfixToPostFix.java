package Project2;

import Project2.Result.ErrorResult;
import Project2.Result.SuccessResult;
import java.util.Stack;

public class InfixToPostFix {

    public InfixToPostFix() {
    }

    public Result execute(String expression) {
        if (expression == null || expression.isEmpty()) {
            return new ErrorResult(1);
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0, size = expression.length(); i < size; i++) {
            char currentCharacter = expression.charAt(i);

            if (Character.isDigit(currentCharacter)) {
                result.append(currentCharacter);
            }

            if (Constant.isOpenPair(currentCharacter)) {
                stack.push(currentCharacter);
            }

            if (Constant.isClosePair(currentCharacter)) {
                char openPair = Constant.getOpenPairByClosePair(currentCharacter);
                // open pair isn't found
                if (openPair == ' ') {
                    return new ErrorResult(2, expression, i);
                }

                while (!stack.isEmpty() && stack.peek() != openPair) {
                    result.append(" ");
                    result.append(stack.peek());
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    // stack is empty but current character is close pair
                    // ==> incomplete expression
                    return new ErrorResult(3, expression, i);
                }

                stack.pop();
            }

            if (Constant.isOperator(currentCharacter)) {
                int currentPrecedence = Constant.getPrecedence(currentCharacter);
                while (!stack.isEmpty()
                        && Constant.getPrecedence(stack.peek()) >= currentPrecedence) {
                    result.append(" ");
                    result.append(stack.peek());
                    stack.pop();
                }
                result.append(" ");
                stack.push(currentCharacter);
            }
        }

        while (!stack.isEmpty()) {
            char peek = stack.peek();

            if (Constant.isOpenPair(peek)) {
                return new ErrorResult(3, expression, expression.length() - 1);
            }
            result.append(" ");
            result.append(peek);
            stack.pop();
        }

        return new SuccessResult(result.toString().trim());
    }
}

