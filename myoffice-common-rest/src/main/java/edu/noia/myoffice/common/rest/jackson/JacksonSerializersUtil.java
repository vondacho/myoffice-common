package edu.noia.myoffice.common.rest.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JacksonSerializersUtil {
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
