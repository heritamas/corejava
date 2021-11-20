--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

-- Started on 2021-11-20 18:36:44 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16386)
-- Name: corejava; Type: SCHEMA; Schema: -; Owner: dev
--

CREATE SCHEMA corejava;


ALTER SCHEMA corejava OWNER TO dev;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 207 (class 1259 OID 16409)
-- Name: eats; Type: TABLE; Schema: corejava; Owner: dev
--

CREATE TABLE corejava.eats (
    pet integer NOT NULL,
    eats integer NOT NULL
);


ALTER TABLE corejava.eats OWNER TO dev;

--
-- TOC entry 204 (class 1259 OID 16389)
-- Name: foods; Type: TABLE; Schema: corejava; Owner: dev
--

CREATE TABLE corejava.foods (
    id integer NOT NULL,
    food_name character varying NOT NULL
);


ALTER TABLE corejava.foods OWNER TO dev;

--
-- TOC entry 203 (class 1259 OID 16387)
-- Name: foods_id_seq; Type: SEQUENCE; Schema: corejava; Owner: dev
--

ALTER TABLE corejava.foods ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME corejava.foods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 209 (class 1259 OID 16424)
-- Name: owners; Type: TABLE; Schema: corejava; Owner: dev
--

CREATE TABLE corejava.owners (
    id integer NOT NULL,
    name character varying NOT NULL,
    city character varying NOT NULL,
    phone character varying
);


ALTER TABLE corejava.owners OWNER TO dev;

--
-- TOC entry 208 (class 1259 OID 16422)
-- Name: owners_id_seq; Type: SEQUENCE; Schema: corejava; Owner: dev
--

ALTER TABLE corejava.owners ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME corejava.owners_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 206 (class 1259 OID 16397)
-- Name: pets; Type: TABLE; Schema: corejava; Owner: dev
--

CREATE TABLE corejava.pets (
    id integer NOT NULL,
    pet_name character varying NOT NULL,
    owner integer,
    owned_since date,
    species character varying NOT NULL
);


ALTER TABLE corejava.pets OWNER TO dev;

--
-- TOC entry 205 (class 1259 OID 16395)
-- Name: pets_id_seq; Type: SEQUENCE; Schema: corejava; Owner: dev
--

ALTER TABLE corejava.pets ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME corejava.pets_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 2993 (class 0 OID 16409)
-- Dependencies: 207
-- Data for Name: eats; Type: TABLE DATA; Schema: corejava; Owner: dev
--

INSERT INTO corejava.eats VALUES (1, 2);
INSERT INTO corejava.eats VALUES (1, 3);
INSERT INTO corejava.eats VALUES (2, 2);
INSERT INTO corejava.eats VALUES (3, 2);
INSERT INTO corejava.eats VALUES (4, 4);
INSERT INTO corejava.eats VALUES (5, 1);


--
-- TOC entry 2990 (class 0 OID 16389)
-- Dependencies: 204
-- Data for Name: foods; Type: TABLE DATA; Schema: corejava; Owner: dev
--

INSERT INTO corejava.foods OVERRIDING SYSTEM VALUE VALUES (1, 'Whiskas marhás');
INSERT INTO corejava.foods OVERRIDING SYSTEM VALUE VALUES (2, 'Szív mix');
INSERT INTO corejava.foods OVERRIDING SYSTEM VALUE VALUES (3, 'Szárított hal');
INSERT INTO corejava.foods OVERRIDING SYSTEM VALUE VALUES (4, 'Spirulina');


--
-- TOC entry 2995 (class 0 OID 16424)
-- Dependencies: 209
-- Data for Name: owners; Type: TABLE DATA; Schema: corejava; Owner: dev
--

INSERT INTO corejava.owners OVERRIDING SYSTEM VALUE VALUES (1, 'Ancsa', 'Budapest', '+36 1 1234567');
INSERT INTO corejava.owners OVERRIDING SYSTEM VALUE VALUES (2, 'Timi', 'Székesfehérvár', '+36 22 456789');
INSERT INTO corejava.owners OVERRIDING SYSTEM VALUE VALUES (3, 'Rita', 'Budapest', '+36 30 2000300');


