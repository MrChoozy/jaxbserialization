CREATE TABLE persons (
    id serial,
    name VARCHAR(50) NOT NULL,
    birthday date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE hobby(
    id serial,
    hobby_name VARCHAR(50) NOT NULL,
    persons_id int,
    complexity int,
    CONSTRAINT complexity_check CHECK (complexity BETWEEN 1 AND 100),
    PRIMARY KEY (id),
    FOREIGN KEY (persons_id) REFERENCES persons(id) ON DELETE CASCADE
    );