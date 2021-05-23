package ch09;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public interface LeapDay {
    boolean hasLeapDay(int year) ;
}

class LeapDayIf implements LeapDay {
    @Override
    public boolean hasLeapDay(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}

class LeapDayWithoutIf implements LeapDay {

    static class Rule<T>  {
        private Map<Predicate<T>, Rule<T>> rules = new LinkedHashMap<>();

        Rule() {}

        static <T> Rule<T>  of(Predicate<T> pred, Rule trueRule, Rule falseRule) {
            return new Rule().addRule(pred, trueRule, falseRule);
        }

        Rule<T> addRule(Predicate<T> pred, Rule trueRule, Rule falseRule) {
            rules.put(pred, trueRule);
            rules.put(pred.negate(), falseRule);
            return this;
        }

        boolean evaluate(T input) {
            for (var pred : rules.keySet()) {
                if (pred.test(input)) {
                    return rules.get(pred).evaluate(input);
                }
            }
            return false;
        }
    }

    static class ConstRule<T> extends Rule<T> {
        private final boolean result;

        static Rule of(boolean arg) {
            return new ConstRule(arg);
        }

        ConstRule(boolean arg) {
            result = arg;
        }

        @Override
        boolean evaluate(T input) {
            return result;
        }
    }

    private Rule rule400 = Rule.of((Integer i) -> i % 400 == 0, ConstRule.of(true), ConstRule.of(false));
    private Rule rule100 = Rule.of((Integer i) -> i % 100 == 0, rule400, ConstRule.of(true));
    private Rule rule4 = Rule.of((Integer i) -> i % 4 == 0, rule100, ConstRule.of(false));

    @Override
    public boolean hasLeapDay(int year) {
        return rule4.evaluate(year);
    }
}


class LeapDayRunner {

    public static void main(String[] args) {
        LeapDay ld = new LeapDayWithoutIf();
        //LeapDay ld = new LeapDayIf();

        System.out.format("%d is leap-year? %b%n", 2020, ld.hasLeapDay(2020));
        System.out.format("%d is leap-year? %b%n", 2000, ld.hasLeapDay(2000));
        System.out.format("%d is leap-year? %b%n", 2021, ld.hasLeapDay(2021));
        System.out.format("%d is leap-year? %b%n", 1900, ld.hasLeapDay(1900));


    }

}