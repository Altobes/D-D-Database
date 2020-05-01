USE [DungeonDatabase]
GO
/****** Object:  StoredProcedure [dbo].[Modify_StatBlock]    Script Date: 5/1/2020 2:16:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE Procedure [dbo].[Modify_StatBlock] (
  @StatID int,
  @Name varchar(50), 
  @AddLanguage varchar(50),
  @AC int,
  @Speed int = 30,
  @Race varchar(50),
  @DeleteCharacter varchar(50), 
  @STR int, @DEX int, @CON int, @INT int, @WIS int, @CHA int
)
AS

If (@StatID is null)
Begin
	RAISERROR('Invalid StatID', 14, 1)
	Return
End

If (@DeleteCharacter is not null)
Begin
	Delete from StatBlock where StatID = @StatID
	Return
End

If (@Name is not null)
Begin
	Update StatBlock
	Set Name = @Name
	Where @StatID = StatID
End
/*
If (@AddLanguage is not null)
Begin
	Update StatBlock
	Set Language = CONCAT((Select Language from StatBlock where StatID = @StatID), @AddLanguage);
	Where @StatID = StatID
End
*/
If (@AC is not null)
Begin
	Update StatBlock
	Set AC = @AC
	Where @StatID = StatID
End
If (@Speed is not null)
Begin
	Update StatBlock
	Set Speed = @Speed
	Where @StatID = StatID
End
If (@Race is not null)
Begin
	Update StatBlock
	Set Race = @Race
	Where @StatID = StatID
End
If (@STR is not null)
Begin
	Update StatBlock
	Set [STR] = @STR
	Where @StatID = StatID
End
If (@DEX is not null)
Begin
	Update StatBlock
	Set [DEX] = @DEX
	Where @StatID = StatID
End
If (@CON is not null)
Begin
	Update StatBlock
	Set [CON] = @CON
	Where @StatID = StatID
End
If (@INT is not null)
Begin
	Update StatBlock
	Set [INT] = @INT
	Where @StatID = StatID
End
If (@WIS is not null)
Begin
	Update StatBlock
	Set [WIS] = @WIS
	Where @StatID = StatID
End
If (@CHA is not null)
Begin
	Update StatBlock
	Set [CHA] = @CHA
	Where @StatID = StatID
End
