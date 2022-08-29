package main.java.basicdb.tuple;

import java.io.Serializable;
import java.util.Iterator;

public class Tuple implements Serializable {

    private static final long serialVersionUID = 1L;

    private TupleDesc tupleDesc;
    private RecordId recordId;
    private Field[] fields;

    public Tuple(TupleDesc tupleDesc){
        throw new UnsupportedOperationException("implement me!");
    }

    public TupleDesc getTupleDesc() {
        throw new UnsupportedOperationException("implement me!");
    }

    public void setField(int i, Field f) {
        throw new UnsupportedOperationException("implement me!");
    }

    public Field getField(int i) {
        throw new UnsupportedOperationException("implement me!");
    }

    public String toString() {
        throw new UnsupportedOperationException("implement me!");
    }

    public Iterator<Field> fields() {
        throw new UnsupportedOperationException("implement me!");
    }

    public RecordId getRecordId() {
        return recordId;
    }

    public void setRecordId(RecordId recordId) {
        this.recordId = recordId;
    }
}
