Use DungeonDatabase
GO

Create Procedure Delete_Player (@PlayerID int) AS

if (@PlayerID is NULL OR @PlayerID NOT IN (Select @PlayerID
										   From Player_Character as PC
										   Where PC.PlayerID = @PlayerID))
BEGIN
	RAISERROR('ERROR: Invalid PlayerID', 18, 0)
	Return 1
END

Delete FROM Player_Character WHERE Player_Character.PlayerID = @PlayerID
Return 0