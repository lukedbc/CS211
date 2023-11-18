package Project2;

import Project2.Result.ErrorResult;
import Project2.Result.FinalResult;
import Project2.Result.SuccessResult;
import java.util.Scanner;

// First Name: Loc
// Last Name: Dao
// Date: 5th, Nov 2023
// sID: 201875743
// Description:
//  Main idea:
//    Evaluate expression using postfix approach
//    ExpressionEvaluation class: check if expression is valid or not
//    InfixToPostFix: Convert expression from infix to postfix
//    MyExpressionTest: Main class to execute program (input expression,
//      check expression, convert expression and evaluate expression)
//    Helpful Class: Constant, Result, TestCases
public class MyExpressionTest {

    private static void info() {
        System.out.println("------ Student Info ------");
        System.out.println("CS 211. Fall Quarter 2023");
        System.out.println("Name: Dao, Loc");
        System.out.println("sID: 201875743");
        System.out.println("-------------------------");
    }

    public static void main(String[] args) {
        info();
        userInput();

        //TestCases.slide3Input();
        //TestCases.myInput();
    }

    private static void userInput() {
        final Scanner CONSOLE = new Scanner(System.in);
        while (true) {
            System.out.println("\nCS 211. Fall Quarter 2023");
            System.out.print("Enter a math expression: ");

            String expression = CONSOLE.nextLine();
            if (expression == null || expression.isEmpty()) {
                System.out.println("\nBye!");
                break;
            }
            Result result = evaluate(expression);
            if (result == null) {
                continue;
            }
            result.print();
        }

        CONSOLE.close();
    }

    // If expression is not valid
    //      return a result as ErrorResult
    // If expression is valid
    //      Convert expression to Postfix expression
    //      If result of converting to postfix is successful result
    //         Evaluate postfix
    //            If result of evaluating is successful result
    //               build a Final Result then return it
    //            Else -- result of evaluating is an error
    //               return an error result
    //     Else  -- result of converting to posfix is an error
    //         return an error result
    public static Result evaluate(String expression) {
        if (null == expression || expression.isEmpty()) {
            return new ErrorResult() {
                @Override
                public void print() {
                    System.out.println("\nBye!");
                }
            };
        }

        expression = expression.trim();
        // Validate that input expression is valid or not
        ExpressionEvaluation expressionEvaluation = new ExpressionEvaluation();
        Result validateResult = expressionEvaluation.isValidExpression(expression);
        // invalid expression
        if (validateResult instanceof ErrorResult) {
            return validateResult;
        }

        // valid expression
        if (validateResult instanceof SuccessResult) {
            // Convert infix expression to postfix expression
            InfixToPostFix l2p = new InfixToPostFix();
            Result postFixResult = l2p.execute(expression);
            // convert infix to post fix fails
            if (postFixResult instanceof ErrorResult) {
                return postFixResult;
            }
            // Evaluate postfix expression
            String postfix = postFixResult.getResultString();
            PostfixEvaluation pe = new PostfixEvaluation();
            Result evaluateResult = pe.execute(postfix);
            // Invalid result
            if (evaluateResult instanceof ErrorResult) {
                // evaluate fails
                return evaluateResult;
            }
            // Valid result
            return new FinalResult(expression, postfix, evaluateResult.getResultString());
        } else {
            // Something bad happened because validateResult is not Success or Error result neither
            System.out.println("Something really bad happened");
        }

        return null;
    }
}

