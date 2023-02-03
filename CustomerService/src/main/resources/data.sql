    insert into country (code, name) values ('IN', 'India');
insert into country (code, name) values ('US', 'United States');
insert into country (code, name) values ('CN', 'Canada');
insert into country (code, name) values ('AU', 'Australia');

insert into state (code, name) values ('DEL', 'Delhi');
insert into state (code, name) values ('NCR', 'National Capital Region');
insert into state (code, name) values ('UP', 'Uttar Pradesh');
insert into state (code, name) values ('MB', 'Maharastra');
insert into state (code, name) values ('CN', 'Karnataka');
insert into state (code, name) values ('HR', 'Hariyana');

insert into city (code, name, pin) values ('C001', 'New Delhi', '100001');
insert into city (code, name, pin) values ('C002', 'Noida', '120000');
insert into city (code, name, pin) values ('C003', 'Gurugram', '121002');
insert into city (code, name, pin) values ('C004', 'Faridabad', '121003');
insert into city (code, name, pin) values ('C005', 'Mumbai', '800004');
insert into city (code, name, pin) values ('C006', 'Banglore', '600001');
insert into city (code, name, pin) values ('C007', 'Kanpur', '151000');
insert into city (code, name, pin) values ('C008', 'Chembur', '800424');
insert into city (code, name, pin) values ('C009', 'Pune', '802125');
insert into city (code, name, pin) values ('C010', 'Meerut', '121054');

insert into country_states (country_id, states_id) values (1, 1);
insert into country_states (country_id, states_id) values (1, 2);
insert into country_states (country_id, states_id) values (1, 3);
insert into country_states (country_id, states_id) values (1, 4);
insert into country_states (country_id, states_id) values (1, 5);

insert into state_cities (state_id, cities_id) values (1, 1);
insert into state_cities (state_id, cities_id) values (2, 2);
insert into state_cities (state_id, cities_id) values (6, 3);
insert into state_cities (state_id, cities_id) values (6, 4);
insert into state_cities (state_id, cities_id) values (4, 5);
insert into state_cities (state_id, cities_id) values (5, 6);
insert into state_cities (state_id, cities_id) values (3, 7);
insert into state_cities (state_id, cities_id) values (4, 8);
insert into state_cities (state_id, cities_id) values (4, 9);
insert into state_cities (state_id, cities_id) values (3, 10);

insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User01', 'M01', 'L01', 18, 'MALE', '9999999990', TRUE, 'Flat 101', 'C001', '8978954321', true, 'user01@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User02', 'M02', 'L02', 14, 'MALE', '9999999991', FALSE, 'Flat 102', 'C002', null, true, 'user02@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User03', 'M03', 'L03', 27, 'MALE', '9999999992', FALSE, 'Flat 103', 'C003', null, true, 'user03@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User04', 'M04', 'L04', 33, 'FEMALE', '9999999993', FALSE, 'Flat 104', 'C001', null, true, 'user04@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User05', 'M05', 'L05', 65, 'MALE', '9999999994', FALSE, 'Flat 105', 'C002', '8978954320', true, 'user05@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User06', 'M06', 'L06', 11, 'MALE', '9999999995', FALSE, 'Flat 106', 'C003', null, true, 'user06@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User07', 'M07', 'L07', 18, 'FEMALE', '9999999996', FALSE, 'Flat 107', 'C002', null, true, 'user07@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User08', 'M08', 'L08', 71, 'MALE', '9999999997', FALSE, 'Flat 108', 'C002', null, true, 'user08@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User09', 'M09', 'L09', 53, 'FEMALE', '9999999998', FALSE, 'Flat 109', 'C005', null, true, 'user09@test.com');
insert into customer (FIRSTNAME, MIDDLENAME, LASTNAME, AGE, GENDER, MOBILENO, INDIVIDUAL, ADDRESS, CITY, ALTMOBILENO, isactive, email) values ('User10', 'M10', 'L10', 22, 'MALE', '9999999999', FALSE, 'Flat 110', 'C003', null, true, 'user10@test.com');

insert into genre (name, description) values ('Action', 'Action movies');
insert into genre (name, description) values ('Comedy', 'Comedy movies');
insert into genre (name, description) values ('Thriller', 'Thriller movies');
insert into genre (name, description) values ('SciFi', 'SciFi movies');
insert into genre (name, description) values ('Animation', 'Animation movies');

insert into language (code, name) values ('EN', 'English');
insert into language (code, name) values ('HN', 'Hindi');
insert into language (code, name) values ('MT', 'Marathi');
insert into language (code, name) values ('KN', 'Kannada');
insert into language (code, name) values ('PN', 'Punjabi');

