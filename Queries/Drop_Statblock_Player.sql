USE DungeonDatabase
Go

Create Procedure Drop_Stackblock (@Player int) as

If (@Player NOT IN (Select PlayerID
					  From Player_Character))
BEGIN
	RAISERROR('Invalid PlayerID',18, 0)
	Return 1
END

Delete From StatBlock Where StatBlock.StatID = (Select StatID
												From Player_Character
												Where PlayerID = @Player)
Return 0