CREATE Procedure [dbo].[Change_Player_Name] (@Player int, @Name Varchar(200) = 'John Smith') AS

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