CREATE Procedure [dbo].[Create_Item] (
	@Name nvarchar(50) = null,
	@Descript nvarchar(500) = null,
	@StatID int = null
)
AS

If (@Name is null or @Name = '' or @Descript is null or @Descript = '')
Begin
	RaisError('Must provide valid Name and Description', 14, 1)
	Return 1
End

Declare @itemName nvarchar(50)
Set @itemName = (Select TOP(1) Items.Name from Items where Name = @Name)

If (@itemName is not null)
Begin
	RaisError('ERROR: Item already exists', 14, 2)
	Return 2
End

Declare @ItemID int
SET @ItemID = (select MAX(ItemID) from Items)
IF (@ItemID is null)
BEGIN
	SET @ItemID = 0
END
SET @ItemID = @ItemID + 1

Insert into Items(Name, Description, ItemID)
Values(@Name, @Descript, @ItemID)

DECLARE @StatCount int
set @Statcount = (SELECT Count(StatID) From StatBlock Where StatID = @StatID)
If (@StatID is not NULL AND @StatCount > 0)
BEGIN
	INSERT INTO StatItems(ItemID, StatID)
	VALUES (@ItemID, @StatID)
END