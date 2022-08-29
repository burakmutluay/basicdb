package main.java.basicdb.tuple;

import main.java.basicdb.page.PageId;

import java.io.Serializable;

public class RecordId implements Serializable {

    private static final long serialVersionUID = 1L;

    public RecordId(PageId pageId, int tupleNo) {
        throw new UnsupportedOperationException("Implement me!");
    }

    public int getTupleNo() {
        throw new UnsupportedOperationException("Implement me!");
    }

    public PageId getPageId() {
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Implement me!");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Implement me!");
    }
}
