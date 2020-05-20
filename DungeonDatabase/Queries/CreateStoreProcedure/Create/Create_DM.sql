CREATE Procedure [dbo].[CreateDM] ( 
  @Username varchar(50) = null
)
AS

If ((Select Username from [User] where @Username = Username) is null) 
Begin
	Print 'Error: User not listed'
	Return 2
End

If ((select Username from DM where Username = @Username) is not null)
Begin 
	Print 'Error: Selected user is already a DM'
	Return 3
End

Declare @DM_ID int
SET @DM_ID = (select MAX(DM_ID) from DM)
IF (@DM_ID is null)
BEGIN
	SET @DM_ID = 0
END
SET @DM_ID += 1

Insert into DM(Username, DM_ID)
Values(@Username, @DM_ID)
return 1