insert into movie (name, description, year, isactive) values ('Iron Man', 'Marvel Infinity Saga', 2009, true);
insert into movie (name, description, year, isactive) values ('Iron Man 2', 'Marvel Infinity Saga', 2010, true);
insert into movie (name, description, year, isactive) values ('Iron Man 3', 'Marvel Infinity Saga', 2012, true);
insert into movie (name, description, year, isactive) values ('Thor', 'Marvel Infinity Saga', 2011, true);
insert into movie (name, description, year, isactive) values ('Thor 2', 'Marvel Infinity Saga', 2014, true);
insert into movie (name, description, year, isactive) values ('Thor 3', 'Marvel Infinity Saga', 2016, true);
insert into movie (name, description, year, isactive) values ('Hulk', 'Marvel Infinity Saga', 2007, true);
insert into movie (name, description, year, isactive) values ('Caption America', 'Marvel Infinity Saga', 2015, true);
insert into movie (name, description, year, isactive) values ('Winter Solder', 'Harry Porter Saga', 1998, true);
insert into movie (name, description, year, isactive) values ('Harri Porter 1', 'Harry Porter Saga', 1999, true);
insert into movie (name, description, year, isactive) values ('Harri Porter 2', 'Harry Porter Saga', 2003, true);
insert into movie (name, description, year, isactive) values ('Harri Porter 3', 'Harry Porter Saga', 2004, true);
insert into movie (name, description, year, isactive) values ('Harri Porter 4', 'Harry Porter Saga', 2007, true);
insert into movie (name, description, year, isactive) values ('Golmal', 'Utpal Dutt Golmal', 1971, true);
insert into movie (name, description, year, isactive) values ('Cars', 'Desney', 2003, true);

insert into movie_genres (movie_id, genres_id) values (1, 1);
insert into movie_genres (movie_id, genres_id) values (2, 1);
insert into movie_genres (movie_id, genres_id) values (3, 1);
insert into movie_genres (movie_id, genres_id) values (4, 1);
insert into movie_genres (movie_id, genres_id) values (5, 1);
insert into movie_genres (movie_id, genres_id) values (6, 1);
insert into movie_genres (movie_id, genres_id) values (7, 1);
insert into movie_genres (movie_id, genres_id) values (8, 1);
insert into movie_genres (movie_id, genres_id) values (9, 1);
insert into movie_genres (movie_id, genres_id) values (10, 3);
insert into movie_genres (movie_id, genres_id) values (11, 3);
insert into movie_genres (movie_id, genres_id) values (12, 3);
insert into movie_genres (movie_id, genres_id) values (13, 3);
insert into movie_genres (movie_id, genres_id) values (1, 4);
insert into movie_genres (movie_id, genres_id) values (2, 4);
insert into movie_genres (movie_id, genres_id) values (3, 4);
insert into movie_genres (movie_id, genres_id) values (4, 4);
insert into movie_genres (movie_id, genres_id) values (5, 4);
insert into movie_genres (movie_id, genres_id) values (6, 4);
insert into movie_genres (movie_id, genres_id) values (7, 4);
insert into movie_genres (movie_id, genres_id) values (8, 4);
insert into movie_genres (movie_id, genres_id) values (9, 4);
insert into movie_genres (movie_id, genres_id) values (14, 2);
insert into movie_genres (movie_id, genres_id) values (15, 5);

insert into movie_languages (movie_id, languages_id) values (1, 2);
insert into movie_languages (movie_id, languages_id) values (2, 2);
insert into movie_languages (movie_id, languages_id) values (3, 2);
insert into movie_languages (movie_id, languages_id) values (4, 2);
insert into movie_languages (movie_id, languages_id) values (5, 2);
insert into movie_languages (movie_id, languages_id) values (6, 2);
insert into movie_languages (movie_id, languages_id) values (7, 2);
insert into movie_languages (movie_id, languages_id) values (8, 2);
insert into movie_languages (movie_id, languages_id) values (9, 2);
insert into movie_languages (movie_id, languages_id) values (10, 2);
insert into movie_languages (movie_id, languages_id) values (11, 2);
insert into movie_languages (movie_id, languages_id) values (12, 2);
insert into movie_languages (movie_id, languages_id) values (13, 2);
insert into movie_languages (movie_id, languages_id) values (14, 2);
insert into movie_languages (movie_id, languages_id) values (15, 2);

insert into theatre (name, columns, rows, address, city, isactive) values ('Inox', '5', 6, 'CMS Mall', 'C001', true);
insert into theatre (name, columns, rows, address, city, isactive) values ('Sony', '6', 6, 'Citymall', 'C002', true);
insert into theatre (name, columns, rows, address, city, isactive) values ('Ralince', '2', 5, 'GIP Mall', 'C003', true);
insert into theatre (name, columns, rows, address, city, isactive) values ('BigCinema', '4', 5, 'Reliance Mall', 'C004', true);
insert into theatre (name, columns, rows, address, city, isactive) values ('Regal', '3', 8, 'Super Market', 'C005', true);

insert into offer (discount_type, discount_type_desc, ticket_count, percent_discount) values (1, 'After noon discount', null, 20);
insert into offer (discount_type, discount_type_desc, ticket_count, percent_discount) values (2, 'Ticket count discount', 3, 50);

insert into offer_theatres (offer_id, theatres_id) values (1, 1);
insert into offer_theatres (offer_id, theatres_id) values (1, 2);
insert into offer_theatres (offer_id, theatres_id) values (1, 3);
insert into offer_theatres (offer_id, theatres_id) values (1, 4);
insert into offer_theatres (offer_id, theatres_id) values (1, 5);
insert into offer_theatres (offer_id, theatres_id) values (2, 1);
insert into offer_theatres (offer_id, theatres_id) values (2, 2);
insert into offer_theatres (offer_id, theatres_id) values (2, 3);
insert into offer_theatres (offer_id, theatres_id) values (2, 4);
insert into offer_theatres (offer_id, theatres_id) values (2, 5);





