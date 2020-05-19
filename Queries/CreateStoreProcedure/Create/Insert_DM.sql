CREATE PROCEDURE [dbo].[insert_DM] (
	@DM_ID [int],
	@Name varchar(200), 
	@Username varchar(200))
AS
If (@Name is null or @Name = '')
Begin
	RaisError('Error: Need DM name', 14, 1)
End
If ((select Username from DM where Username = @Username) is not null)
Begin 
	RaisError('Error: Already Exist a DM', 14, 3)
End
Insert into [DM] ([DM_ID], [Name], [Username])
VALUES (@DM_ID, @Name, @Username)


