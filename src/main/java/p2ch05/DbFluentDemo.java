package p2ch05;

import org.codejargon.fluentjdbc.api.FluentJdbc;
import org.codejargon.fluentjdbc.api.FluentJdbcBuilder;
import org.codejargon.fluentjdbc.api.mapper.ObjectMapperRsExtractor;
import org.codejargon.fluentjdbc.api.mapper.ObjectMappers;
import org.codejargon.fluentjdbc.api.query.Mapper;
import org.codejargon.fluentjdbc.api.query.Query;
import org.postgresql.ds.PGSimpleDataSource;
import p2ch05.db.Eat;
import p2ch05.db.Food;
import p2ch05.db.Owner;
import p2ch05.db.Pet;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class DbFluentDemo {

    private static Set<Owner> owners = new HashSet<>();
    private static Set<Pet> pets = new HashSet<>();
    private static Set<Food> foods = new HashSet<>();
    private static Set<Eat> eats = new HashSet<>();

    private static ObjectMappers objectMappers;

    static {
        Map<Class, ObjectMapperRsExtractor> my_extractors = new HashMap<>();
        my_extractors.put(Owner.class, (resultSet, index) -> {
            int owner_id = resultSet.getInt("owner");
            Optional<Owner> optOwner = owners.stream().filter(o -> o.getId() == owner_id).findFirst();
            return optOwner.orElse(new Owner());
        });

        my_extractors.put(Pet.class, (resultSet, index) -> {
            int pet_id = resultSet.getInt("pet");
            Optional<Pet> optPet = pets.stream().filter(o -> o.getId() == pet_id).findFirst();
            return optPet.orElse(new Pet());
        });

        my_extractors.put(Food.class, (resultSet, index) -> {
            int food_id = resultSet.getInt("eats");
            Optional<Food> optFood = foods.stream().filter(o -> o.getId() == food_id).findFirst();
            return optFood.orElse(new Food());
        });

        objectMappers = ObjectMappers.builder()
                .extractors(my_extractors)
                .build();
    }

    public static void fetchTables(Query query) {
        Mapper<Food> foodMapper = objectMappers.forClass(Food.class);
        foods = query.select("select * from corejava.foods")
                .setResult(foodMapper);
        //foods.forEach(System.out::println);

        Mapper<Owner> ownerMapper = objectMappers.forClass(Owner.class);
        owners = query.select("select * from corejava.owners")
                .setResult(ownerMapper);
        //owners.forEach(System.out::println);

        Mapper<Pet> petMapper = objectMappers.forClass(Pet.class);
        pets = query.select("select * from corejava.pets")
                .setResult(petMapper);
        //pets.forEach(System.out::println);

        Mapper<Eat> eatMapper = objectMappers.forClass(Eat.class);
        eats = query.select("select * from corejava.eats")
                .setResult(eatMapper);

        // fix mappings
        // owner -> pets
        pets.forEach(pet -> pet.getOwner().getPets().add(pet));

        // pet -> food
        eats.forEach(eat -> eat.getPet().getEats().add(eat.getEats()));

    }

    public static void main(String[] args) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource() ;
        dataSource.setServerNames( new String[]{"localhost"});
        dataSource.setDatabaseName( "dev" );
        dataSource.setUser( "dev" );
        dataSource.setPassword( "dev" );

        FluentJdbc fluentJdbc = new FluentJdbcBuilder()
                .connectionProvider(dataSource)
                .build();
        Query query = fluentJdbc.query();

        fetchTables(query);
        for(Pet pet : pets) {
            for (Food food: pet.getEats())
            System.out.printf("%s szereti %s-t%n", pet.getPetName(), food.getFoodName());
        }
        System.out.println("Success.");

    }
}
