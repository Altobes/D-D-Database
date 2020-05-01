USE [DungeonDatabase]
GO
/****** Object:  StoredProcedure [dbo].[CreateStatBlock]    Script Date: 5/1/2020 2:20:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[Create_Spell] (
	@Name nvarchar(50),
	@Descript nvarchar(500)
)
AS

If (@Name is null or @Name = '' or @Descript is null or @Descript = '')
Begin
	RaisError('Must provide valid Name and Description', 14, 1)
End

Declare @spellName nvarchar(50)
Set @spellName = (Select TOP(1) Spells.Name from Spells where Name = @Name)

If (@spellName is not null)
Begin
	RaisError('ERROR: Spell already exists', 14, 2)
End

Declare @SpellID int
SET @SpellID = (select MAX(SpellID) from Spells)
IF (@SpellID is null)
BEGIN
	SET @SpellID = 1
END

Insert into Spells (Name, Description, SpellID)
Values(@Name, @Descript, @SpellID)