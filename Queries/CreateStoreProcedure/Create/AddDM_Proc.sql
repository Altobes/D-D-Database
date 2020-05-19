Create Procedure [dbo].[Add_DM] (
  @Name varchar(50), 
  @Username varchar(50)
)
AS

If (@Name is null or @Name = '')
Begin
	RaisError('Error: Must provide valid DM name', 14, 1)
End

If ((Select Username from User where @Username = Username) is null) 
Begin
	RaisError('Error: User not listed', 14, 2)
End

If ((select Username from DM where Username = @Username) is not null)
Begin 
	RaisError('Error: Selected user is already a DM', 14, 3)
End

Declare @DM_ID int
SET @DM_ID = (select MAX(@DM_ID) from DM)
IF (@DM_ID is null)
BEGIN
	SET @DM_ID = 0
END
SET @DM_ID = @DM_ID + 1

Insert into DM(Username, DM_ID, [Name])
Values(@Username, @DM_ID, @Name)

