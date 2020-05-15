CREATE TABLE [dbo].[Party](
	[Level] [int] NULL,
	[Name] [varchar](50) NOT NULL,
	[PartyID] [int] NOT NULL,
	[Current Location] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[PartyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] 

ALTER TABLE [dbo].[Party] ADD  CONSTRAINT [start_lvl]  DEFAULT ((1)) FOR [Level] 

ALTER TABLE [dbo].[Party]  WITH CHECK ADD  CONSTRAINT [CK_Party] CHECK  (([Level]>(0)))


ALTER TABLE [dbo].[Party] CHECK CONSTRAINT [CK_Party]



