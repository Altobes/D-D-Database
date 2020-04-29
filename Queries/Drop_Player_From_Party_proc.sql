CREATE PROCEDURE [Drop_Player]
		(@PlayerID int)
AS

IF (@PlayerID is null)
BEGIN
	Print('Error, PlayerID Not Specified')
	RETURN 1
END

UPDATE [Player]
	SET PartyID = NULL
	WHERE PlayerID = @PlayerID

GO