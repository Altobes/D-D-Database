CREATE Procedure [dbo].[Delete_User] (@username varChar(200) = null, @pwd varChar(50) = null) AS

Declare @date as date
Set @date = GETDATE()

IF (@username NOT IN (Select Username
					  From [User] as UE
					  Where UE.Username = @username
					  AND UE.PasswordHash = @pwd))
BEGIN
	Print 'Error: Username or Password not found' 
	Return 1
END

DELETE FROM [User] WHERE [User].Username = @username --AND [User].p = @pwd
RETURN 0