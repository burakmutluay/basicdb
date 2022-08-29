package test.java.basicdb;

import main.java.basicdb.tuple.TupleDesc;
import main.java.basicdb.tuple.Type;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


public class TupleDescTest {

    @Test
    public void constructorNamed() {
        TupleDesc tupleDesc = TestUtility.getTupleDesc(2, "myFieldName");

        assertEquals(Type.INT_TYPE, tupleDesc.getFieldType(0));
        assertEquals(Type.INT_TYPE, tupleDesc.getFieldType(1));
        assertEquals("myFieldName0", tupleDesc.getFieldName(0));
        assertEquals("myFieldName1", tupleDesc.getFieldName(1));

    }

    @Test
    public void constructorUnNamed() {
        TupleDesc td = TestUtility.getTupleDesc(1);
        assertEquals(Type.INT_TYPE, td.getFieldType(0));
        assertEquals("", td.getFieldName(0));
    }

    @Test
    public void numFields() {
        int[] lengths = new int[]{1, 2, 1000};

        for (int len : lengths) {
            TupleDesc td = TestUtility.getTupleDesc(len);
            assertEquals(len, td.numFields());
        }
    }

    @Test
    public void getFieldType() {
        int[] lengths = new int[]{1, 2, 1000};

        for (int len : lengths) {
            TupleDesc td = TestUtility.getTupleDesc(len);
            for (int i = 0; i < len; ++i)
                assertEquals(Type.INT_TYPE, td.getFieldType(i));
        }

        TupleDesc td = TestUtility.getTupleDesc(10);
        try {
            td.getFieldType(11);
            fail("Invalid index.  Should throw NoSuchElementException.");
        } catch (IndexOutOfBoundsException e) {
            fail("Threw IndexOutOfBoundsException on invalid index.  Should throw NoSuchElementException instead.");
        } catch (NoSuchElementException e) {
            // expected to get here
        } catch (Exception e) {
            fail("Threw some other kind of exception.  Should throw NoSuchElementException.");
        }
    }

    @Test
    public void getFieldName() {
        TupleDesc td = TestUtility.getTupleDesc(3, "td");
        assertEquals("td0", td.getFieldName(0));
        assertEquals("td2", td.getFieldName(2));
        try {
            td.getFieldName(3);
            fail("Invalid index.  Should throw NoSuchElementException.");
        } catch (IndexOutOfBoundsException e) {
            fail("Threw IndexOutOfBoundsException on invalid index.  Should throw NoSuchElementException instead.");
        } catch (NoSuchElementException e) {
            // expected to get here
        } catch (Exception e) {
            fail("Threw some other kind of exception.  Should throw NoSuchElementException.");
        }
    }


    @Test
    public void fieldNameToIndex() {
        int[] lengths = new int[]{1, 2, 1000};
        String prefix = "test";

        for (int len : lengths) {
            // Make sure you retrieve well-named fields
            TupleDesc td = TestUtility.getTupleDesc(len, prefix);
            for (int i = 0; i < len; ++i) {
                assertEquals(i, td.fieldNameToIndex(prefix + i));
            }

            // Make sure you throw exception for non-existent fields
            try {
                td.fieldNameToIndex("foo");
                fail("foo is not a valid field name");
            } catch (NoSuchElementException e) {
                // expected to get here
            } catch (Exception e) {
                fail("Threw some other kind of exception.  Should throw NoSuchElementException.");
            }

            // Make sure you throw exception for null searches
            try {
                td.fieldNameToIndex(null);
                fail("null is not a valid field name");
            } catch (NoSuchElementException e) {
                // expected to get here
            } catch (Exception e) {
                fail("Threw some other kind of exception.  Should throw NoSuchElementException.");
            }

            // Make sure you throw exception when all field names are null
            td = new TupleDesc(new Type[]{Type.INT_TYPE, Type.INT_TYPE}, new String[2]);
            try {
                td.fieldNameToIndex(prefix);
                fail("no fields are named, so you can't find it");
            } catch (NoSuchElementException e) {
                // expected to get here
            }
        }
    }


    @Test
    public void getSize() {
        TupleDesc td;

        // create a tupledesc with string types
        td = new TupleDesc(new Type[] {
                Type.STRING_TYPE, Type.STRING_TYPE,
                Type.STRING_TYPE, Type.STRING_TYPE});
        assertEquals(4 * Type.STRING_TYPE.getLen(), td.getSize());

        int[] lengths = new int[]{1, 2, 1000};

        for (int len : lengths) {
            td = TestUtility.getTupleDesc(len);
            assertEquals(len * Type.INT_TYPE.getLen(), td.getSize());
        }

        // create a tupledesc of mixed type
        td = new TupleDesc(new Type[] { Type.INT_TYPE, Type.STRING_TYPE, Type.INT_TYPE});
        assertEquals(2L * Type.INT_TYPE.getLen() + Type.STRING_TYPE.getLen(), td.getSize());
    }


