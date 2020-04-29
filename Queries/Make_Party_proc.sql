CREATE PROCEDURE [Create_Party]
	(@Name_1 varchar(30) = NULL,
	 @Level_2 int = 1,
	 @Cur_Loc_3 varchar(255) = NULL)
AS

--Error Checking
IF (@Name_1 is null)
BEGIN
	Print('Error, Must include a party name')
	RETURN 1
END

--Actual Procedure goes here
INSERT INTO Party ([Level], Name)
VALUES (@Level_2, @Name_1)

GO