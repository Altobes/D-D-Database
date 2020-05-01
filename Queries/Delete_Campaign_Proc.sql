USE [DungeonDatabase]
GO
/****** Object:  StoredProcedure [dbo].[CreateStatBlock]    Script Date: 5/1/2020 2:20:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[Delete_Campaign](
	@DeleteCampID int
)
AS

If ((Select CampaignID from Campaign where @DeleteCampID = CampaignID) = null)
Begin
	RaisError('Invalid or Incorrect campaign deletion id', 14, 1)
End

Delete from Campaign where CampaignID = @DeleteCampID