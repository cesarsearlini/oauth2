INSERT INTO tbl_user
    (id,
    username,
    password,
    enabled,
    account_non_expired,
    credentials_non_expired,
    account_non_locked)
VALUES
    (nextval('tbl_user_id_seq'),
    'admin',
    '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
    true,
    true,
    true,
    true);

INSERT INTO tbl_user
    (id,
    username,
    password,
    enabled,
    account_non_expired,
    credentials_non_expired,
    account_non_locked)
VALUES
    (nextval('tbl_user_id_seq'),
    'manager',
    '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu',
    true,
    true,
    true,
    true);

INSERT INTO ROLE_USER
    (ROLE_ID, USER_ID)
VALUES
    (1, 1),
    (2, 2);