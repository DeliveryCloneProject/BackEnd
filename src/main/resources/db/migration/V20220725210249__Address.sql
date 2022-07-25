CREATE TABLE address (
    id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    address VARCHAR(200) NOT NULL,
    metropolitan VARCHAR(10) NOT NULL,
    city VARCHAR (20) NOT NULL,
    town VARCHAR (30) NOT NULL,
    road VARCHAR (40) NOT NULL,
    main_number INT NOT NULL,
    sub_number INT NULL,
    point POINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_address(
    id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    address_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    detail_address VARCHAR(300) NULL,
    memo VARCHAR(50) NULL,
    updated_at DATETIME NOT NULL,
    deleted_at DATETIME NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);