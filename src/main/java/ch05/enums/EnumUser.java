package ch05.enums;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

enum Colors {
    RED("piros"),
    GREEN("zöld"),
    BLUE("kék"),
    PURPLE(Set.of(RED, BLUE))
    ;

    private Set<Colors> colors = new HashSet<>();
    String hungarianName;

    Colors(String hun) {
        colors.add(this);
        hungarianName = hun;
    }

    Colors(Set<Colors> colorsSet) {
        this.colors = colorsSet;
        hungarianName = colorsSet.stream().map(Colors::getHungarianName).collect(Collectors.joining(","));
    }

    String getHungarianName() {
        return hungarianName;
    }

    @Override
    public String toString() {
        return colors.stream().map(e -> e.name()).collect(Collectors.joining(","));
    }

    public Colors getColor() {
        return this;
    }

    public Set<Colors> getColorSet() {
        return colors;
    }
}

public class EnumUser {
    public static void main(String[] args) {
        System.out.printf("PURPLE is basically: %s%n", Colors.PURPLE);
        System.out.printf("RED is basically: %s%n", Colors.RED);

        System.out.printf("Color of RED: %s%n", Colors.RED.getColor().name());
        System.out.printf("Colorset of RED: %s%n", Colors.RED.getColorSet());

        System.out.printf("Color of PURPLE: %s%n", Colors.PURPLE.getColor().name());
        System.out.printf("Colorset of PURPLE: %s%n", Colors.PURPLE.getColorSet());

        System.out.printf("PURPLE magyarul: %s%n", Colors.PURPLE.getHungarianName());

    }
}
