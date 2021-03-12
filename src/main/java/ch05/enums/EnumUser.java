package ch05.enums;

enum Colors {
    RED,
    PURPLE,
    GREEN,
    BLUE
}

public class EnumUser {
    public static void main(String[] args) {
        System.out.printf("PURPLE is basically: %s%n", Colors.PURPLE);
    }
}
