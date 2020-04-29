CREATE PROCEDURE [Add_Player]
		(@PlayerID int, @PartyID int)
AS

IF (@PlayerID is null)
BEGIN
	Print('Error, PlayerID Not Specified')
	RETURN 1
END

IF (@PartyID is null)
BEGIN
	Print('Error, PartyID is not specified')
	RETURN 2
END

UPDATE [Player]
	SET PartyID = @PartyID
	WHERE PlayerID = @PlayerID

GO