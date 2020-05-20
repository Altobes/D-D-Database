CREATE PROCEDURE [dbo].[DM_Manages] (@user varChar(50), @campaign int)  AS

If ((Select Username from [User] where @user = Username) is null) 
Begin
	Print 'Error: User not listed'
	Return 1
End

If ((Select CampaignID from Campaign where @campaign = CampaignID) is null) 
Begin
	Print 'Error: Invalid Campaign'
	Return 2
End

insert into DM_Manages_Campaign (DM_ID, CampaignID)
values (@user, @campaign)
return 0

