CREATE PROCEDURE [dbo].[insert_DM] (
	@DM_ID [int],
	@Username varchar(200))
AS
If ((select Username from DM where Username = @Username) is not null)
Begin 
	RaisError('Error: Already Exist a DM', 14, 3)
End
Insert into [DM] ([DM_ID], [Username])
VALUES (@DM_ID, @Username)


