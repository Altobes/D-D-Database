Create Database DungeonDatabase
on
	Primary (
	NAME = DungeonDatabase,
	FILENAME = 'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\DungeonDatabase.mdf',
	SIZE=10MB,
	MAXSIZE = 250MB,
	FILEGROWTH = 15%
	)
Log On (
	Name = 'DungeonLog',
	FILENAME = 'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\DungeonLog.ldf',
	SIZE = 3MB,
	MAXSIZE= 25MB,
	FILEGROWTH = 15%
	)
