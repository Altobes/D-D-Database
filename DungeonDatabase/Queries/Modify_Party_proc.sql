CREATE PROCEDURE [Modify_Party]
	(@PartyID int,
	 @Name varchar(30) = NULL,
	 @Current_Location varchar(255) = NULL,
	 @Level int = NULL)
	 
AS

--------------------------
IF (@Name is not null) BEGIN
	UPDATE Party
	SET Name = @Name WHERE PartyID = @PartyID
END
--------------------------
IF (@Current_Location is not null) BEGIN
	UPDATE Party
	SET Current_Location = @Current_Location WHERE PartyID = @PartyID
END
--------------------------
IF (@Level is not null) BEGIN
	UPDATE NPC
	SET [Level] = @Level WHERE PartyID = @PartyID
END

GO
	