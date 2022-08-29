package main.java.basicdb.tuple;

import java.io.DataOutputStream;
import java.io.IOException;

public class IntField implements Field {

    private static final long serialVersionUID = 1L;

    private final int value;

    public int getValue() {
        return value;
    }
    public IntField(int i) {
        value = i;
    }

    public String toString() {
        return Integer.toString(value);
    }

    public boolean equals(Object field) {
        return (field instanceof IntField) && (((IntField) field).value == value);
    }

    public int hashCode() {
        return value;
    }

    @Override
    public void serialize(DataOutputStream dos) throws IOException {
        dos.writeInt(value);
    }

    @Override
    public boolean compare(Operation operation, Field value) {

        IntField iVal = (IntField) value;

        switch (operation) {
            case EQUALS:
            case LIKE:
                return this.value == iVal.value;
            case GREATER_THAN:
                return this.value > iVal.value;
            case LESS_THAN:
                return this.value < iVal.value;
            case LESS_THAN_OR_EQ:
                return this.value <= iVal.value;
            case GREATER_THAN_OR_EQ:
                return this.value >= iVal.value;
            case NOT_EQUALS:
                return this.value != iVal.value;
        }

        return false;
    }

    public Type getType() {
        return Type.INT_TYPE;
    }
}
