CREATE SEQUENCE permission_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE role_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE tbl_user_id_seq START 1 INCREMENT 1;

CREATE TABLE permission
  (
     id   INT4 NOT NULL DEFAULT NEXTVAL ('permission_id_seq'),
     name VARCHAR(255),
     PRIMARY KEY (id),
     CONSTRAINT PERMISSION_NAME UNIQUE (name)
  );

CREATE TABLE ROLE
  (
     id   INT4 NOT NULL DEFAULT NEXTVAL ('role_id_seq'),
     name VARCHAR(255),
     PRIMARY KEY (id)
  );

CREATE TABLE permission_role
  (
     role_id       INT4 NOT NULL,
     permission_id INT4 NOT NULL
  );

CREATE TABLE role_user
  (
     user_id INT8 NOT NULL,
     role_id INT4 NOT NULL
  );

CREATE TABLE tbl_user
  (
     id                      INT4 NOT NULL DEFAULT NEXTVAL ('tbl_user_id_seq'),
     account_non_expired     BOOLEAN,
     account_non_locked      BOOLEAN,
     credentials_non_expired BOOLEAN,
     enabled                 BOOLEAN,
     password                VARCHAR(255),
     username                VARCHAR(255),
     PRIMARY KEY (id)
  );

ALTER TABLE permission_role
  ADD CONSTRAINT UK_k0bty7tbcye41jpxam88q5kj2 FOREIGN KEY (permission_id)
  REFERENCES permission;

ALTER TABLE permission_role
  ADD CONSTRAINT FK3tuvkbyi6wcytyg21hvpd6txw FOREIGN KEY (role_id) REFERENCES
  ROLE;

ALTER TABLE role_user
  ADD CONSTRAINT FK50sfdcvbvdaclpn7wp4uop4ml FOREIGN KEY (role_id) REFERENCES
  ROLE;

ALTER TABLE role_user
  ADD CONSTRAINT FKm7rbvp8sdxnbm46bqdd7jm399 FOREIGN KEY (user_id) REFERENCES
  tbl_user;