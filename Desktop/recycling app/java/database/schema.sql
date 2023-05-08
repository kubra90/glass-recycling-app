BEGIN TRANSACTION;

DROP TABLE IF EXISTS routes, pickup_details, driver_details, user_details, users CASCADE;

DROP SEQUENCE IF EXISTS seq_account_id, seq_driver_id;

-- user/password information for users who recycle, drivers, and admins
-- Drivers (ROLE_USER && is_driver = true)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	is_driver boolean DEFAULT false,
	CONSTRAINT PK_users PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- User information for those registered to recycle with us
-- Sequence below --> recyclers have account_id starting with 1001
CREATE SEQUENCE seq_account_id
    INCREMENT BY 1
    START WITH 1001
    NO MAXVALUE;

CREATE TABLE user_details (
    account_id int NOT NULL DEFAULT nextval ('seq_account_id'),
	username varchar(50) NOT NULL UNIQUE,
	--full_name or first_name & last_name? does one or the other benefit us when user inputs data on the front end?
	full_name varchar(100) NOT NULL,
	street_address varchar(100) NOT NULL, -- 3001 Railroad St
	city varchar(50) NOT NULL, -- Pittsburgh
	state_abbreviation varchar(2) NOT NULL, -- PA
	zipcode varchar(5) NOT NULL, -- 15201
	phone_number varchar(11), -- example: 7032978845 (country code '1' optional)
	email_address varchar(100),
	total_pounds_recycled int DEFAULT 0,
    credits_balance int DEFAULT 0, -- 1pt per pound
    credits_redeemed int DEFAULT 0,
	CONSTRAINT PK_user_details PRIMARY KEY (account_id),
	CONSTRAINT FK_user_details_users FOREIGN KEY (username) REFERENCES users (username)
);

-- Driver information
-- Sequence below --> Drivers have driver_id, starting with 3001
CREATE SEQUENCE seq_driver_id
    INCREMENT BY 1
    START WITH 3001
    NO MAXVALUE;

CREATE TABLE driver_details (
    driver_id int NOT NULL DEFAULT nextval ('seq_driver_id'),
    username varchar(50) NOT NULL UNIQUE,
    home_office_address varchar(200) DEFAULT ('3001 Railroad St, Pittsburgh, PA 15201'), -- default to home base address, same for all drivers -- full address could avoid concatenation when feeding into API
    CONSTRAINT PK_driver_details PRIMARY KEY (driver_id),
    CONSTRAINT FK_driver_details_users FOREIGN KEY (username) REFERENCES users (username)
);

-- Route table - unique routeID, route Date, and driver assigned to that route
-- when pickups are assigned to a route, in the same method, we can update the route_id in pickup_details for that specific route
-- keeping in mind limit of pickups per route, per day
CREATE TABLE routes (
    route_id SERIAL,
    route_date date NOT NULL,
    driver_id int,
    CONSTRAINT PK_routes PRIMARY KEY (route_id),
    CONSTRAINT FK_routes_driver_details FOREIGN KEY (driver_id) REFERENCES driver_details (driver_id)
);

-- pickup information
-- route_id will default to null = IF NULL means pickup is yet to be assigned
-- to add pickup to a route, update the route_id value
CREATE TABLE pickup_details (
	pickup_id SERIAL,
	route_id int,
	requesting_username varchar(50) NOT NULL,
	pickup_date date NOT NULL,
	pickup_weight int, -- 60lbs per bin
	num_of_bins int NOT NULL, -- User selection of 1, 2, or 3(max) per pickup
	is_Picked_Up boolean DEFAULT false,
	CONSTRAINT PK_pickup_details PRIMARY KEY (pickup_id),
    CONSTRAINT FK_pickup_details_user_details FOREIGN KEY (requesting_username) REFERENCES user_details (username),
    CONSTRAINT FK_pickup_details_routes FOREIGN KEY (route_id) REFERENCES routes (route_id),
	CONSTRAINT chk_num_of_bins CHECK (num_of_bins > 0 AND num_of_bins <= 3)
);


CREATE TABLE prize_details (
    prize_id SERIAL,
    prize_name varchar(200) NOT NULL,
    point_value int NOT NULL,
    --is_redeemed boolean --- Once a prize is redeemed, is it removed from the list?
    CONSTRAINT PK_prize_details PRIMARY KEY (prize_id)
);

CREATE TABLE prize_user (
    prize_id int,
    account_id int NOT NULL,
    date_redeemed date NOT NULL,
    CONSTRAINT PK_prize_user PRIMARY KEY (prize_id, account_id)
);

COMMIT TRANSACTION;

