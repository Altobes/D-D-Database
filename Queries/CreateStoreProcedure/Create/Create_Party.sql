CREATE PROCEDURE [dbo].[Create_Party]
	(@Name_1 varchar(30) = NULL,
	 @Level_2 int = 1,
	 @Cur_Loc_3 varchar(255) = NULL)
AS

--Error Checking
IF (@Name_1 is null or @Name_1 = '')
BEGIN
	Print 'Error, Must include a party name'
	RETURN 1
END

IF (@Level_2 < 1) 
BEGIN
	Set @Level_2 = 1
END

If (@Cur_Loc_3 is null)
Begin
	Set @Cur_Loc_3 = 'The void'
End

Declare @PartyID int
SET @PartyID = (select MAX(PartyID) from Party)
IF (@PartyID is null)
BEGIN
	SET @PartyID = 0
END
SET @PartyID = @PartyID + 1

--Actual Procedure goes here
INSERT INTO Party (PartyID, [Name],Level, [Current Location])
VALUES (@PartyID, @Name_1, @Level_2, @Cur_Loc_3)

