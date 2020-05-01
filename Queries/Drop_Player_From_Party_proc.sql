CREATE PROCEDURE [Drop_Player]
		(@PlayerID int)
AS

IF (@PlayerID is null)
BEGIN
	Print('Error, PlayerID Not Specified')
	RETURN 1
END

IF (SELECT Count(PlayerID) FROM Player_Character WHERE PlayerID = @PlayerID) = 0
BEGIN
	PRINT('Error, Party Does not exist')
	RETURN 3
END

UPDATE [Player_Character]
	SET PartyID = NULL
	WHERE PlayerID = @PlayerID

GO