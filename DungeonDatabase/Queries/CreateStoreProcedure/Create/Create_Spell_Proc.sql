CREATE Procedure [dbo].[Create_Spell] (
	@Name nvarchar(50) = null,
	@Descript nvarchar(500) = null,
	@StatID int = null
)
AS

If (@Name is null or @Name = '' or @Descript is null or @Descript = '')
Begin
	Print 'Must provide valid Name and Description'
	Return 1
End

Declare @spellName nvarchar(50)
Set @spellName = (Select TOP(1) Spells.Name from Spells where Name = @Name)

If (@spellName is not null)
Begin
	Print 'ERROR: Spell already exists'
	Return 2
End

Declare @SpellID int
SET @SpellID = (select MAX(SpellID) from Spells)
IF (@SpellID is null)
BEGIN
	SET @SpellID = 0
END
Set @SpellID += 1

Insert into Spells (Name, Description, SpellID)
Values(@Name, @Descript, @SpellID)

DECLARE @StatCount int
set @Statcount = (SELECT Count(StatID) From StatBlock Where StatID = @StatID)
If (@StatID is not NULL AND @StatCount > 0)
BEGIN
	INSERT INTO StatSpells(SpellID, StatID)
	VALUES (@SpellID, @StatID)
END