DROP TABLE IF EXISTS attractiontags;
DROP TABLE IF EXISTS attractions;

CREATE TABLE attractions (
                             id INT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             description VARCHAR(150),
                             cityKey INT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE attractiontags (
                                attractionKey INT,
                                tagKey INT
);

-- Optional: seed some attractions
INSERT INTO attractions (name, description, cityKey) VALUES
                                                         ('Tivoli', 'Rutsjebaner til hele familien', 1),
                                                         ('Den lille havfrue', 'Flot dame', 1);

-- Optional: seed some attractiontags
INSERT INTO attractiontags (attractionKey, tagKey) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (1, 3),
                                                       (2, 2),
                                                       (2, 3),
                                                       (2, 5);
