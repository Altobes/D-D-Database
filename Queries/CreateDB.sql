Create Database [DungeonDatabaseTest]
ON
	Primary (
	NAME = DungeonDatabaseData,
	FILENAME = 'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\DungeonDatabaseData.mdf',
	SIZE=10MB,
	MAXSIZE = 250MB,
	FILEGROWTH = 15%
	)
Log On (
	Name = 'DungeonTestLog',
	FILENAME = 'E:\Database\MSSQL12.MSSQLSERVER\MSSQL\DATA\DungeonTestLog.ldf',
	SIZE = 3MB,
	MAXSIZE= 25MB,
	FILEGROWTH = 15%
	)
