Use DungeonDatabase
Go

CREATE PROCEDURE [dbo].[insert_NPC] (
	@NPC_ID [int],
	@Name varchar(200), 
	@CR [int], 
	@StatID [int],
	@CampaignID [int])
AS
Insert into [NPC] ([NPC_ID], [CR], [StatID], [CampaignID])
VALUES (@NPC_ID, @Name, @CR, @StatID, @CampaignID)

GO

CREATE PROCEDURE [dbo].[delete_NPC] (@NPC_ID int)
--Check to see if NPC_ID is valid
IF (Select NPC_ID FROM [NPC] WHERE NPC_ID=NPC_ID)=0
BEGIN
	PRINT 'Error: The parameters are invalid.'
	RETURN 1
END
DROP NPC 
WHERE NPC_ID = @NPC_ID
--Check for errors
DECLARE @Status SMALLINT
SET @Status = @@ERROR
IF @Status <> 0 
BEGIN
	--Return error code to the calling program to indicate failure
	PRINT 'An error occurred'
	RETURN(@Status)
END

Go

CREATE PROCEDURE [update_NPC]
	(@NPC_ID [int],
	 @Name varchar(200) = NULL,
	 @CR [int] = -1,
	 @StatID [int] = -1,
	 @CampaignID [int] = -1)
	 
AS
--Check to see if OrderID and ProductID is valid
IF (Select NPC_ID FROM [NPC] WHERE NPC_ID=NPC_ID)=0
BEGIN
	PRINT 'Error: The parameters are invalid.'
	RETURN 1
END

IF (@Name is not null) 
	BEGIN
		UPDATE NPC
		SET Name = @Name 
		WHERE NPC_ID = @NPC_ID
	END

IF (@CR is not null) 
	BEGIN
		UPDATE NPC
		SET CR = @CR 
		WHERE NPC_ID = @NPC_ID
	END

IF (@StatID is not null) 
	BEGIN
		UPDATE NPC
		SET StatID = @StatID 
		WHERE NPC_ID = @NPC_ID
	END

IF (@CampaignID is not null) 
	BEGIN
		UPDATE NPC
		SET CampaignID = @CampaignID 
		WHERE NPC_ID = @NPC_ID
	END
GO
	