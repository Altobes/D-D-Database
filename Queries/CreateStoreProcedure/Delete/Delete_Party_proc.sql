CREATE PROCEDURE [dbo].[Delete_Party]
		(@PartyID int)
AS

IF (@PartyID is null)
BEGIN
	Print('Error, PartyID is not specified')
	RETURN 2
END

IF (SELECT Count(PartyID) FROM Party WHERE PartyID = @PartyID) = 0
BEGIN
	PRINT('Error, Party Does not exist')
	RETURN 3
END

DELETE Party
	WHERE PartyID = @PartyID