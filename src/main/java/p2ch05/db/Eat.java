package p2ch05.db;

import java.util.StringJoiner;

public class Eat {

    private Pet pet;
    private Food eats;

    public Eat() {
    }

    public Eat(Pet pet, Food eats) {
        this.pet = pet;
        this.eats = eats;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Food getEats() {
        return eats;
    }

    public void setEats(Food eats) {
        this.eats = eats;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Eat.class.getSimpleName() + "[", "]")
                .add("pet=" + pet)
                .add("eats=" + eats)
                .toString();
    }
}
