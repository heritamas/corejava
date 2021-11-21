package p2ch05.db;

import java.io.Serializable;
import java.util.StringJoiner;

public class Food implements Serializable {

    private Integer id;
    private String foodName;

    public Food() {
    }

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public Integer getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Food.class.getSimpleName() + "[", "]")
                .add("foodName='" + foodName + "'")
                .toString();
    }
}
