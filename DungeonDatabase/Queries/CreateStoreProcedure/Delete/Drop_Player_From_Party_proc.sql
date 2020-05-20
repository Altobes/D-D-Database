CREATE PROCEDURE [dbo].[Drop_Player]
		(@PlayerID int = null, @PartyID int = null)
AS

IF (@PlayerID is null)
BEGIN
	Print 'Error: Need valid Player ID'
	RETURN 1
END

IF (@PartyID is null)
BEGIN
	Print 'Error: Need valid Party ID' 
	RETURN 2
END

IF (@PlayerID NOT IN (SELECT PlayerID FROM Player_Character WHERE @PartyID = PartyID))
BEGIN
	PRINT('Error, Party Does not exist')
	RETURN 3
END

UPDATE [Player_Character]
	SET PartyID = NULL
	WHERE PlayerID = @PlayerID

Return 0