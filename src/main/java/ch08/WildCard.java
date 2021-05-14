package ch08;

import java.util.ArrayList;
import java.util.List;

class Ancestor {
    protected String entity;

    public Ancestor(String entity) {
        this.entity = entity;
    }

    public String doSomething() {
        return String.format("Something is done with a %s", entity);
    }
    
    
}

class Derived extends Ancestor {

    protected String way;

    public Derived(String entity, String how) {
        super(entity);
        this.way = how;
    }

    @Override
    public String doSomething() {
        return String.format("Something is done with a %s in a %s way", entity, way);
    }
}


public class WildCard {

    public static void executeAncestors(List<? extends Ancestor> lst) {
        lst.stream().map(Ancestor::doSomething).forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Derived> derivedList = new ArrayList<>();
        derivedList.add(new Derived("teddy bear", "cruel"));
        List<? extends Ancestor> variable = derivedList;

        executeAncestors(derivedList);
    }
}
