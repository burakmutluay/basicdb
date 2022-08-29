package main.java.basicdb.tuple;

import java.io.DataOutputStream;
import java.io.IOException;

public class StringField implements Field {

    private static final long serialVersionUID = 1L;

    private final String value;

    private final int maxSize;

    private String getValue() {
        return value;
    }

    public StringField(String s, int maxSize) {
        this.maxSize = maxSize;

        if (s.length() > maxSize)
            value = s.substring(0, maxSize);
        else
            value = s;
    }

    public StringField(String s) {
        this(s, Type.STRING_LEN);
    }

    public String toString() {
        return value;
    }

    public int hashCode() {
        return value.hashCode();
    }

    public boolean equals(Object field) {
        return ((StringField) field).value.equals(value);
    }

    public void serialize(DataOutputStream dataOutputStream) throws IOException {
        String s = value;
        int overflow = maxSize - s.length();
        if (overflow < 0) {
            String news = s.substring(0, maxSize);
            s = news;
        }

        dataOutputStream.writeInt(s.length());
        dataOutputStream.writeBytes(s);
        while (overflow-- > 0)
            dataOutputStream.write((byte) 0);
    }

    public boolean compare(Operation operation, Field value) {
        StringField iVal = (StringField) value;
        int cmpVal = this.value.compareTo(iVal.value);

        switch (operation) {
            case EQUALS:
                return cmpVal == 0;
            case GREATER_THAN:
                return cmpVal > 0;
            case LESS_THAN:
                return cmpVal < 0;
            case LESS_THAN_OR_EQ:
                return cmpVal <= 0;
            case GREATER_THAN_OR_EQ:
                return cmpVal >= 0;
            case LIKE:
                return this.value.contains(iVal.value);
            case NOT_EQUALS:
                return cmpVal != 0;
        }
        return false;
    }

    public Type getType() {
        return Type.STRING_TYPE;
    }



}
