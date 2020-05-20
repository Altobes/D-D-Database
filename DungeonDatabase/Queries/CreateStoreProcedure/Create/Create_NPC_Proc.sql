CREATE Procedure [dbo].[CreateNPC] (
  @name varchar(50) = 'John Smith',
  @cr int = null,
  @statID int = null,
  @campaignID int = null)
AS

Declare @NPC_ID int

Declare @ID int 
SET @ID = (select MAX(@NPC_ID) from NPC)
IF (@ID is null)
	BEGIN
		SET @ID = 0
	END
SET @NPC_ID = @ID + 1

IF (@name is null or @name = '')
	BEGIN
		RAISERROR('Error!!! Please add a Name', 18, 0)
		Return 1
	END

IF (@statID is null or @statID = '')
	BEGIN
		RAISERROR('Error!!! Please add a StatID', 18, 0)
		Return 2
	END

IF (@campaignID is null or @campaignID = '')
	BEGIN
		RAISERROR('Error!!! Please add a CampaignID', 18, 0)
		Return 3
	END

IF (@cr is null or @cr = '')
	BEGIN
		RAISERROR('Error!!! Please add a CR', 18, 0)
		Return 4
	END
IF (SELECT COUNT(*) FROM NPC
          WHERE Name = @Name) = 1
	BEGIN
      PRINT 'ERROR: NPC name already exists.';
	  RETURN 5
	END

Insert into NPC([Name], [CR], [StatID], [CampaignID])
VALUES (@name, @cr, @statID, @campaignID)
return 0