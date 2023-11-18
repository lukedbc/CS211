package Project2;

// Result class
// 1. Result Class: base result class
// 2. ErrorResult is used when we got unexpected result such as invalid expression, incomplete expression,..
// 3. SuccessResult is used when ce got expected result  such as valid expression, convert infix to posfix successfully,...
public class Result {

    public static class ErrorResult extends Result {

        private final int indexOccurError;
        private String expression;

        public ErrorResult() {
            super(-1, null);
            this.indexOccurError = -1;
        }

        public ErrorResult(int errorCode) {
            super(errorCode, null);
            this.indexOccurError = -1;
        }

        public ErrorResult(int errorCode, String expression, int indexOccurError) {
            super(errorCode, null);
            this.indexOccurError = indexOccurError;
            this.expression = expression;
        }

        private String calculateIndent() {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < indexOccurError; i++) {
                res.append(" ");
            }
            return res.toString();
        }

        @Override
        public void print() {
            if (indexOccurError <= -1) {
                System.out.println("Error: " + Constant.getErrorMessage(getErrorCode()));
                return;
            }
            String errorMessage = "^ " + Constant.getErrorMessage(getErrorCode());
            System.out.printf("%s\n%s%s\n", expression, calculateIndent(), errorMessage);
        }
    }

    public static class SuccessResult extends Result {

        public SuccessResult(String resultString) {
            super(-1, resultString);
        }

        @Override
        public void print() {
            System.out.println("Result: " + getResultString());
        }
    }

    public static class FinalResult extends SuccessResult {

        private final String infix;
        private final String postfix;

        public FinalResult(String infix, String postfix, String result) {
            super(result);
            this.infix = infix;
            this.postfix = postfix;
        }

        public String getInfix() {
            return infix;
        }

        public String getPostfix() {
            return postfix;
        }

        @Override
        public void print() {
            System.out.println("Infix: " + getInfix());
            System.out.println("Postfix: " + getPostfix());
            System.out.println("Result: " + getResultString());
        }
    }

    private final int errorCode;
    private final String resultString;

    public Result(int errorCode, String result) {
        this.errorCode = errorCode;
        this.resultString = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getResultString() {
        return resultString;
    }

    public void print() {
        System.out.println("Result: " + resultString);
    }
}
