package se.isselab.HAnS.codeAnnotation;

import java.util.ArrayList;

public class FeatureModelTreeLO extends FeatureModelTree{
    enum Operators{
        OR,
        XOR
    }
    private Operators Operator;
    private ArrayList<FeatureModelTreeLO> Operands;

    public FeatureModelTreeLO(FeatureModelTree parent, String Operator) {
        super(parent);
        setOperatorfromSring(Operator);
    }

    public FeatureModelTreeLO(FeatureModelTree parent, String name , String Operator) {
        super(parent, name);
        setOperatorfromSring(Operator);
    }

    public FeatureModelTreeLO(FeatureModelTree parent, String name, int line, boolean isOptional, String Operator) {
        super(parent, name, line, isOptional);
        setOperatorfromSring(Operator);
    }

    private void setOperatorfromSring(String Operator){
        switch (Operator.toLowerCase()) {
            case "or":
                this.Operator = Operators.OR;
                break;
            case "xor":
                this.Operator = Operators.XOR;
                break;
        }
    }

    public void setOperands(ArrayList<FeatureModelTreeLO> operands) {
        Operands = operands;
    }
    public ArrayList<FeatureModelTreeLO> getOperands() {
        return Operands;
    }
    public String getOperator() {
        return Operator.toString();
    }
}
