package com.massivecraft.massivegates.util;

import java.util.EnumMap;
import java.util.Map;

import org.bukkit.Material;

public class StandableUtil
{
	private static Map<Material, StandableInfo> m2s = new EnumMap<Material, StandableInfo>(Material.class);

	public static boolean validGround(Material material)
	{
		StandableInfo info = null;
		info = m2s.get(material);
		if (info == null) return true;
		return info.isValidGround();
	}
	
	public static boolean validLower(Material material)
	{
		StandableInfo info = null;
		info = m2s.get(material);
		if (info == null) return false;
		return info.isValidLower();
	}
	
	public static boolean validUpper(Material material)
	{
		StandableInfo info = null;
		info = m2s.get(material);
		if (info == null) return false;
		return info.isValidUpper();
	}
	
	protected static void setInfo(Material material, final boolean validGround, final boolean validLower, final boolean validUpper)
	{
		m2s.put(material, new StandableInfo(validGround, validLower, validUpper));
	}

	protected static void setInfo(int id, final boolean validGround, final boolean validLower, final boolean validUpper)
	{
		setInfo(Material.getMaterial(id), validGround, validLower, validUpper);
	}
	
	static
	{
		setInfo(0, false, true, true); //0 Air
		setInfo(1, true, false, false); //1 Stone
		setInfo(2, true, false, false); //2 Grass
		setInfo(3, true, false, false); //3 Dirt
		setInfo(4, true, false, false); //4 Cobblestone
		setInfo(5, true, false, false); //5 Wooden Planks
		setInfo(6, false, true, false); //6 Saplings D B
		setInfo(7, true, false, false); //7 Bedrock
		setInfo(8, false, true, false); //8 Water D
		setInfo(9, false, true, false); //9 Stationary water D
		setInfo(10, false, false, false); //10 Lava D
		setInfo(11, false, false, false); //11 Stationary lava D
		setInfo(12, true, false, false); //12 Sand
		setInfo(13, true, false, false); //13 Gravel
		setInfo(14, true, false, false); //14 Gold Ore
		setInfo(15, true, false, false); //15 Iron Ore
		setInfo(16, true, false, false); //16 Coal Ore
		setInfo(17, true, false, false); //17 Wood D B
		setInfo(18, true, false, false); //18 Leaves D B
		setInfo(19, true, false, false); //19 Sponge
		setInfo(20, true, false, false); //20 Glass
		setInfo(21, true, false, false); //21 Lapis Lazuli Ore
		setInfo(22, true, false, false); //22 Lapis Lazuli Block
		setInfo(23, true, false, false); //23 Dispenser D T
		setInfo(24, true, false, false); //24 Sandstone
		setInfo(25, true, false, false); //25 Note Block T
		setInfo(26, true, false, false); //26 Bed D I
		setInfo(27, false, true, false); //27 Powered Rail D
		setInfo(28, false, true, false); //28 Detector Rail D
		setInfo(29, true, false, false); //29 Sticky Piston D
		setInfo(30, false, false, false); //30 Cobweb
		setInfo(31, false, true, false); //31 Tall Grass D
		setInfo(32, false, true, false); //32 Dead Bush
		setInfo(33, true, false, false); //33 Piston D
		setInfo(34, true, false, false); //34 Piston Extension D
		setInfo(35, true, false, false); //35 Wool D B
		setInfo(36, true, false, false); //36 Block moved by Piston T
		setInfo(37, false, true, false); //37 Dandelion
		setInfo(38, false, true, false); //38 Rose
		setInfo(39, false, true, false); //39 Brown Mushroom
		setInfo(40, false, true, false); //40 Red Mushroom
		setInfo(41, true, false, false); //41 Block of Gold
		setInfo(42, true, false, false); //42 Block of Iron
		setInfo(43, true, false, false); //43 Double Slabs D B
		setInfo(44, true, true, false); //44 Slabs D B
		setInfo(45, true, false, false); //45 Bricks
		setInfo(46, true, false, false); //46 TNT
		setInfo(47, true, false, false); //47 Bookshelf
		setInfo(48, true, false, false); //48 Moss Stone
		setInfo(49, true, false, false); //49 Obsidian
		setInfo(50, false, true, true); //50 Torch D
		setInfo(51, false, false, false); //51 Fire D
		setInfo(52, true, false, false); //52 Monster Spawner T
		setInfo(53, true, true, false); //53 Wooden Stairs D
		setInfo(54, true, false, false); //54 Chest D T
		setInfo(55, false, true, false); //55 Redstone Wire D I
		setInfo(56, true, false, false); //56 Diamond Ore
		setInfo(57, true, false, false); //57 Block of Diamond
		setInfo(58, true, false, false); //58 Crafting Table
		setInfo(59, false, true, false); //59 Wheat Seeds D
		setInfo(60, true, false, false); //60 Farmland D
		setInfo(61, true, false, false); //61 Furnace D T
		setInfo(62, true, false, false); //62 Burning Furnace D T
		setInfo(63, false, true, true); //63 Sign Post D I T
		setInfo(64, false, true, true); //64 Wooden Door D I
		setInfo(65, false, true, true); //65 Ladders D
		setInfo(66, false, true, false); //66 Rails D
		setInfo(67, true, true, false); //67 Cobblestone Stairs D
		setInfo(68, false, true, true); //68 Wall Sign D T
		setInfo(69, false, true, true); //69 Lever D
		setInfo(70, false, true, false); //70 Stone Pressure Plate D
		setInfo(71, false, true, true); //71 Iron Door D I
		setInfo(72, false, true, false); //72 Wooden Pressure Plate D
		setInfo(73, true, false, false); //73 Redstone Ore
		setInfo(74, true, false, false); //74 Glowing Redstone Ore
		setInfo(75, false, true, true); //75 Redstone Torch ("off" state) D
		setInfo(76, false, true, true); //76 Redstone Torch ("on" state) D
		setInfo(77, false, true, true); //77 Stone Button D
		setInfo(78, false, true, false); //78 Snow D
		setInfo(79, true, false, false); //79 Ice
		setInfo(80, true, false, false); //80 Snow Block
		setInfo(81, false, false, false); //81 Cactus D
		setInfo(82, true, false, false); //82 Clay Block
		setInfo(83, false, true, true); //83 Sugar Cane D I
		setInfo(84, true, false, false); //84 Jukebox D T
		setInfo(85, true, false, false); //85 Fence
		setInfo(86, true, false, false); //86 Pumpkin D
		setInfo(87, true, false, false); //87 Netherrack
		setInfo(88, true, false, false); //88 Soul Sand
		setInfo(89, true, false, false); //89 Glowstone Block
		setInfo(90, false, true, true); //90 Portal
		setInfo(91, true, false, false); //91 Jack-O-Lantern D
		setInfo(92, true, false, false); //92 Cake Block D I
		setInfo(93, false, true, false); //93 Redstone Repeater ("off" state) D I
		setInfo(94, false, true, false); //94 Redstone Repeater ("on" state) D I
		setInfo(95, true, false, false); //95 Locked Chest
		setInfo(96, false, false, false); //96 Trapdoor D
		setInfo(97, true, false, false); //97 Hidden Silverfish D
		setInfo(98, true, false, false); //98 Stone Bricks D B
		setInfo(99, true, false, false); //99 Huge Brown Mushroom D
		setInfo(100, true, false, false); //100 Huge Red Mushroom D
		setInfo(101, true, false, false); //101 Iron Bars
		setInfo(102, true, false, false); //102 Glass Pane
		setInfo(103, true, false, false); //103 Melon
		setInfo(104, false, true, true); //104 Pumpkin Stem D
		setInfo(105, false, true, true); //105 Melon Stem D
		setInfo(106, false, true, true); //106 Vines D
		setInfo(107, false, false, false); //107 Fence Gate D
		setInfo(108, true, true, false); //108 Brick Stairs D
		setInfo(109, true, true, false); //109 Stone Brick Stairs D
		setInfo(110, true, false, false); //110 Mycelium
		setInfo(111, true, true, false); //111 Lily Pad
		setInfo(112, true, false, false); //112 Nether Brick
		setInfo(113, true, false, false); //113 Nether Brick Fence
		setInfo(114, true, true, false); //114 Nether Brick Stairs D
		setInfo(115, false, true, false); //115 Nether Wart D I
		setInfo(116, true, false, false); //116 Enchantment Table T
		setInfo(117, true, false, false); //117 Brewing Stand D T I
		setInfo(118, true, false, false); //118 Cauldron D I
		setInfo(119, false, true, true); //119 End Portal T
		setInfo(120, true, false, false); //120 End Portal Frame D
		setInfo(121, true, false, false); //121 End Stone
		setInfo(122, true, false, false); //122 Dragon Egg
	}
	
}
