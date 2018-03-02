package edu.noia.myoffice.common.util.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Converters {

    private Converters() {
    }

    public static LocalDateTime toLocalDateTimeUTC(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
