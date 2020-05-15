/****** Object:  Table [dbo].[Player_Character]    Script Date: 5/15/2020 1:06:06 AM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON


CREATE TABLE [dbo].[Player_Character](
	[PlayerID] [int] NOT NULL,
	[Name] [varchar](200) NOT NULL,
	[back_story] [varchar](200) NULL,
	[Username] [nvarchar](50) NOT NULL,
	[PartyID] [int] NULL,
	[StatID] [int] NULL,
 CONSTRAINT [PK__Player_C__4A4E74A88C56F642] PRIMARY KEY CLUSTERED 
(
	[PlayerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


ALTER TABLE [dbo].[Player_Character]  WITH CHECK ADD  CONSTRAINT [FK__Player_Ch__Party__75A278F5] FOREIGN KEY([PartyID])
REFERENCES [dbo].[Party] ([PartyID])


ALTER TABLE [dbo].[Player_Character] CHECK CONSTRAINT [FK__Player_Ch__Party__75A278F5]


ALTER TABLE [dbo].[Player_Character]  WITH CHECK ADD  CONSTRAINT [FK__Player_Ch__StatI__76969D2E] FOREIGN KEY([StatID])
REFERENCES [dbo].[StatBlock] ([StatID])
ON UPDATE CASCADE
ON DELETE CASCADE


ALTER TABLE [dbo].[Player_Character] CHECK CONSTRAINT [FK__Player_Ch__StatI__76969D2E]

