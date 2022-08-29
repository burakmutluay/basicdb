package main.java.basicdb.page;

public interface PageId {

    int getTableId();

    int hashCode();

    boolean equals(Object o);

    int[] serialize();

}
