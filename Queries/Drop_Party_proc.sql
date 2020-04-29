CREATE PROCEDURE [Drop_Party]
		(@PartyID int)
AS

IF (@PartyID is null)
BEGIN
	Print('Error, PartyID is not specified')
	RETURN 2
END

DROP Party
	WHERE PartyID = @PartyID

GO