CREATE Procedure [dbo].[Drop_DM] (@DM_ID nvarchar(50) = null, @Campaign int = null) AS

If (@DM_ID is NULL or @DM_ID = '' ) --Add check for DM to campaign table
BEGIN
	Print 'ERROR: Need valid DM ID'
	Return 1
END

IF (@Campaign is NULL) 
BEGIN
	Print 'ERROR: Need valid Campaign ID' 
	Return 2
END

--Procedure will drop DM from campaign, not delete them
--Delete from DM where DM_ID = @DM_ID 


