CREATE TABLE users (
    id int GENERATED ALWAYS AS IDENTITY,
    login TEXT UNIQUE NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    email TEXT NOT NULL,
    phone TEXT not NULL,

    CONSTRAINT login_chk CHECK ( LENGTH(login) BETWEEN 1 AND 100 ),
    CONSTRAINT fname_chk CHECK ( LENGTH(first_name) BETWEEN 1 AND 100 ),
    CONSTRAINT lname_chk CHECK ( LENGTH(last_name) BETWEEN 1 AND 100 ),
    CONSTRAINT email_chk CHECK ( LENGTH(email) BETWEEN 1 AND 50 ),
    CONSTRAINT phone_chk CHECK ( LENGTH(phone) BETWEEN 11 AND 18 )
);
