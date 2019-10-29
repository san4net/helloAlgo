package com.java8;

import java.util.function.DoubleBinaryOperator;

public enum OperationVersion2 {

    PLUS("+", Double::sum),
    MINUS("-", (double a, double b)->{return  a-b;}) ,
    TIMES("*", (double a, double b)->{return  a*b;}) ;

    private final String symbol;
    private final DoubleBinaryOperator doubleBinaryOperator;

    OperationVersion2(String symbol, DoubleBinaryOperator doubleBinaryOperator) {
        this.symbol = symbol;
        this.doubleBinaryOperator = doubleBinaryOperator;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public double apply(double x, double y){
        return doubleBinaryOperator.applyAsDouble(x,y);
    }

    public static void main(String[] args) {
        double operand1 = 4, operand2 = 5;
        double sumNumber = OperationVersion2.PLUS.apply(2, 3);
        double substractionNum = OperationVersion2.MINUS.apply(2, 3);
        double multiplicationNumber = OperationVersion2.TIMES.apply(2, 3);
        System.out.println("sum="+sumNumber +" subsraction="+substractionNum +" multiply="+multiplicationNumber);

    }
}
