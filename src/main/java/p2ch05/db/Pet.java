package p2ch05.db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Pet {

    private Integer id;
    private LocalDate ownedSince;
    private String petName;
    private String species;
    private List<Food> eats = new ArrayList<>();
    private Owner owner;

    public Pet() {
    }

    public Pet(LocalDate ownedSince, String petName, String species, Owner owner) {
        this.ownedSince = ownedSince;
        this.petName = petName;
        this.species = species;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getOwnedSince() {
        return ownedSince;
    }

    public void setOwnedSince(LocalDate ownedSince) {
        this.ownedSince = ownedSince;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public List<Food> getEats() {
        return eats;
    }

    public void setEats(List<Food> eats) {
        this.eats = eats;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pet.class.getSimpleName() + "[", "]")
                .add("ownedSince=" + ownedSince)
                .add("petName='" + petName + "'")
                .add("species='" + species + "'")
                .add("eats=" + eats)
                .toString();
    }
}
