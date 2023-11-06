package Project2;

import Project2.Result.ErrorResult;
import Project2.Result.FinalResult;
import java.util.ArrayList;
import java.util.List;

public class TestCases {

    public static void slide3Input() {
        System.out.println("-------- Slide 3 test cases --------");
        String[] expressions = new String[]{
                "1+2+3",
                "(1+2*3",
                "((1+2 + 3",
                "}1+2)*3",
                "((1+2",
                "15+(2*3)-(30+2)",
                "123+456+789",
                "1+2*(((80/2)))",
                ""
        };
        for (int i = 0; i < expressions.length; i++) {
            System.out.println("\nTest #" + (i + 1));
            Result res = MyExpressionTest.evaluate(expressions[i]);
            if (res != null) {
                res.print();
            }
        }
        System.out.println("----------------------------");
    }

    public static void myInput() {
        System.out.println("\n-------- My test cases --------");
        List<String[]> testCases = new ArrayList<>();

        testCases.add(new String[]{"-2 + 3", "Error"});
        testCases.add(new String[]{"*123 + 3", "Error"});
        testCases.add(new String[]{"123 ++ 3", "Error"});
        testCases.add(new String[]{"123 +(+3)", "Error"});
        testCases.add(new String[]{"123 +* 3)", "Error"});
        testCases.add(new String[]{"((+123", "Error"});
        testCases.add(new String[]{"123 + -2", "Error"});
        testCases.add(new String[]{"123^2", "Error"});
        testCases.add(new String[]{"123 * [1 + 2*(3+2)]", "Error"});
        testCases.add(new String[]{"x + y", "Error"});
        testCases.add(new String[]{"12-*/", "Error"});
        testCases.add(new String[]{"(+2*3", "Error"});
        testCases.add(new String[]{"(+2*3", "Error"});
        testCases.add(new String[]{"2.3 + 3", "Error"});

        testCases.add(new String[]{"2 * (3 + 4)", "14"});
        testCases.add(new String[]{"5 * (2 + 6) - 9", "31"});
        testCases.add(new String[]{"(8 - 3) * (7 + 2)", "45"});
        testCases.add(new String[]{"2 * (5 - (3 + 1))", "2"});
        testCases.add(new String[]{"(4 + 3) / (2 * (9 - 7))", "1.75"});
        testCases.add(new String[]{"5 / (7 - 7)", "Error"});
        testCases.add(new String[]{"6 * 3 / (2 + 1", "Error"});
        testCases.add(new String[]{"9 + (4 / 2) * 3 + 7", "22"});
        testCases.add(new String[]{"5 + (6 * (7 - 2) / 4", "Error"});
        testCases.add(new String[]{"2 * (3 + 4 / 2) - (8 - 2)", "4"});
        testCases.add(new String[]{"3 + (4 * (5 - 2) / 3) - 1", "6"});
        testCases.add(new String[]{"7 - (5 * 2) + (8 / (2 + 2)", "Error"});
        testCases.add(new String[]{"((3 + 2) * (7 - 4)) / (6 / 2)", "5"});
        testCases.add(new String[]{"2 * ((4 + 5) / 3) + (7 - 1)", "12"});
        testCases.add(new String[]{"1 + (6 - (2 * 3) / (2 + 1)", "Error"});

        testCases.add(new String[]{"10 * (25 / 5)", "50"});
        testCases.add(new String[]{"100 + (200 - 50) / 2", "175"});
        testCases.add(new String[]{"99 / (9 - 1)", "12.375"});
        testCases.add(new String[]{"1/0", "Error"});

        testCases.add(new String[]{"(2 + {3 - 1}) * (4 / 2)", "8"});
        testCases.add(new String[]{"{5 + (6 * (7 - 2) / 4)}", "12.5"});
        testCases.add(new String[]{"(3 + 2) * {7 - (4 / 2)} / (6 / 2)", "8.333333333333333"});

        testCases.add(new String[]{"(3 + {25 * (7 - 12)})", "-122"});
        testCases.add(new String[]{"(50 / (2 + 2)) * {10 - (3 + 4)}", "37.5"});
        testCases.add(new String[]{"(12 + 34) * 5 / (6 - {9 + (8 / 2)})", "-32.857142857142857"});
        testCases.add(new String[]{"(100 - (3 * {2 + 3})) * (6 / 2)", "255"});
        testCases.add(new String[]{"(4 + 3) / ({2 * (9 - 7)})", "1.75"});
        testCases.add(new String[]{"(2 + 1) * (8 - {3 + 7})", "-6"});
        testCases.add(new String[]{"((4 * {5 - 2}) + 1) / 3", "4.333333333333333"});
        testCases.add(new String[]{"{(6 * 3) / (2 + 1) * 2}", "12"});
        testCases.add(new String[]{"(5 + 2) * {7 - (4 / 2)}", "35"});
        testCases.add(new String[]{"(2 * (3 + {4 / 2})) - (8 - {2 * 1})", "4"});

        testCases.add(new String[]{"1000000000 + 2 - (50000 / 30)", "999998335.333333333333333"});

        boolean[] validateResult = new boolean[testCases.size()];

        for (int i = 0; i < testCases.size(); i++) {
            System.out.println("\nTest #" + (i + 1));

            String[] input = testCases.get(i);
            String expression = input[0];
            String testCaseResult = input[1];

            Result res = MyExpressionTest.evaluate(expression);
            if (res != null) {
                res.print();
            }
            if (res instanceof ErrorResult) {
                if (testCaseResult.equals("Error")) {
                    validateResult[i] = true;
                }
            }
            if (res instanceof FinalResult) {
                String resultStr = res.getResultString();
                if (testCaseResult.equals(resultStr)) {
                    validateResult[i] = true;
                }
            }
        }
        int failAt = -1;
        for (int i = 0; i < validateResult.length; i++) {
            if (!validateResult[i]) {
                failAt = i;
                break;
            }
        }
        if (failAt != -1) {
            System.out.println("Fail at test #" + (failAt + 1));
        } else {
            System.out.println("\n\n|All test are passed|");
        }
        System.out.println("------------------------------");
    }

}
