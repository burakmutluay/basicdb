package main.java.basicdb.tuple;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TupleDesc implements Serializable {

    public static class TDItem implements Serializable {

        private static final long serialVersionUID = 1L;

        public final Type fieldType;

        public final String fieldName;

        public TDItem(Type fieldType, String fieldName) {
            this.fieldType = fieldType;
            this.fieldName = fieldName;
        }

        public String toString() {
            return fieldName + "(" + fieldType + ")";
        }
    }

    private static final long serialVersionUID = 1L;

    private TDItem[] itemArr;

    private int size;

    public TupleDesc(Type[] typeArr, String[] fieldArr) {
        throw new UnsupportedOperationException("implement me!");
    }

    public TupleDesc(Type[] typeArr) {
        throw new UnsupportedOperationException("implement me!");
    }

    public int numFields() {
        throw new UnsupportedOperationException("implement me!");
    }

    public Type getFieldType(int i) throws NoSuchElementException {
        throw new UnsupportedOperationException("implement me!");
    }

    public String getFieldName(int i) throws NoSuchElementException {
        throw new UnsupportedOperationException("implement me!");
    }

    public int fieldNameToIndex(String name) throws NoSuchElementException {
        throw new UnsupportedOperationException("implement me!");
    }

    public int getSize() {
        throw new UnsupportedOperationException("implement me!");
    }

    public Iterator<TDItem> iterator() {
        throw new UnsupportedOperationException("implement me!");
    }

    public boolean equals(Object o) {
        throw new UnsupportedOperationException("implement me!");
    }

    public int hashCode() {
        throw new UnsupportedOperationException("implement me!");
    }

    public String toString() {
        throw new UnsupportedOperationException("implement me!");
    }

    public static TupleDesc merge(TupleDesc td1, TupleDesc td2) {
        throw new UnsupportedOperationException("implement me!");
    }

}
