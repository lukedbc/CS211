package Project2;

import Project2.Result.ErrorResult;
import Project2.Result.SuccessResult;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class PostfixEvaluation {

    // I changed from Integer to BigDecimal because
    // when I tested with very big number (out range of an integer), it leaded to an error
    public Result execute(String postfix) {
        if (null == postfix || postfix.isEmpty()) {
            return new ErrorResult(1);
        }

        Stack<BigDecimal> stack = new Stack<>();
        for (int i = 0, size = postfix.length(); i < size; i++) {
            char currentCharacter = postfix.charAt(i);

            if (currentCharacter == ' ') {
                continue;
            }

            if (Character.isDigit(currentCharacter)) {
                // Get the number from string
                // for example:
                // Postfix: 123 456 + 345
                // Let say, I want to get the number from index 4 (the result is 456)
                // 1. The value is postfix.charAt(o) = 4.
                // 2. check if next index is a digit.
                //      If so, append postfix.charAt(next) to string
                //      If not, the character at next index is not digit, break out the loop
                // In the loop:
                // 1. The next index is next = i + 1 = 4 + 1 = 5
                //    The character at next index is postFix.charAt(next) = 5, append to res
                // 2. The next index is next = i + 1 = 5 + 1 = 6
                //    The character at next index is postFix.charAt(next) = 6, append to res
                // 3. THe next index is next = i + 1 = 6 + 1 = 7
                //    THe character at next index is postFix.charAt(next) = ' ', break the loop
                // The result is "456"

                StringBuilder intAsString = new StringBuilder();
                intAsString.append(currentCharacter);
                while (++i < size) {
                    char nextCharacter = postfix.charAt(i);
                    if (Character.isDigit(nextCharacter)) {
                        intAsString.append(nextCharacter);
                    } else {
                        i -= 1;
                        break;
                    }
                }
                stack.push(new BigDecimal(intAsString.toString()));
            }

            if (Constant.isOperator(currentCharacter)) {
                BigDecimal num1 = tryPop(stack);
                BigDecimal num2 = tryPop(stack);
                // num1 or num2 is null because the expression has invalid or unsupported format yet
                // For an example:
                // infix: 1 + (+2)
                // postfix: 1 2 + +
                // when currentCharacter is second '+' in postfix
                // ==> num1 = null and num2 = null
                if (num1 == null || num2 == null) {
                    return new ErrorResult(6, postfix, i);
                }
                if (num1.equals(BigDecimal.ZERO)) {
                    return new ErrorResult(4, postfix, i);
                }
                BigDecimal result = calculate(currentCharacter, num1, num2);
                stack.push(result.stripTrailingZeros());
            }
        }

        BigDecimal finalResult = stack.pop();

        // if finalResult is whole number ==> get result as integer
        if (isWholeNumber(finalResult)) {
            return new SuccessResult(String.valueOf(finalResult.intValueExact()));
        }

        return new SuccessResult(String.valueOf(finalResult));
    }

    private boolean isWholeNumber(BigDecimal bd) {
        return bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0;
    }

    private BigDecimal tryPop(Stack<BigDecimal> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    private BigDecimal calculate(char operator, BigDecimal num1, BigDecimal num2) {
        switch (operator) {
            case '+':
                return num2.add(num1);
            case '-':
                return num2.subtract(num1);
            case '*':
                return num2.multiply(num1);
            case '/':
                // real number with 15 decimal places
                return num2.divide(num1, 15, RoundingMode.HALF_UP);
            default:
                return BigDecimal.ZERO;
        }
    }
}
