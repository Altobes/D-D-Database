--Procedure to change a player character's Name
--Sal Altobellis
USE DungeonDatabase
GO 

Create Procedure Change_Player_Name (@Player int, @Name Varchar(200)) AS

IF (@Player IS NULL or @Player NOT IN (Select PlayerID
									   From Player_Character as PC
									   Where PC.PlayerID = @Player))
BEGIN
	RAISERROR('ERROR: Invalid PlayerID', 18, 0)
	Return 1
END

Update Player_Character
Set [Name] = @Name
Where PlayerID = @Player

Return 0