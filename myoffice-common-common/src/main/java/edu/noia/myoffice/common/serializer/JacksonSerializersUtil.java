package edu.noia.myoffice.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;

public final class JacksonSerializersUtil {

    private JacksonSerializersUtil() {
    }

    public static void writeString(JsonGenerator gen, String value) throws IOException {
        if (value != null) {
            gen.writeString(value.trim());
        }
    }

    public static void writeNumber(JsonGenerator gen, Long value) throws IOException {
        if (value != null) {
            gen.writeNumber(value);
        }
    }
}
