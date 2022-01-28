package extmod.impl;

import extmod.intf.DateService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class LocalDateTimeService implements DateService {
    @Override
    public String getDate() {
        return LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
}
