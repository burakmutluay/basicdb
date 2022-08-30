package main.java.basicdb.tuple;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

public class Tuple implements Serializable {

    private static final long serialVersionUID = 1L;

    private TupleDesc tupleDesc;
    private RecordId recordId;
    private Field[] fields;

    public Tuple(TupleDesc tupleDesc){
        this.tupleDesc = tupleDesc;
    }

    public TupleDesc getTupleDesc() {
        return this.tupleDesc;
    }

    public void setField(int i, Field f) {
        this.fields[i] = f;
    }

    public Field getField(int i) {
        return this.fields[i];
    }

    public String toString() {
        return "Tuple{" +
                "tupleDesc=" + tupleDesc +
                ", recordId=" + recordId +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }

    public Iterator<Field> fields() {
        return Arrays.stream(this.fields).iterator();
    }

    public RecordId getRecordId() {
        return recordId;
    }

    public void setRecordId(RecordId recordId) {
        this.recordId = recordId;
    }
}
