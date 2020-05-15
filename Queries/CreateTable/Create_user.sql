/****** Object:  Table [dbo].[User]    Script Date: 5/15/2020 1:14:09 AM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

CREATE TABLE [dbo].[User](
	[Username] [nvarchar](50) NOT NULL,
	[PasswordSalt] [varchar](50) NOT NULL,
	[PasswordHash] [varchar](50) NOT NULL,
	[JoinDate] [date] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]


