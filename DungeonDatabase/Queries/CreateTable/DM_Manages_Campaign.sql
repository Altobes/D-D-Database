/****** Object:  Table [dbo].[DM_Manages_Campaign]    Script Date: 5/15/2020 12:59:05 AM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[DM_Manages_Campaign](
	[DM_ID] [int] NOT NULL,
	[CampaignID] [int] NOT NULL
) ON [PRIMARY]

ALTER TABLE [dbo].[DM_Manages_Campaign]  WITH CHECK ADD  CONSTRAINT [FK_DM_Manages_Campaign_Campaign] FOREIGN KEY([CampaignID])
REFERENCES [dbo].[Campaign] ([CampaignID])
ON UPDATE CASCADE
ON DELETE CASCADE

ALTER TABLE [dbo].[DM_Manages_Campaign] CHECK CONSTRAINT [FK_DM_Manages_Campaign_Campaign]

ALTER TABLE [dbo].[DM_Manages_Campaign]  WITH CHECK ADD  CONSTRAINT [FK_DM_Manages_Campaign_DM] FOREIGN KEY([DM_ID])
REFERENCES [dbo].[DM] ([DM_ID])
ON UPDATE CASCADE

ALTER TABLE [dbo].[DM_Manages_Campaign] CHECK CONSTRAINT [FK_DM_Manages_Campaign_DM]



