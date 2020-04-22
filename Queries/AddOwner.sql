use DungeonDatabase
Go

Create USER yuans From Login yuans;
exec sp_addrolemember 'db_owner', yuans;
Go