package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        int power = (int)Math.pow(operandTwo.getValue(), operandOne.getValue());
        Operand result = new Operand(power);
        return result;
    }
}
