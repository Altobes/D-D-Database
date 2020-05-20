/****** Object:  StoredProcedure [dbo].[Add_Player]    Script Date: 5/19/2020 3:28:37 PM ******/
CREATE PROCEDURE [dbo].[Add_Player]
		(@PlayerID int, @PartyID int)
AS

IF (@PlayerID is null)
BEGIN
	Print('Error, PlayerID Not Specified')
	RETURN 1
END

IF (@PartyID is null)
BEGIN
	Print('Error, PartyID is not specified')
	RETURN 2
END

IF (SELECT Count(PlayerID) FROM Player_Character WHERE PlayerID = @PlayerID) = 0
BEGIN
	PRINT('Error, Party Does not exist')
	RETURN 3
END

IF (SELECT Count(PartyID) FROM Party WHERE PartyID = @PartyID) = 0
BEGIN
	PRINT('Error, Party Does not exist')
	RETURN 4
END

UPDATE [Player_Character]
	SET PartyID = @PartyID
	WHERE PlayerID = @PlayerID


