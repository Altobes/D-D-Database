
CREATE Procedure [dbo].[Drop_Stackblock] (@Player int = null) as

If (@Player is NULL or @Player NOT IN (Select PlayerID
									   From Player_Character))
BEGIN
	Print 'Invalid PlayerID' 
	Return 1
END

Delete From StatBlock Where StatBlock.StatID = (Select StatID
												From Player_Character
												Where PlayerID = @Player)

Return 0