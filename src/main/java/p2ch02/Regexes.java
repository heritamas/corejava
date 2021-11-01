package p2ch02;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexes {

    static class MyDate {
        Integer year;
        Integer month;
        Integer day;
        Integer hour;
        Integer minute;
        Integer second;

        public MyDate() {
        }

        public MyDate(String year, String month, String day, String hour, String minute, String second) {
            this.year = Integer.valueOf(year);
            this.month = Integer.valueOf(month);
            this.day = Integer.valueOf(day);
            this.hour = Integer.valueOf(hour);
            this.minute = Integer.valueOf(minute);
            this.second = Integer.valueOf(second);
        }

        @Override
        public String toString() {
            return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
        }
    }
    
    private static MyDate parseDate(String date) {
        Pattern pattern = Pattern.compile("(?<year>\\d{4})(.|-)(?<month>\\d{1,2})(.|-)(?<day>\\d{1,2})(.|-)\\s(?<hour>\\d{1,2}):(?<min>\\d{1,2}):(?<sec>\\d{1,2})");
        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return new MyDate(matcher.group("year"), matcher.group("month"), matcher.group("day"), matcher.group("hour"), matcher.group("min"), matcher.group("sec"));
        }
        return new MyDate();
    }
    
    private static List<String> split4Chars(String input) {
        return List.of(input.split("(?<=\\G.{4})"));
    }

    // substitute ${key} values from Map in a string template
    private static String interpolate(String template, Map<String, String> dictionary) {
        return "";
    }

    public static void main(String[] args) {
//        System.out.println(parseDate("2021.10.22. 11:14:33"));
//        System.out.println(parseDate("20sfg21.10.22. 11:1ff4:33"));
//        System.out.println(parseDate("2021.6.3. 11:2:33"));

        //System.out.println(split4Chars("ksdfjga adfga ASDGADFb wrgesw =p;;vpew "));
        System.out.println(interpolate("Foo is ${foo}, bar is ${bar}, baz is ${baz}", Map.of("foo", "fú", "bar", "bár")));

    }
}
