
CREATE Procedure [dbo].[Change_Backstory] (@Player int, @Story varChar(200) = '') AS

IF (@Player IS NULL or @Player NOT IN (Select PlayerID
									   From Player_Character as PC
									   Where PC.PlayerID = @Player))
BEGIN
	RAISERROR('ERROR: Invalid PlayerID', 18, 0)
	Return 1
END

Update Player_Character
Set back_story = @story
Where PlayerID = @Player

Return 0