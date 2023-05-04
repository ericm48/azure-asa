-- CREATE TYPE
DROP TYPE IF EXISTS genre;
CREATE TYPE genre AS ENUM (
    'ADVENTURE',
    'HORROR',
    'COMEDY',
    'ACTION',
    'SPORTS'
);

-- CREATE TABLE
DROP TABLE IF EXISTS movies;
CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    release_year SMALLINT,
    genre genre,
    price NUMERIC(4, 2)
);

DROP TABLE IF EXISTS customer_profile;
CREATE TABLE customer_profile (
    id SERIAL PRIMARY KEY,
    email VARCHAR NOT NULL,
    first_name  VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL
);
