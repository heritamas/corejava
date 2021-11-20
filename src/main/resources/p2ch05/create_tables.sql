-- corejava.owners definition

-- Drop table

-- DROP TABLE corejava.owners;

CREATE TABLE corejava.owners (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	"name" varchar NOT NULL,
	city varchar NOT NULL,
	phone varchar NULL,
	CONSTRAINT owners_pk PRIMARY KEY (id)
);

-- corejava.pets definition

-- Drop table

-- DROP TABLE corejava.pets;

CREATE TABLE corejava.pets (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	pet_name varchar NOT NULL,
	"owner" int4 NULL,
	owned_since date NULL,
	species varchar NOT NULL,
	CONSTRAINT pets_pk PRIMARY KEY (id)
);


-- corejava.pets foreign keys

ALTER TABLE corejava.pets ADD CONSTRAINT pets_fk FOREIGN KEY ("owner") REFERENCES corejava.owners(id);

-- corejava.foods definition

-- Drop table

-- DROP TABLE corejava.foods;

CREATE TABLE corejava.foods (
	id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	food_name varchar NOT NULL,
	CONSTRAINT foods_pk PRIMARY KEY (id),
	CONSTRAINT foods_un UNIQUE (food_name)
);

-- corejava.eats definition

-- Drop table

-- DROP TABLE corejava.eats;

CREATE TABLE corejava.eats (
	pet int4 NOT NULL,
	eats int4 NOT NULL
);


-- corejava.eats foreign keys

ALTER TABLE corejava.eats ADD CONSTRAINT eats_fk FOREIGN KEY (eats) REFERENCES corejava.foods(id);
ALTER TABLE corejava.eats ADD CONSTRAINT eats_fk_1 FOREIGN KEY (pet) REFERENCES corejava.pets(id);

