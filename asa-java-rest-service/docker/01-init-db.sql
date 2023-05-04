-- CREATE TYPE
DROP TYPE IF EXISTS genre;
CREATE TYPE genre AS ENUM (
    'ADVENTURE',
    'HORROR',
    'COMEDY',
    'ACTION',
    'SPORTS'
);

-- CREATE TABLE movies
DROP TABLE IF EXISTS movies;
CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    release_year SMALLINT,
    genre genre,
    price NUMERIC(4, 2)
);

-- CREATE TABLE customer_profile
DROP TABLE IF EXISTS customer_profile;
CREATE TABLE customer_profile (
    id SERIAL PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);
