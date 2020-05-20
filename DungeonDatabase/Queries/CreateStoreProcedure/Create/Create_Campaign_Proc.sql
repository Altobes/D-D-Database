CREATE Procedure [dbo].[Create_Campaign](
	@Name nvarchar(75) = '',
	@PartyID int = null,
	@User nvarchar(75) = null
)
AS

If (@PartyID is null)
Begin
	Print 'ERROR: Must provide valid party id' 
	Return 1
End

If not exists (select * from Party where PartyID = @PartyID)
Begin
	Print 'ERROR: Party does not exist' 
	return 2
End

If ((Select Username from [User] where @User = Username) is null) 
Begin
	Print 'Error: User not listed'
	Return 3
End

If ((Select Username from DM Where Username = @User) is null)
BEGIN
	Print 'Error: User not DM'
	Return 4
END

Declare @CampaignID int 
SET @CampaignID = (select MAX(CampaignID) from Campaign)
IF (@CampaignID is null)
BEGIN
	SET @CampaignID = 0
END
Set @CampaignID += 1

If (@Name is null or @Name = '')
Begin
	Set @Name = 'Its a dangerous business going out your door...'
End

Declare @DM int 
Set @DM = (select DM_ID
			From DM
			Where Username = @User)

Insert into Campaign([Name], CampaignID, PartyID)
Values(@Name, @CampaignID, @PartyID)

Insert into DM_Manages_Campaign (DM_ID, CampaignID)
Values (@DM, @CampaignID)

return 0