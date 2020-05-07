CREATE TABLE Player_Character(
PlayerID int NOT NULL,
Name varchar(200) NOT NULL,
back_story varchar(200),
Username varchar(200) NOT NULL,
PartyID int NULL,
StatID int NULL
Primary key(PlayerID),
Foreign key(Username) References UserEntity(Username)
On Update cascade On Delete set null,
Foreign key(PartyID) References Party(PartyID)
On Update cascade On Delete set null,
Foreign key(StatID) References StatBlock(StatID)
On Update cascade On Delete set null
)