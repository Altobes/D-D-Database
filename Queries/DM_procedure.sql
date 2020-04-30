Use DungeonDatabase
Go

CREATE PROCEDURE [dbo].[insert_DM] (
	@DM_ID [int],
	@Name varchar(200), 
	@Username varchar(200))
AS
Insert into [DM] ([DM_ID], [Name], [Username])
VALUES (@DM_ID, @Name, @Username)

GO


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

Go

CREATE PROCEDURE [update_DM]
	(@DM_ID [int],
	 @Name varchar(200) = NULL,
	 @Username varchar(200) = NULL)
	 
AS
--Check to see if OrderID and ProductID is valid
IF (Select DM_ID FROM [DM] WHERE DM_ID=@DM_ID)=0
	BEGIN
		PRINT 'Error: The parameters are invalid.'
		RETURN 1
	END

IF (@Name is not null) 
	BEGIN
		UPDATE DM
		SET Name = @Name 
		WHERE DM_ID = @DM_ID
	END

IF (@Username is not null) 
	BEGIN
		UPDATE DM
		SET Username = @Username 
		WHERE DM_ID = @DM_ID
	END
GO