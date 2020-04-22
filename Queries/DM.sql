CREATE TABLE DM(
Username varchar(200) NOT NULL,
DM_ID int NOT NULL,
Name varchar(200) NOT NULL,
Pwd varchar(200) NOT NULL,
Join_Date date 
Primary key(Username, DM_ID)
)