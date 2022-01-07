package p2ch07;

import java.text.Collator;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {

  public static void main(String[] args) {
    Locale magyar = Locale.forLanguageTag("hu");

//    Arrays.stream(Locale.getAvailableLocales())
//        .map(name -> name.getDisplayName(name))
//        .forEach(System.out::println);
    ResourceBundle bundle = ResourceBundle.getBundle("p2ch07.PracticeBundle", magyar);
    NumberFormat numberFormat = (NumberFormat) bundle.getObject("currency");
    System.out.println(numberFormat.format(12345678.6780));
    String welcome = bundle.getString("welcome");
    System.out.println(MessageFormat.format(welcome, "Geza"));
    MessageFormat date = (MessageFormat) bundle.getObject("date");
    System.out.println(date.format(new Object[]{Date.from(Instant.now())}));
    String goodbye = bundle.getString("goodbye");
    System.out.println(goodbye);
    /*String number = NumberFormat.getCurrencyInstance(magyar).format(45723427.68857);
//    System.out.println(number);

    Collator collator = Collator.getInstance(magyar);
    List<String> words = Stream.of("érik", "erik", "szőlő", "hajlik", "vessző")
        .sorted(collator)
        .collect(Collectors.toList());
//    System.out.println(words);

    LocalDateTime rightNow = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT).withLocale(magyar);
    System.out.println(dateTimeFormatter.format(rightNow));
  }*/
}}
