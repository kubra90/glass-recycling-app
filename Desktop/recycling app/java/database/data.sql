BEGIN TRANSACTION;

-- Admin account
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'false');

-- Recyclers
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('mraymond','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('bblanc','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('mbron','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('ltoussaint','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('dcody','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('awingfield','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('jholden','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('aburton','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('nnagata','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('cmao','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('cdrummer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('fjohnson','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('kashford','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('adawes','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('minaros','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'false');

-- Drivers
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('lglassmore','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'true');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('mglassmore','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'true');
INSERT INTO users (username, password_hash, role, is_driver) VALUES ('cglassmore','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'true');

INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
VALUES ('mraymond','Miles Raymond','271 Lelia St', 'Pittsburgh', 'PA','15211','4125551212','mraymond@gmail.com',120,120,0);
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('bblanc','Benoit Blanc','220 Luray St', 'Pittsburgh', 'PA','15214','4125551213','bblanc@gmail.com',240,120,120);
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('mbron','Miles Bron','2257 Wylie Ave', 'Pittsburgh', 'PA','15219','4125551214','mbron@gmail.com','60','60','0');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('ltoussaint','Lionel Toussaint','1721 Wharton St', 'Pittsburgh', 'PA','15203','4125551215','ltoussaint@gmail.com','100','100','0');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('dcody','Duke Cody','317 Semple St', 'Pittsburgh', 'PA','15213','4125551216','dcody@gmail.com','360','60','300');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('awingfield','Amanda Wingfield','403 Grandview Ave', 'Pittsburgh', 'PA','15211','4125551217','awingfield@gmail.com','480','120','360');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('jholden','James Holden','1207 W North Ave', 'Pittsburgh', 'PA','15233','4125551218','jholden@gmail.com','60','60','0');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('aburton','Amos Burton','812 Beech Ave', 'Pittsburgh', 'PA','15233','4125551219','aburton@gmail.com','180','100','80');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('nnagata','Naomi Nagata','3120 Avalon St', 'Pittsburgh', 'PA','15219','4125551220','nnagata@gmail.com','0','0','0');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('cmao','Clarissa Mao','28 Welsford St', 'Pittsburgh', 'PA','15213','4125551221','cmao@gmail.com','300','120','180');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('cdrummer','Camina Drummer','1031 Herron Ave', 'Pittsburgh', 'PA','15219','4125551222','cdrummer@gmail.com','360','60','300');
INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('fjohnson','Fred Johnson','300 Heinz St', 'Pittsburgh', 'PA','15212','4125551223','fjohnson@gmail.com','420','60','360');
INSERT INTO user_details (account_id, username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('913','kashford','Klaes Ashford','4812 Broad St', 'Pittsburgh', 'PA','15224','4125551224','kashford@gmail.com','480','0','480');
INSERT INTO user_details (account_id, username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('914','adawes','Anderson Dawes','715 Ivy St', 'Pittsburgh', 'PA','15232','4125551225','adawes@gmail.com','600','0','600');
INSERT INTO user_details (account_id, username, full_name, street_address, city, state_abbreviation, zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed)
 VALUES ('915','minaros','Marco Inaros','213 Gross St', 'Pittsburgh', 'PA','15224','4125551226','minaros@gmail.com','540','540','0');


INSERT INTO driver_details (username,home_office_address) VALUES ('lglassmore','3001 Railroad St, Pittsburgh, PA 15201');
INSERT INTO driver_details (username,home_office_address) VALUES ('mglassmore','3001 Railroad St, Pittsburgh, PA 15201');
INSERT INTO driver_details (username,home_office_address) VALUES ('cglassmore','3001 Railroad St, Pittsburgh, PA 15201');

INSERT INTO routes (route_date)
 VALUES ('2023-02-11');
INSERT INTO routes (route_date)
VALUES ('2023-02-11');
INSERT INTO routes (route_date)
 VALUES ('2023-02-12');
INSERT INTO routes (route_date)
 VALUES ('2023-02-12');

INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('mraymond', '2023-02-11', 60, 1, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('bblanc', '2023-02-11', 120, 2, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('mbron', '2023-02-11', 180, 3, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('ltoussaint', '2023-02-11', 60, 1, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('dcody', '2023-02-11', 180, 3, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('awingfield', '2023-02-11', 120, 2, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('jholden', '2023-02-11', 180, 3, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('aburton', '2023-02-12', 60, 1, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('nnagata', '2023-02-12', 120, 2, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('cmao', '2023-02-12', 180, 3, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('cdrummer', '2023-02-12', 60, 1, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('fjohnson', '2023-02-12', 120, 2, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('kashford', '2023-02-12', 180, 3, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('adawes', '2023-02-12', 60, 1, false);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('minaros', '2023-02-12', 120, 2, false);

INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('mraymond', '2023-01-31', 90, 3, true);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('mraymond', '2023-01-15', 60, 2, true);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('mraymond', '2022-12-31', 30, 1, true);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('mraymond', '2022-12-15', 60, 2, true);
INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up)
VALUES ('mraymond', '2022-11-30', 30, 1, true);

-- load prize_details table with prizes
INSERT INTO prize_details (prize_name, point_value)
VALUES ('Vitrum Tshirt', 75),
       ('Donation to Red Cross - Sand Bags for Flood Remediation', 100),
       ('Vitrum Snapback Hat', 150),
       ('$25 Amazon Giftcard', 250),
       ('Local Brewery Tour Experience', 400),
       ('$100 REI Gift Card', 600),
       ('Espresso Machine', 1500),
       ('PlayStation 5 Console', 2500),
       ('Weekend Getaway to 7 Springs Resort', 3750),
       ('Custom Recycled Glass Kitchen Countertop', 5000);

COMMIT TRANSACTION;
