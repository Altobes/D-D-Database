CREATE PROCEDURE [dbo].[Register]
	@Username nvarchar(50) = null,
	@PasswordSalt varchar(50) = null,
	@PasswordHash varchar(50) = null
AS
BEGIN
	if @Username is null or @Username = ''
	BEGIN
		Print 'Username cannot be null or empty.' 
		RETURN (1)
	END
	if (@PasswordSalt is null or @PasswordSalt = '')
	BEGIN
		Print 'PasswordSalt cannot be null or empty.' 
		RETURN (2)
	END
	if (@PasswordHash is null or @PasswordHash = '')
	BEGIN
		Print 'PasswordHash cannot be null or empty.'
		RETURN (3)
	END
	IF ((SELECT COUNT(*) FROM [User]
          WHERE Username = @Username) > 0)
	BEGIN
      Print 'ERROR: Username already exists.' 
	  RETURN(4)
	END

	Declare @Date date
	Set @Date = GETDATE() 

	INSERT INTO [User](Username, PasswordSalt, PasswordHash, JoinDate)
	VALUES (@username, @passwordSalt, @passwordHash, @Date)
	Return 0
END

