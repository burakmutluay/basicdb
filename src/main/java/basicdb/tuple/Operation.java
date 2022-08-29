package main.java.basicdb.tuple;

import java.io.Serializable;

public enum Operation implements Serializable {

    EQUALS, GREATER_THAN, LESS_THAN, LESS_THAN_OR_EQ, GREATER_THAN_OR_EQ, LIKE, NOT_EQUALS;

    public static Operation getOperation(int i) {
        return values()[i];
    }

    public String toString() {
        switch (this) {
            case EQUALS:
                return "=";
            case GREATER_THAN:
                return ">";
            case LESS_THAN:
                return "<";
            case LESS_THAN_OR_EQ:
                return "<=";
            case GREATER_THAN_OR_EQ:
                return ">=";
            case LIKE:
                return "LIKE";
            case NOT_EQUALS:
                return "<>";
            default:
                throw new IllegalStateException("Enter a valid operation");
        }
    }
}
