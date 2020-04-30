USE [DungeonDatabase]
GO
/****** Object:  StoredProcedure [dbo].[CreateStatBlock]    Script Date: 4/30/2020 5:44:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE Procedure [dbo].[CreateStatBlock] (
  @Name varchar(50), 
  @Language varchar(50),
  @AC int,
  @Speed int = 30,
  @Race varchar(50),
  @STR int = 10, @DEX int = 10, @CON int = 10, @INT int = 10, @WIS int = 10, @CHA int = 10)
AS

Declare @StatID int

IF (@Race is null or @Race = '')
BEGIN
	SET @Race = 'Human'
END
IF (@STR < 0)
BEGIN
	SET @STR = 10
END
IF (@DEX < 0)
BEGIN
	SET @DEX = 10
END
IF (@CON < 0)
BEGIN
	SET @CON = 10
END
IF (@INT < 0)
BEGIN
	SET @INT = 10
END
IF (@WIS < 0)
BEGIN
	SET @WIS = 10
END
IF (@CHA < 0)
BEGIN
	SET @CHA = 10
END

IF (@Language is null or @Language = '')
BEGIN
	SET @Language = 'Common'
END

IF (@Speed < 0)
BEGIN
	SET @Speed = 30
END

Declare @LargestID int 
SET @LargestID = (select MAX(StatID) from StatBlock)
IF (@LargestID is null)
BEGIN
	SET @LargestID = 0
END
SET @StatID = @LargestID + 1

IF (@Name is null or @Name = '')
BEGIN
	SET @Name = 'John Smith ' + @StatID
END

INSERT INTO StatBlock ([Name], AC, Speed, StatID, Race, [STR], DEX, CON, [INT], WIS, CHA)
VALUES (
	@Name, @AC, @Speed, @StatID, @Race, @STR, @DEX, @CON, @INT, @WIS, @CHA
)
