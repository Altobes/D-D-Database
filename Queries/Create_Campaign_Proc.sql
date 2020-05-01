USE [DungeonDatabase]
GO
/****** Object:  StoredProcedure [dbo].[CreateStatBlock]    Script Date: 5/1/2020 2:20:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[Create_Campaign](
	@Name nvarchar(75),
	@PartyID int
)
AS

If (@PartyID is null)
Begin
	RaisError('ERROR: Must provide valid party id', 14, 1)
End

Declare @CampaignID int 
SET @CampaignID = (select MAX(CampaignID) from Campaign)
IF (@CampaignID is null)
BEGIN
	SET @CampaignID = 1
END

If (@Name is null or @Name = '')
Begin
	Set @Name = 'Its a dangerous business going out your door...' + @CampaignID
End

Insert into Campaign([Name], CampaignID, PartyID)
Values(@Name, @CampaignID, @PartyID)