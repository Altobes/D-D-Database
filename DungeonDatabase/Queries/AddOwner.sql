Create USER dungeon19 From Login dungeon19;
exec sp_addrolemember 'db_datareader', dungeon19;
exec sp_addrolemember 'db_datawriter', dungeon19;