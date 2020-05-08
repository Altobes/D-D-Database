USE [DungeonDatabase]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE DM_Manages_Campaign(
	[DM_ID] int NOT NULL,
	[CampaignID] [int] NOT NULL,
)