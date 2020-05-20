CREATE PROCEDURE [dbo].[Delete_Item] (@ItemID int = null, @StatID int = null)
AS
DECLARE @tmp int
SELECT @tmp = Count(Name) FROM Items WHERE ItemID = @ItemID

IF (@tmp != 1) BEGIN
	RAISERROR('Error, item does not exist', 10, 1)
	RETURN 1
END

SELECT @tmp = Count(Name) FROM StatBlock WHERE StatID = @StatID

IF (@tmp != 1) BEGIN
	RAISERROR('Error, Stat Block does not exist', 10, 1)
	RETURN 2
END

DELETE FROM StatItems 
WHERE ItemID = @ItemID AND StatID = @StatID

RETURN 0