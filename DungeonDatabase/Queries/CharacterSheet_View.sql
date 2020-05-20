CREATE VIEW CharacterSheet
AS
SELECT Statblock.Name as Name, AC, Speed, Race, [STR], DEX, CON, [INT], WIS, 
Spells.Name as Spell, Skills.Name as Skill, Items.Name as Item
FROM StatBlock
JOIN StatSpells ON StatBlock.StatID = StatSpells.StatID
JOIN StatSkills ON StatBlock.StatID = StatSkills.StatID
JOIN StatItems ON StatBlock.StatID = StatItems.StatID
JOIN Items ON StatItems.ItemID = Items.ItemID
JOIN Skills ON StatSkills.SkillID = Skills.SkillID
JOIN Spells ON StatSpells.SpellID = Spells.SpellID

--DROP VIEW CharacterSheet