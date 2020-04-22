USE [DungeonDatabase]
GO

/****** Object:  Table [dbo].[Party]    Script Date: 4/22/2020 5:10:37 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Party](
	[Level] [int] Default 1,
	[Name] [varchar](50) NOT NULL,
	[PartyID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PartyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


