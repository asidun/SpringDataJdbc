create table if not exists customer(
id int IDENTITY PRIMARY KEY,
  first_name varchar(255),
  dob        date
);

create table if not exists book(
id int IDENTITY PRIMARY KEY,
  title  varchar(255),
  author varchar(255)
);