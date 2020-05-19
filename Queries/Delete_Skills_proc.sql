CREATE PROCEDURE [Delete_Skill] (@SkillID int = null, @StatID int = null)
AS
DECLARE @tmp int
SELECT @tmp = Count(Name) FROM Skills WHERE SkillID = @SkillID

IF (@tmp != 1) BEGIN
	RAISERROR('Error, Skill does not exist', 10, 1)
	RETURN 1
END

SELECT @tmp = Count(Name) FROM StatBlock WHERE StatID = @StatID

IF (@tmp != 1) BEGIN
	RAISERROR('Error, Stat Block does not exist', 10, 1)
	RETURN 2
END

DELETE FROM StatSkills
WHERE SkillID = @SkillID AND StatID = @StatID

RETURN 0