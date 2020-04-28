--Procedure to create a player character from provided values and add it to the database
Use DungeonDatabase
Go

Create Procedure Create_PlayerCharacter (@Name varChar(200), @story varChar(200), @user varChar(200), @party int, @stat int ) AS 

Declare @Playerid int
Set @Playerid as Rand(1000)
Declare @i int
Set @i as 1

While (@PlayerID IN (Select PlayerID
				  From Player_Character))
BEGIN
	Set @PlayerID as Rand(@i)
	@i = @i + 1
END

IF (@Name is NULL or @Name = '')
BEGIN
	RAISERROR('ERROR: Name cannot be empty', 18, 0)
	Return 2
END

IF (@user is NULL or @Name = '')
BEGIN
	RAISERROR('ERROR: Need UserID', 18, 0)
	Return 3
END

IF (@party is NULL)
BEGIN
	RAISERROR('ERROR: Need PartyID', 18, 0)
	Return 4
END

--Create statblock function

Insert Into Player_Character(PlayerID, [Name], back_story, Username, PartyID, StatID)
Values (@PlayerID, @Name, @story, @user, @party, @stat)