CREATE Procedure [dbo].[Delete_Player] (@PlayerID int = null) AS

if (@PlayerID is NULL OR @PlayerID NOT IN (Select @PlayerID
										   From Player_Character as PC
										   Where PC.PlayerID = @PlayerID))
BEGIN
	Print 'ERROR: Invalid PlayerID'
	Return 1
END

Delete FROM Player_Character WHERE Player_Character.PlayerID = @PlayerID
Return 0