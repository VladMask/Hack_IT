package grsu.by.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
@PropertySource("classpath:application.properties")
public final class OffsetDateTimeFormatter {

    @Value("{offset_date_time.format}")
    private static String FORMAT;

    public static OffsetDateTime getOffsetDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        return OffsetDateTime.parse(date, formatter);
    }
}
