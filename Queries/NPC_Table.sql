Use DungeonDatabase
go


create table NPC(
	NPC_ID int Primary Key Identity(1,1),
	Name varchar(50) NOT NULL,
	CR int Default 0,
	StatID int NOT NULL,
	CampaignID int NOT NULL,

	Foreign key (StatID) References ([Stat Block]) StatID,
	Foreign key (CampaignID) References (Campaign) CampaignID
)

--Drop Table NPC

/*
User: Username has to be unique
	  Join Data cannot be alterable
	  Password has to contain...

Some stat block identities need to change as party level goes up.
Not too sure which...

Nothing else needs real constraints in my opinion. Experience for 
anything should default to 0. There should be a max cap on items 
and spells etc. which change as party level increases.

If you guys can think of anything else let me know.
*/