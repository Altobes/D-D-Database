USE [DungeonDatabase]
GO
/****** Object:  StoredProcedure [dbo].[CreateStatBlock]    Script Date: 5/1/2020 2:20:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[Create_Item] (
	@Name nvarchar(50),
	@Descript nvarchar(500)
)
AS

If (@Name is null or @Name = '' or @Descript is null or @Descript = '')
Begin
	RaisError('Must provide valid Name and Description', 14, 1)
End

Declare @itemName nvarchar(50)
Set @itemName = (Select TOP(1) Items.Name from Items where Name = @Name)

If (@itemName is not null)
Begin
	RaisError('ERROR: Item already exists', 14, 2)
End

Declare @ItemID int
SET @ItemID = (select MAX(@ItemID) from Items)
IF (@ItemID is null)
BEGIN
	SET @ItemID = 0
END
SET @ItemID = @ItemID + 1

Insert into Items(Name, Description, ItemID)
Values(@Name, @Descript, @ItemID)