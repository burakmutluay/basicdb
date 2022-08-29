package test.java.basicdb;

import main.java.basicdb.tuple.IntField;
import main.java.basicdb.tuple.Tuple;
import main.java.basicdb.tuple.TupleDesc;
import main.java.basicdb.tuple.Type;

import java.util.ArrayList;

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

    public static TupleDesc getTupleDesc(int n) {
        return new TupleDesc(getTypes(n));
    }

    public static Tuple getIntTuple(int[] values) {
        Tuple tuple = new Tuple(getTupleDesc(values.length));
        for (int i = 0; i < values.length; i++) {
            tuple.setField(i, new IntField(values[i]));
        }
        return tuple;
    }

    public static Tuple getIntTuple(int n) {
        return getIntTuple(new int[n]);
    }

    public static Tuple getIntTuple(int val, int n) {
        int[] values = new int[n];
        for (int i = 0; i < values.length; i++) {
            values[i] = val;
        }
        return getIntTuple(values);
    }

    public static Tuple getIntTuple(TupleDesc tupleDesc, int[] data) {
        Tuple tuple = new Tuple(tupleDesc);
        for (int i = 0; i < data.length; i++) {
            tuple.setField(i, new IntField(data[i]));
        }
        return tuple;
    }

    public static String listToString(ArrayList<Integer> list) {
        String out = "";
        for (Integer i : list) {
            if (out.length() > 0) out += "\t";
            out += i;
        }
        return out;
    }




}
