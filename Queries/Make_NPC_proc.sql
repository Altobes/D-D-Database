CREATE PROCEDURE [Create_NPC]
	(@Name varchar(50),
	 @CR int = 0,
	 @StatID int,
	 @CampaignID int)
	 
AS

--UPDATE THIS IF VALUES ARE ALLOWED TO BE NULL OR SOMETHING

INSERT INTO NPC (Name, CR, StatID, CampaignID)
VALUES (@Name, @CR, @StatID, @CampaignID)

GO