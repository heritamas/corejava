package extmod.impl;

import extmod.intf.DateService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateService implements DateService {
    @Override
    public String getDate() {
        return LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    }
}
