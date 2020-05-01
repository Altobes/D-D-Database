CREATE PROCEDURE [Increment_Level] (@PartyID_1 int)
AS

IF (@PartyID_1 is null) BEGIN
	Print('Error, PartyID not specified')
	RETURN 1
END

IF (SELECT Count(PartyID) FROM Party WHERE PartyID = @PartyID_1) = 0
BEGIN
	PRINT('Error, Party Does not exist')
	RETURN 3
END

DECLARE @Level int
SELECT @Level = [Level] FROM Party WHERE PartyID = @PartyID_1

UPDATE Party
SET [Level] = @Level + 1
WHERE PartyID = @PartyID_1

GO