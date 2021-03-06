CREATE Procedure [dbo].[Create_Skill] (
	@Name nvarchar(50),
	@Descript nvarchar(500),
	@StatID int = null
)
AS

If (@Name is null or @Name = '' or @Descript is null or @Descript = '')
Begin
	RaisError('Must provide valid Name and Description', 14, 1)
End

Declare @skillName nvarchar(50)
Set @skillName = (Select TOP(1) Skills.Name from Skills where Name = @Name)


Declare @SkillID int
SET @SkillID = (select MAX(SkillID) from Skills)
IF (@SkillID is null)
BEGIN
	SET @SkillID = 0
END
SET @SkillID = @SkillID + 1

Insert into Skills (Name, Description, SkillID)
Values(@Name, @Descript, @SkillID)

DECLARE @StatCount int
set @Statcount = (SELECT Count(StatID) From StatBlock Where StatID = @StatID)
If (@StatID is not NULL AND @StatCount > 0)
BEGIN
	INSERT INTO StatSkills(SkillID, StatID)
	VALUES (@SkillID, @StatID)
END