Create USER dungeon19 From Login dungeon19;
exec sp_addrolemember 'db_datareader', dungeon19;
exec sp_addrolemember 'db_datawriter', dungeon19;
exec sp_addrolemember 'db_owner', dungeon19;

Create USER moyersjm From Login moyersjm;
exec sp_addrolemember 'db_owner', moyersjm;

Create USER caranid From Login caranid;
exec sp_addrolemember 'db_owner', caranid;


Create USER altobes From Login altobes;
exec sp_addrolemember 'db_owner', altobes;
