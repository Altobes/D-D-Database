CREATE Procedure [dbo].[Delete_Campaign](
	@DeleteCampID int = null
)
AS

If ((Select CampaignID from Campaign where @DeleteCampID = CampaignID) = null)
Begin
	Print 'ERROR: Invalid campaign ID'
	Return 1
End

Delete from Campaign where CampaignID = @DeleteCampID