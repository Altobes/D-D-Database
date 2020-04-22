--Create the base StatBlock table
CREATE TABLE StatBlock (
	Name varchar(50),
	AC tinyint,
	Speed tinyint,
	StatID int,
	Race varchar(20),
	[STR] tinyint,
	DEX tinyint,
	CON tinyint,
	[INT] tinyint,
	WIS tinyint,
	CHA tinyint,
	Primary Key (StatID)
)

--There needs to be another table for each of Skills/Feats/Spells/Items
--This is the only way I could find to have multiple values, there are no lists/arrays in SQL
CREATE TABLE Spells (
	Name varchar(50),
	Description varchar(255),
	SpellID int,
	Primary Key (SpellID)
)

CREATE TABLE Skills (
	Name varchar(50),
	Description varchar(255),
	SkillID int,
	Primary Key (SkillID)
)

CREATE TABLE Items (
	Name varchar(50),
	Description varchar(255),
	ItemID int,
	Primary Key (ItemID)
)

--One way I found to combine these was, rather than a foreign key, have a mapping table.
--We can insert values saying "This StatID maps to these SpellIDs"
--We then join these tables into something that resembles the stat block and save it as a view.
CREATE TABLE StatSpells (
	StatID int,
	SpellID int,
	Foreign Key(StatID) References StatBlock(StatID),
	Foreign Key(SpellID) References Spells(SpellID)
)

CREATE TABLE StatSkills (
	StatID int,
	SkillID int,
	Foreign Key(StatID) References StatBlock(StatID),
	Foreign Key(SkillID) References Skills(SkillID)
)

CREATE TABLE StatItems (
	StatID int,
	ItemID int,
	Foreign Key(StatID) References StatBlock(StatID),
	Foreign Key(ItemID) References Items(ItemID)
)

--Here is the join statement that should produce the correct results
--We will most likely save this as a view
SELECT * FROM StatBlock
JOIN StatSpells ON StatBlock.StatID = StatSpells.StatID
JOIN StatSkills ON StatBlock.StatID = StatSkills.StatID
JOIN StatItems ON StatBlock.StatID = StatItems.StatID
JOIN Items ON StatItems.ItemID = Items.ItemID
JOIN Skills ON StatSkills.SkillID = Skills.SkillID
JOIN Spells ON StatSpells.SpellID = Spells.SpellID

--The code below this comment will undo any changes this code makes
/*
DROP TABLE StatBlock
DROP TABLE Skills
DROP TABLE Spells
DROP TABLE Items
DROP TABLE StatItems
DROP TABLE StatSkills
DROP TABLE StatSpells
*/
