Use DungeonDatabase
Go

Create Procedure Delete_User (@username varChar(200), @pwd varChar(200)) AS

Declare @date as date
Set @date = GETDATE()

IF (@username NOT IN (Select username
					  From UserEntity as UE
					  Where UE.Username = @username
					  AND UE.Pwd = @pwd))
BEGIN
	RAISERROR('Error: Username or Password not found', 18, 0)
	Return 1
END

DELETE FROM UserEntity WHERE UserEntity.Username = @username AND UserEntity.Pwd = @pwd
RETURN 0