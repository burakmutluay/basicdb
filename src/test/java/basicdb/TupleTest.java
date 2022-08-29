package test.java.basicdb;

import main.java.basicdb.tuple.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class TupleTest {

    @Test
    public void getTupleDesc() {
        TupleDesc td = TestUtility.getTupleDesc(5);
        Tuple tup = new Tuple(td);
        assertEquals(td, tup.getTupleDesc());
    }

    @Test
    public void getAndSetField() {
        TupleDesc td = TestUtility.getTupleDesc(5);
        Tuple tup = new Tuple(td);

        assertNull(tup.getField(4));
        try {
            tup.getField(5);
            fail("Invalid index.  Should throw NoSuchElementException.");
        } catch (NoSuchElementException e) {
            // expected to get here
        } catch (Exception e) {
            fail("Threw some other kind of exception.  Should throw NoSuchElementException.");
        }

        tup.setField(4, new IntField(-1));
        assertEquals(new IntField(-1), tup.getField(4));

        try {
            tup.setField(5, new IntField(-1));
            fail("Invalid index.  Should throw NoSuchElementException.");
        } catch (NoSuchElementException e) {
            // expected to get here
        } catch (Exception e) {
            fail("Threw some other kind of exception.  Should throw NoSuchElementException.");
        }

        try {
            tup.setField(0, new StringField("field 0 should be INT"));
            fail("Incompatible types.  Should throw RuntimeException.");
        } catch (RuntimeException e) {
            // expected to get here
        } catch (Exception e) {
            fail("Threw some other kind of exception.  Should throw RuntimeException.");
        }
    }

    @Test
    public void modifyFields() {
        TupleDesc td = TestUtility.getTupleDesc(2);

        Tuple tup = new Tuple(td);
        tup.setField(0, new IntField(-1));
        tup.setField(1, new IntField(0));

        assertEquals(new IntField(-1), tup.getField(0));
        assertEquals(new IntField(0), tup.getField(1));

        tup.setField(0, new IntField(1));
        tup.setField(1, new IntField(37));

        assertEquals(new IntField(1), tup.getField(0));
        assertEquals(new IntField(37), tup.getField(1));

        try {
            tup.setField(0, new StringField("hello!")); // field 0 is of int type
            fail("Incompatible types; field 0 has INT_TYPE but trying to set it to STRING_TYPE.");
        } catch (RuntimeException e) {
            // expected
        } catch (Exception e) {
            fail("Threw some other kind of exception.  Should throw RuntimeException.");
        }
    }

    @Test
    public void testToString() {
        TupleDesc td = new TupleDesc(new Type[]{Type.INT_TYPE, Type.INT_TYPE, Type.STRING_TYPE});
        Tuple tup = new Tuple(td);
        tup.setField(0, new IntField(170));
        tup.setField(1, new IntField(99));
        tup.setField(2, new StringField("blah"));
        String tupStr = tup.toString();
        String expected = "170\t99\tblah";
        assertEquals(expected.length(), tupStr.length());  // should not end with a "\t"
        assertEquals(expected, tupStr);
    }

    @Test
    public void iterator() {
        TupleDesc td = new TupleDesc(new Type[]{Type.INT_TYPE, Type.STRING_TYPE});
        Tuple tup = new Tuple(td);
        tup.setField(0, new IntField(-1));
        tup.setField(1, new StringField("blech"));

        Iterator<Field> fieldIterator = tup.fields();
        assertTrue(fieldIterator.hasNext());
        Field next = fieldIterator.next();
        assertEquals(next.getType(), Type.INT_TYPE);
        assertEquals(next.toString(), "-1");

        assertTrue(fieldIterator.hasNext());
        next = fieldIterator.next();
        assertEquals(next.getType(), Type.STRING_TYPE);
        assertEquals(next.toString(), "blech");

        assertFalse(fieldIterator.hasNext());
    }

}