package org.java.java8;

public enum OperationVersion1 {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };
    private final String symbol;

    OperationVersion1(String symbol) {
        this.symbol = symbol;

    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);

    public static void main(String[] args) {
        double operand1 = 4, operand2 = 5;
        double sumNumber = OperationVersion1.PLUS.apply(2, 3);
        double substractionNum = OperationVersion1.MINUS.apply(2, 3);
        double multiplicationNumber = OperationVersion1.TIMES.apply(2, 3);
        System.out.println("sum="+sumNumber +" subsraction="+substractionNum);

    }
}
