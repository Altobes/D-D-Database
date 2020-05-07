USE [DungeonDatabase]
GO
--StoredProcedure [dbo].[CreateNPC]
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE Procedure [dbo].[CreateNPC] (
  @Name varchar(50),
  @CR int,
  @StatID int,
  @CampaignID int)
AS

Declare @NPC_ID int

Declare @ID int 
SET @ID = (select MAX(@NPC_ID) from NPC)
IF (@ID is null)
	BEGIN
		SET @ID = 0
	END
SET @NPC_ID = @ID + 1

IF (@Name is null or @Name = '')
	BEGIN
		RAISERROR('Error!!! Please add a Name', 18, 0)
		Return 1
	END

IF (@StatID is NULL or @StatID = '')
	BEGIN
		RAISERROR('Error!!! Please add a StatID', 18, 0)
		Return 2
	END

IF (@CampaignID is NULL or @CampaignID = '')
	BEGIN
		RAISERROR('Error!!! Please add a CampaignID', 18, 0)
		Return 3
	END

Insert into [NPC] ([NPC_ID], [Name], [CR], [StatID], [CampaignID])
VALUES (@NPC_ID, @Name, @CR, @StatID, @CampaignID)