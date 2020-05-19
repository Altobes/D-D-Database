USE [DungeonDatabase]
GO

/****** Object:  StoredProcedure [dbo].[update_DM]    Script Date: 5/19/2020 4:29:33 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


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
GO

