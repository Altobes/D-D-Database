CREATE PROCEDURE [dbo].[update_DM]
	(@DM_ID [int],
	 @Username varchar(200) = NULL)
	 
AS
IF (Select DM_ID FROM [DM] WHERE DM_ID=@DM_ID)=0
	BEGIN
		PRINT 'Error: The parameters are invalid.'
		RETURN 1
	END

IF (@Username is not null) 
	BEGIN
		UPDATE DM
		SET Username = @Username 
		WHERE DM_ID = @DM_ID
	END

