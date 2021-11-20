package p2ch05.db;

import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class Pet {

    protected LocalDate ownedSince;
    protected String petName;
    protected String species;
    protected List<Food> eats;
    protected Owner owner;


    public Pet(LocalDate ownedSince, String petName, String species, Owner owner) {
        this.ownedSince = ownedSince;
        this.petName = petName;
        this.species = species;
        this.owner = owner;
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
                .add("owner=" + owner)
                .toString();
    }
}
