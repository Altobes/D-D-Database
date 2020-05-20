CREATE PROCEDURE [dbo].[Increment_Level] (@PartyID_1 int = null)
AS

IF (@PartyID_1 is null) 
BEGIN
	Print 'Error: PartyID not specified' 
	RETURN 1
END

IF (@PartyID_1 NOT IN (SELECT PartyID FROM Party WHERE PartyID = @PartyID_1))
BEGIN
	Print 'Error: Party Does not exist' 
	RETURN 3
END

DECLARE @Level int
SELECT @Level = [Level] FROM Party WHERE PartyID = @PartyID_1

UPDATE Party
SET [Level] = @Level + 1
WHERE PartyID = @PartyID_1

Return 0