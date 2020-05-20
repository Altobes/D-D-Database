CREATE PROCEDURE [Modify_NPC]
	(@NPC_ID int,
	 @Name varchar(50) = NULL,
	 @CR int = NULL,
	 @StatID int = NULL,
	 @CampaignID int = NULL)
	 
AS

--------------------------
IF (@Name is not null) BEGIN
	UPDATE NPC
	SET Name = @Name WHERE NPC_ID = @NPC_ID
END
--------------------------
IF (@CR is not null) BEGIN
	UPDATE NPC
	SET CR = @CR WHERE NPC_ID = @NPC_ID
END
--------------------------
IF (@CampaignID is not null) BEGIN
	UPDATE NPC
	SET CampaignID = @CampaignID WHERE NPC_ID = @NPC_ID
END
--------------------------
IF (@StatID is not null) BEGIN
	UPDATE NPC
	SET StatID = @StatID WHERE NPC_ID = @NPC_ID
END

GO
	