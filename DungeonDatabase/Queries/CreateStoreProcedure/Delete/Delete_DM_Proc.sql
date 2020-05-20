CREATE PROCEDURE [dbo].[delete_DM] (@DM_ID int)
AS
--Check to see if NPC_ID is valid
IF (Select DM_ID FROM DM WHERE DM_ID=@DM_ID)=0
	BEGIN
		PRINT 'Error: The parameters are invalid.'
		RETURN 1
	END
DELETE DM 
WHERE DM_ID = @DM_ID
--Check for errors
DECLARE @Status SMALLINT
SET @Status = @@ERROR
IF @Status <> 0 
BEGIN
	--Return error code to the calling program to indicate failure
	PRINT 'An error occurred'
	RETURN(@Status)
END