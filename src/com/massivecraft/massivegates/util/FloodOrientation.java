package com.massivecraft.massivegates.util;

import com.massivecraft.massivecore.collections.MassiveSet;
import org.bukkit.block.BlockFace;

import java.util.Arrays;
import java.util.Set;

public enum FloodOrientation
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	// |
	VERTICAL1("facing west/east", BlockFace.NORTH, BlockFace.SOUTH, BlockFace.UP, BlockFace.DOWN),
	
	// --
	VERTICAL2("facing north/south", BlockFace.WEST, BlockFace.EAST, BlockFace.UP, BlockFace.DOWN),
	
	// /
	VERTICAL3("facing northwest/southeast", BlockFace.NORTH_EAST, BlockFace.SOUTH_WEST, BlockFace.UP, BlockFace.DOWN),
	
	// \
	VERTICAL4("facing northeast/southwest", BlockFace.NORTH_WEST, BlockFace.SOUTH_EAST, BlockFace.UP, BlockFace.DOWN),
	
	// O
	HORIZONTAL("facing up/down", BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST),
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected final Set<BlockFace> directions;
	public Set<BlockFace> getDirections() { return this.directions; }
	
	protected final String desc;
	public String getDesc() { return this.desc; }
	
	FloodOrientation(final String desc, final BlockFace... directions)
	{
		this.directions = new MassiveSet<>(Arrays.asList(directions));
		this.desc = desc;
	}
	
}