--
-- TOC entry 2992 (class 0 OID 16397)
-- Dependencies: 206
-- Data for Name: pets; Type: TABLE DATA; Schema: corejava; Owner: dev
--

INSERT INTO corejava.pets OVERRIDING SYSTEM VALUE VALUES (1, 'Aranka', 1, '2000-01-01', 'teknős');
INSERT INTO corejava.pets OVERRIDING SYSTEM VALUE VALUES (2, 'Julika', 1, '2001-01-01', 'teknős');
INSERT INTO corejava.pets OVERRIDING SYSTEM VALUE VALUES (3, 'Henike', 1, '2002-01-01', 'teknős');
INSERT INTO corejava.pets OVERRIDING SYSTEM VALUE VALUES (4, 'Júlia', 3, '2010-06-01', 'aranyhal');
INSERT INTO corejava.pets OVERRIDING SYSTEM VALUE VALUES (5, 'Gombóc', 2, '2011-08-01', 'macska');


--
-- TOC entry 3001 (class 0 OID 0)
-- Dependencies: 203
-- Name: foods_id_seq; Type: SEQUENCE SET; Schema: corejava; Owner: dev
--

SELECT pg_catalog.setval('corejava.foods_id_seq', 4, true);


--
-- TOC entry 3002 (class 0 OID 0)
-- Dependencies: 208
-- Name: owners_id_seq; Type: SEQUENCE SET; Schema: corejava; Owner: dev
--

SELECT pg_catalog.setval('corejava.owners_id_seq', 3, true);


--
-- TOC entry 3003 (class 0 OID 0)
-- Dependencies: 205
-- Name: pets_id_seq; Type: SEQUENCE SET; Schema: corejava; Owner: dev
--

SELECT pg_catalog.setval('corejava.pets_id_seq', 5, true);


--
-- TOC entry 2853 (class 2606 OID 16406)
-- Name: foods foods_pk; Type: CONSTRAINT; Schema: corejava; Owner: dev
--

ALTER TABLE ONLY corejava.foods
    ADD CONSTRAINT foods_pk PRIMARY KEY (id);


--
-- TOC entry 2855 (class 2606 OID 16408)
-- Name: foods foods_un; Type: CONSTRAINT; Schema: corejava; Owner: dev
--

ALTER TABLE ONLY corejava.foods
    ADD CONSTRAINT foods_un UNIQUE (food_name);


--
-- TOC entry 2859 (class 2606 OID 16428)
-- Name: owners owners_pk; Type: CONSTRAINT; Schema: corejava; Owner: dev
--

ALTER TABLE ONLY corejava.owners
    ADD CONSTRAINT owners_pk PRIMARY KEY (id);


--
-- TOC entry 2857 (class 2606 OID 16404)
-- Name: pets pets_pk; Type: CONSTRAINT; Schema: corejava; Owner: dev
--

ALTER TABLE ONLY corejava.pets
    ADD CONSTRAINT pets_pk PRIMARY KEY (id);


--
-- TOC entry 2861 (class 2606 OID 16412)
-- Name: eats eats_fk; Type: FK CONSTRAINT; Schema: corejava; Owner: dev
--

ALTER TABLE ONLY corejava.eats
    ADD CONSTRAINT eats_fk FOREIGN KEY (eats) REFERENCES corejava.foods(id);


--
-- TOC entry 2862 (class 2606 OID 16417)
-- Name: eats eats_fk_1; Type: FK CONSTRAINT; Schema: corejava; Owner: dev
--

ALTER TABLE ONLY corejava.eats
    ADD CONSTRAINT eats_fk_1 FOREIGN KEY (pet) REFERENCES corejava.pets(id);


--
-- TOC entry 2860 (class 2606 OID 16429)
-- Name: pets pets_fk; Type: FK CONSTRAINT; Schema: corejava; Owner: dev
--

ALTER TABLE ONLY corejava.pets
    ADD CONSTRAINT pets_fk FOREIGN KEY (owner) REFERENCES corejava.owners(id);


-- Completed on 2021-11-20 18:36:44 CET

--
-- PostgreSQL database dump complete
--

