package com.massivecraft.massivegates.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.bukkit.block.BlockFace;

public enum FloodOrientation
{
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
	;
	
	protected final Set<BlockFace> directions;
	public Set<BlockFace> getDirections() { return this.directions; }
	
	protected final String desc;
	public String getDesc() { return this.desc; }
	
	private FloodOrientation(final String desc, final BlockFace... directions)
	{
		this.directions = new LinkedHashSet<BlockFace>(Arrays.asList(directions));
		this.desc = desc;
	}
	
}