    @Test
    public void iterator() {
        TupleDesc td = new TupleDesc(new Type[]{Type.INT_TYPE, Type.STRING_TYPE}, new String[]{"fieldA", "fieldB"});
        Iterator<TupleDesc.TDItem> tdItemIterator = td.iterator();
        assertTrue(tdItemIterator.hasNext());
        TupleDesc.TDItem next = tdItemIterator.next();
        assertEquals(next.fieldType, Type.INT_TYPE);
        assertEquals(next.fieldName, "fieldA");

        assertTrue(tdItemIterator.hasNext());
        next = tdItemIterator.next();
        assertEquals(next.fieldType, Type.STRING_TYPE);
        assertEquals(next.fieldName, "fieldB");

        assertFalse(tdItemIterator.hasNext());
    }

    @Test
    public void equals() {
        TupleDesc singleInt = new TupleDesc(new Type[]{Type.INT_TYPE}, new String[]{"field0"});
        TupleDesc singleInt2 = new TupleDesc(new Type[]{Type.INT_TYPE}, new String[]{"firstField"});
        TupleDesc singleString = new TupleDesc(new Type[]{Type.STRING_TYPE});
        TupleDesc twoInts = new TupleDesc(new Type[]{Type.INT_TYPE, Type.INT_TYPE}, new String[]{"firstField", "secondField"});

        // .equals() with null should return false
        assertNotEquals(null, singleInt);

        // .equals() with the wrong type should return false
        assertNotEquals(singleInt, new Object());

        assertEquals(singleInt, singleInt);
        assertEquals(singleInt, singleInt2);       // can differ on field name
        assertEquals(singleInt2, singleInt);
        assertEquals(singleString, singleString);

        assertNotEquals(singleInt, singleString);
        assertNotEquals(singleInt2, singleString);
        assertNotEquals(singleString, singleInt);
        assertNotEquals(singleString, singleInt2);

        assertNotEquals(singleInt, twoInts);
        assertNotEquals(singleInt2, twoInts);
        assertNotEquals(singleString, twoInts);
        assertNotEquals(twoInts, singleInt);
        assertNotEquals(twoInts, singleInt2);
        assertNotEquals(twoInts, singleString);
    }

    @Test
    public void testToString() {
        TupleDesc td = new TupleDesc(new Type[]{Type.INT_TYPE, Type.STRING_TYPE}, new String[]{"fieldA", "fieldB"});
        assertEquals("fieldA(INT_TYPE), fieldB(STRING_TYPE)", td.toString());
    }

    @Test
    public void merge() {
        TupleDesc td1, td2, td3;

        td1 = TestUtility.getTupleDesc(1, "td1");
        td2 = TestUtility.getTupleDesc(2, "td2");

        // test td1.merge(td2)
        td3 = TupleDesc.merge(td1, td2);
        assertEquals(3, td3.numFields());
        assertEquals(3 * Type.INT_TYPE.getLen(), td3.getSize());
        for (int i = 0; i < 3; ++i)
            assertEquals(Type.INT_TYPE, td3.getFieldType(i));
        assertTrue(combinedStringArrays(td1, td2, td3));

        // test td2.merge(td1)
        td3 = TupleDesc.merge(td2, td1);
        assertEquals(3, td3.numFields());
        assertEquals(3 * Type.INT_TYPE.getLen(), td3.getSize());
        for (int i = 0; i < 3; ++i)
            assertEquals(Type.INT_TYPE, td3.getFieldType(i));
        assertTrue(combinedStringArrays(td2, td1, td3));

        // test td2.merge(td2)
        td3 = TupleDesc.merge(td2, td2);
        assertEquals(4, td3.numFields());
        assertEquals(4 * Type.INT_TYPE.getLen(), td3.getSize());
        for (int i = 0; i < 4; ++i)
            assertEquals(Type.INT_TYPE, td3.getFieldType(i));
        assertTrue(combinedStringArrays(td2, td2, td3));
    }

    /**
     * Ensures that merged tupledesc's field names = td1's field names + td2's field names
     */
    private boolean combinedStringArrays(TupleDesc td1, TupleDesc td2, TupleDesc combined) {
        for (int i = 0; i < td1.numFields(); i++) {
            if (!(((td1.getFieldName(i) == null) && (combined.getFieldName(i) == null)) ||
                    td1.getFieldName(i).equals(combined.getFieldName(i)))) {
                return false;
            }
        }

        for (int i = td1.numFields(); i < td1.numFields() + td2.numFields(); i++) {
            if (!(((td2.getFieldName(i - td1.numFields()) == null) && (combined.getFieldName(i) == null)) ||
                    td2.getFieldName(i - td1.numFields()).equals(combined.getFieldName(i)))) {
                return false;
            }
        }

        return true;
    }


}
