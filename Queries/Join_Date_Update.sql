Use DungeonDatabase
Go

Create Trigger [Join_Date_Update] on UserEntity
For Update
AS

if Update (Join_Date)
Begin 
	RaisError('Transaction cannot be processed. ***Join date cannot be modified***', 10, 1)
	Rollback Transaction
End