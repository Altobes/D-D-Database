Use DungeonDatabase
Go
Create Table Campaign(
	[Name] varChar(50) NOT NULL,
	CampaignID int NOT NULL,
	PartyID int,

	Primary Key (CampaignID),
	Foreign Key(PartyID) REFERENCES Party
	)