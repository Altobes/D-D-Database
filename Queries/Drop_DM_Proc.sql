USE [DungeonDatabase]
GO
/****** Object:  StoredProcedure [dbo].[CreateStatBlock]    Script Date: 5/1/2020 2:20:00 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[Drop_DM] (
	@DM_ID nvarchar(50)
)
AS

Delete from DM where DM_ID = @DM_ID
