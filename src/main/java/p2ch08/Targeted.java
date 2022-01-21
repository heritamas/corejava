package p2ch08;

public class Targeted {

    public void greet() {
        System.out.println("Hello, world!");
    }

    public static void main(String[] args) {
        Targeted t = new Targeted();

        t.greet();
    }
}
