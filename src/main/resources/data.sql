insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (1, 'Ken', 'Burns', 15, 'OffensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (2, 'Bob', 'Green', 14, 'OffensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (3, 'Tom', 'Smith', 13, 'OffensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (4, 'Tim', 'Olive', 12, 'OffensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (5, 'Rex', 'King', 11, 'OffensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (6, 'Ted', 'Brown', 10, 'OffensiveCoordinator');

insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (7, 'Keenan', 'Ivester', 10, 'DefensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (8, 'Edwardo', 'Clendening', 11, 'DefensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (9, 'Jules', 'Leavens', 12, 'DefensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (10, 'Vern', 'Ploof', 13, 'DefensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (11, 'Jasper', 'Durbin', 14, 'DefensiveCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (12, 'Franklin', 'Fain', 15, 'DefensiveCoordinator');

insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (13, 'Mel', 'Beamon', 10, 'SpecialTeamsCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (14, 'Kris', 'Freeberg', 11, 'SpecialTeamsCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (15, 'Mohammad', 'Gentle', 12, 'SpecialTeamsCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (16, 'Arlen', 'Rossow', 13, 'SpecialTeamsCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (17, 'Merle', 'Cortes', 14, 'SpecialTeamsCoordinator');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (18, 'Wes', 'Ness', 15, 'SpecialTeamsCoordinator');

insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (19, 'Rafael', 'Carolan', 10, 'HeadCoach');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (20, 'Darryl', 'Brim', 11, 'HeadCoach');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (21, 'Andrew', 'Brim', 12, 'HeadCoach');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (22, 'Raul', 'Carbonaro', 13, 'HeadCoach');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (23, 'Antwan', 'Chavez', 14, 'HeadCoach');
insert into coach (ID, FIRST_NAME, LAST_NAME, POWER, DTYPE) VALUES (24, 'Raymon', 'Bluhm', 15, 'HeadCoach');

insert into team (NAME, LOCATION, OC_ID, DC_ID, STC_ID, HC_ID, POWER) VALUES ('Warriors', 'St. Charles West', 1,  7, 13, 19, 12);
insert into team (NAME, LOCATION, OC_ID, DC_ID, STC_ID, HC_ID, POWER) VALUES ('Indians', 'Wentzville',        2,  8, 14, 20, 12);
insert into team (NAME, LOCATION, OC_ID, DC_ID, STC_ID, HC_ID,POWER) VALUES ('Panthers', 'Fort Zumwalt',      3,  9, 15, 21, 13);
insert into team (NAME, LOCATION, OC_ID, DC_ID, STC_ID, HC_ID,POWER) VALUES ('Vikings', 'Francis Howell',     4, 10, 16, 22, 15);
insert into team (NAME, LOCATION, OC_ID, DC_ID, STC_ID, HC_ID, POWER) VALUES ('Pirates', 'St. Charles',       5, 11, 17, 23, 15);

insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (1, 'Leopoldo', 'Erving', 'QB', 1, 10);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (2, 'Burl', 'Przybylski', 'QB', 2, 11);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (3, 'Jerrold', 'Ohern', 'QB', 3, 11);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (4, 'Richard', 'Hutcheson', 'QB', 4, 12);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (5, 'Ollie', 'Lute', 'QB', 5, 15);

insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (6, 'Sid', 'Grothe', 'RB', 1, 10);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (7, 'Granville', 'Smith', 'RB', 2, 11);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (8, 'Fredric', 'Conlin', 'RB', 3, 11);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (9, 'Benedict', 'Weedon', 'RB', 4, 19);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (10, 'Brenton', 'Downer', 'RB', 5, 15);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (11, 'Rodrigo', 'Doane', 'RB', 1, 10);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (12, 'Jarrett', 'Primmer', 'RB', 2, 11);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (13, 'Carlo', 'Jefferies', 'RB', 3, 11);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (14, 'Deandre', 'Minott', 'RB', 4, 12);
insert into player (ID, FIRST_NAME, LAST_NAME, POSITION, TEAM_ID, POWER) VALUES (15, 'Hank', 'Kissinger', 'RB', 5, 15);

insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 1, 'QB', 1);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 2, 'QB', 2);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 3, 'QB', 3);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 4, 'QB', 4);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 5, 'QB', 5);

insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 6, 'RB', 1);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 7, 'RB', 2);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 8, 'RB', 3);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 9, 'RB', 4);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 1, 10, 'RB', 5);


insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 2, 11, 'RB', 1);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 2, 12, 'RB', 2);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 2, 13, 'RB', 3);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 2, 14, 'RB', 4);
insert into depth_chart (ID, DEPTH, PLAYER_ID, POSITION, TEAM_ID) VALUES (hibernate_sequence.nextval, 2, 15, 'RB', 5);
