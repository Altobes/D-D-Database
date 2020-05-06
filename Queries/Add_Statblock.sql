Use DungeonDatabase
GO

Create Procedure Add_Statblock (@Player int, @Stat int) AS

if (@Stat = NULL OR @Stat NOT IN (Select StatID
								  From StatBlock))
BEGIN
	RAISERROR('Invalid Stat Block ID', 18, 0)
	Return 1
END

Update Player_Character
Set StatID = @Stat
Where PlayerID = @Player

Return 0

