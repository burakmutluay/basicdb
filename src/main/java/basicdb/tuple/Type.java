package main.java.basicdb.tuple;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;

public enum Type implements Serializable {
    INT_TYPE() {
        @Override
        public int getLen() {
            return 4;
        }

        @Override
        public Field parse(DataInputStream dataInputStream) {
            try {
                return new IntField(dataInputStream.readInt());
            } catch (IOException e) {
                throw new RuntimeException("Error reading from stream", new ParseException("Could not parse", 0));
            }
        }
    }, STRING_TYPE() {
        @Override
        public int getLen() {
            return STRING_LEN + 4;
        }

        @Override
        public Field parse(DataInputStream dataInputStream) {
            try {
                int strLen = dataInputStream.readInt();
                byte bs[] = new byte[strLen];
                dataInputStream.read(bs);
                dataInputStream.skipBytes(STRING_LEN - strLen);
                return new StringField(new String(bs), STRING_LEN);
            } catch (IOException e) {
                throw new RuntimeException("Error reading from stream", new ParseException("Could not parse", 0));
            }
        }

    };


    public static final int STRING_LEN = 128;

    public abstract int getLen();

    public abstract Field parse(DataInputStream dataInputStream);
}
