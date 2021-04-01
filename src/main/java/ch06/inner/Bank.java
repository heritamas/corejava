package ch06.inner;

public class Bank {

    public static void main(String[] args) {

        Account first = new Account(4623574, 1000);
        Account second = new Account(2365266, 1000);
        Account third = new Account(114549, 1000);


        first.transfer(second, 100);
        first.transfer(third, 100);
    }
}
