CREATE PROCEDURE [Delete_Spell] (@SpellID int = null, @StatID int = null)
AS
DECLARE @tmp int
SELECT @tmp = Count(Name) FROM Spells WHERE SpellID = @SpellID

IF (@tmp != 1) BEGIN
	RAISERROR('Error, Spell does not exist', 10, 1)
	RETURN 1
END

SELECT @tmp = Count(Name) FROM StatBlock WHERE StatID = @StatID

IF (@tmp != 1) BEGIN
	RAISERROR('Error, Stat Block does not exist', 10, 1)
	RETURN 2
END

DELETE FROM StatSpells 
WHERE SpellID = @SpellID AND StatID = @StatID

RETURN 0