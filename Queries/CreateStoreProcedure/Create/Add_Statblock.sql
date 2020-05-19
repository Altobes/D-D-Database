CREATE Procedure [dbo].[Add_Statblock] (@Player int ,@Stat int) as

If (@Stat NOT IN (Select StatID
					  From StatBlock))
BEGIN
	print 'Invalid StatID' 
	Return 1
END

If (@Player NOT IN (Select PlayerID
					  From Player_Character))
BEGIN
	Print 'Invalid PlayerID' 
	Return 2
END

Update Player_Character
Set StatID = @Stat
Where PlayerID = @Player

Return 0