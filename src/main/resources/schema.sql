
--UTILISATEUR

CREATE TABLE UTILISATOR (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);



-- Message


CREATE TABLE MESSAGE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    length INT,
    content VARCHAR(255),
    published_on TIMESTAMP NOT NULL,
    utilisator INT,  -- 'user' column for the foreign key reference
    FOREIGN KEY (utilisator) REFERENCES UTILISATOR(id)  -- Ensure USER table name is quoted if needed
);

