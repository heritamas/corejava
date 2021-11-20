package p2ch05.db;

import java.util.List;
import java.util.StringJoiner;

public class Owner {

    protected String city;
    protected String name;
    protected String phone;
    protected List<Pet> pets;

    public Owner(String city, String name, String phone) {
        this.city = city;
        this.name = name;
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Owner.class.getSimpleName() + "[", "]")
                .add("city='" + city + "'")
                .add("name='" + name + "'")
                .add("phone='" + phone + "'")
                .add("pets=" + pets)
                .toString();
    }
}
