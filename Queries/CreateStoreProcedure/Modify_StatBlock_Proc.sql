CREATE Procedure [dbo].[Modify_StatBlock] (
  @StatID int = null,
  @Name varchar(50) = null, 
  @AddLanguage varchar(50) = null,
  @AC int = null,
  @Speed int = null,
  @Race varchar(50) = null,
  @DeleteCharacter varchar(50) = null, 
  @STR int = null, @DEX int = null, @CON int = null, @INT int = null, @WIS int = null, @CHA int = null
)
AS

If (@StatID is null)
Begin
	Print 'ERROR:Invalid StatID' 
	Return 1
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

Declare @lang varchar(50)
Set @lang = (Select Languages from StatBlock where StatID = @StatID)
If (@AddLanguage is not null and @AddLanguage != '')
Begin
	Update StatBlock
	Set Languages = Languages + @lang
	Where @StatID = StatID
End

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