package main.java.basicdb.tuple;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;

public interface Field extends Serializable {

    void serialize(DataOutputStream dos) throws IOException;

    public boolean compare(Operation operation, Field value);

    public Type getType();

    public int hashCode();

    public boolean equals(Object field);

    public String toString();
}
