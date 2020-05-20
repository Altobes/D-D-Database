CREATE Procedure [dbo].[Create_PlayerCharacter] (@Name varChar(200) = 'John Smith', @story varChar(200) = '', @user varChar(200) = null, @party int = NULL, @stat int = NULL) AS 

Declare @Playerid int
Declare @rand float
Set @rand = Rand() 
Set @Playerid = @rand * 100

While (@PlayerID IN (Select PlayerID
				  From Player_Character))
BEGIN
	Set @rand = Rand()
	Set @PlayerID = @rand * 100
END

IF (@user is NULL or @user = '' or @user NOT IN (Select Username
												 From [User]))
BEGIN
	Print 'ERROR: Need Valid UserID'
	Return 3
END

IF (@name is NULL or @name = '')
BEGIN
	Print 'ERROR: Need Name'
	Return 6
END

IF (@party != -1)
BEGIN 
	IF (@party is NOT NULL AND @party NOT IN (Select partyID From Party))
	BEGIN
	Print 'ERROR: Need Valid PartyID'
	Return 4
	END
END
ELSE 
Set @party = null

IF (@stat is NOT NULL AND @stat NOT IN (Select StatID From StatBlock))
BEGIN
	Print 'ERROR: Need StatID'
	Return 5
END

Insert Into Player_Character(PlayerID, [Name], back_story, Username, PartyID, StatID)
Values (@PlayerID, @Name, @story, @user, @party, @stat)
Return 0