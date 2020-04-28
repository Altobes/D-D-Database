Use DungeonDatabase
Go

Create Procedure Create_User (@username varChar(200), @pwd varChar(200)) AS

Declare @date as date
Set @date = GETDATE()

IF (@username is null or @username = '')
BEGIN
	RAISERROR('Error: Username cannot be empty', 18, 0)
	Return 1
END

IF (@pwd is null or @username = '')
BEGIN
	RAISERROR('Error: Password cannot be empty', 18, 0)
	Return 2
END

INSERT INTO UserEntity (username, pwd, Join_Date)
Values (@username, @pwd, @date)

RETURN 0