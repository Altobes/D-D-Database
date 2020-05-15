SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON


CREATE TABLE [dbo].[DM](
	[Username] [varchar](200) NOT NULL,
	[DM_ID] [int] NOT NULL,
 CONSTRAINT [PK_DM] PRIMARY KEY CLUSTERED 
(
	[DM_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]