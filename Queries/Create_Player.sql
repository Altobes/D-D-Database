USE [DungeonDatabase]
GO

/****** Object:  StoredProcedure [dbo].[Create_PlayerCharacter]    Script Date: 4/30/2020 6:35:40 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



ALTER Procedure [dbo].[Create_PlayerCharacter] (@Name varChar(200) = 'John Smith', @story varChar(200) = '', @user varChar(200), @party int = NULL, @stat int = NULL) AS 

Declare @PlayerID int
Declare @LargestID int 
SET @LargestID = (select MAX(PlayerID) from Player_Character)
IF (@LargestID is null)
BEGIN
	SET @LargestID = 0
END
SET @PlayerID = @LargestID + 1

IF (@user is NULL or @Name = '')
BEGIN
	RAISERROR('ERROR: Need UserID', 18, 0)
	Return 3
END

IF (@party is NOT NULL AND @party NOT IN (Select partyID From Party))
BEGIN
	RAISERROR('ERROR: Need Valid PartyID', 18, 0)
	Return 4
END

IF (@stat is NOT NULL AND @stat NOT IN (Select StatID From StatBlock))
BEGIN
	RAISERROR('ERROR: Need StatID', 18, 0)
	Return 4
END

Insert Into Player_Character(PlayerID, [Name], back_story, Username, PartyID, StatID)
Values (@PlayerID, @Name, @story, @user, @party, @stat)
GO


