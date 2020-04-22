CREATE TABLE Player_Character(
PlayerID int NOT NULL,
Name varchar(200) NOT NULL,
back_story varchar(200),
Username varchar(200) NOT NULL,
PartyID int NOT NULL,
StatID int NOT NULL
Primary key(PlayerID),
Foreign key(Username) References UserEntity(Username)
On Update cascade On Delete set null,
Foreign key(PartyID) References Party(PartyID)
On Update cascade On Delete set null,
Foreign key(StatID) References Stat_Block(StatID)
On Update cascade On Delete set null
)