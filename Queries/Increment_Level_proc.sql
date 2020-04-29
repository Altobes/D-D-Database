CREATE PROCEDURE [Increment_Level] (@PartyID_1 int)
AS

IF (@PartyID_1 is null) BEGIN
	Print('Error, PartyID not specified')
	RETURN 1
END

DECLARE @NewLevel int
SELECT @NewLevel = [Level] FROM Party WHERE PartyID = @PartyID_1

@NewLevel = @NewLevel + 1

UPDATE Party
SET [Level] = @NewLevel 
WHERE PartyID = @PartyID_1

GO