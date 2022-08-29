package test.java.basicdb;

import main.java.basicdb.tuple.TupleDesc;
import main.java.basicdb.tuple.Type;

public class TestUtility {

    public static Type[] getTypes(int len) {
        Type[] types = new Type[len];
        for (int i = 0; i<len; ++i)
            types[i] = Type.INT_TYPE;
        return types;
    }

    public static String[] getStrings(int len, String val) {
        String[] strings = new String[len];
        for (int i = 0; i < len; i++) {
             strings[i] = val + i;
        }
        return strings;
    }

    public static TupleDesc getTupleDesc(int n, String name) {
        return new TupleDesc(getTypes(n), getStrings(n, name));
    }




}
