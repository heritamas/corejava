package p2ch06;

import org.mockito.cglib.core.Local;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateAndTime {

    public static void main(String[] args) throws InterruptedException {

        LocalDate today = LocalDate.now();

        // how old are you?


        // in days?


        // on which day were you born?


        // WTF is this ????
        System.out.println("Új java:");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE", Locale.forLanguageTag("hu"));
        System.out.printf("Az Úr 1582-ik esztedejének október hava 14-én, %s%n",
                LocalDate.of(1582, Month.OCTOBER, 14).format(dtf));
        System.out.printf("Az Úr 1582-ik esztedejének október hava 15-én, %s%n",
                LocalDate.of(1582, Month.OCTOBER, 15).format(dtf));

        System.out.println("Régi java:");
        SimpleDateFormat df = new SimpleDateFormat("EEEE", Locale.forLanguageTag("hu"));
        Date greg_date14 = (new GregorianCalendar(1582, Calendar.OCTOBER, 14)).getTime();
        System.out.printf("Az Úr 1582-ik esztedejének október hava 14-én, %s%n",
                df.format(greg_date14));

        Date greg_date15 = (new GregorianCalendar(1582, Calendar.OCTOBER, 15)).getTime();
        System.out.printf("Az Úr 1582-ik esztedejének október hava 15-én, %s%n",
                df.format(greg_date15));

        // next of same day?


        LocalTime rightNow = LocalTime.now();

        // what time is it in US EAST time


        // print date time in german

    }
}
