package com.massivecraft.massivegates;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class WorldCoord3 implements Cloneable
{	
	//----------------------------------------------//
	// FIELDS
	//----------------------------------------------//
	
	// FIELD: worldName
	private String worldName;
	public String getWorldName() { return this.worldName; }
	public void setWorldName(String val) { this.worldName = val; }
	public World getWorld() { return Bukkit.getWorld(getWorldName()); }
	public void setWorld (World val) { this.worldName = val.getName(); }
	
	// FIELD: x
	private int x;
	public int getX() { return this.x; }
	public void setX(int val) { this.x = val; }
	
	// FIELD: y
	private int y;
	public int getY() { return this.y; }
	public void setY(int val) { this.y = val; }
	
	// FIELD: z
	private int z;
	public int getZ() { return this.z; }
	public void setZ(int val) { this.z = val; }
	
	//----------------------------------------------//
	// LOADERS
	//----------------------------------------------//
	
	public void load(String persistString)
	{
		String[] parts = persistString.split("\\|");
		this.worldName = parts[0];
		this.x = Integer.parseInt(parts[1]);
		this.y = Integer.parseInt(parts[2]);
		this.z = Integer.parseInt(parts[3]);
	}
	
	public void load(WorldCoord3 coord)
	{
		this.worldName = coord.worldName;
		this.x = coord.x;
		this.y = coord.y;
		this.z = coord.z;
	}
	
	public void load(String worldName, int x, int y, int z)
	{
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void load(World world, int x, int y, int z)
	{
		this.load(world.getName(), x, y, z);
	}
	
	public void load(Location location)
	{
		this.load(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}
	
	public void load(Block block)
	{
		this.load(block.getWorld().getName(), block.getX(), block.getY(), block.getZ());
	}
	
	public void load(Entity entity)
	{
		this.load(entity.getLocation());
	}
	
	//----------------------------------------------//
	// CONSTRUCTORS
	//----------------------------------------------//
	
	public WorldCoord3()
	{
		this.worldName = null;
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public WorldCoord3(WorldCoord3 coord)
	{
		this.load(coord);
	}
	
	public WorldCoord3(String persistString)
	{
		this.load(persistString);
	}
	
	public WorldCoord3(String worldName, int x, int y, int z)
	{
		this.load(worldName, x, y, z);
	}
	
	public WorldCoord3(World world, int x, int y, int z)
	{
		this.load(world.getName(), x, y, z);
	}
	
	public WorldCoord3(Location location)
	{
		this.load(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}
	
	public WorldCoord3(Block block)
	{
		this.load(block.getWorld().getName(), block.getX(), block.getY(), block.getZ());
	}
	
	public WorldCoord3(Entity entity)
	{
		this.load(entity.getLocation());
	}
	
	//----------------------------------------------//
	// CONVERTERS
	//----------------------------------------------//
	
	public Block getBlock()
	{
		World world = this.getWorld();
		if ( world == null) return null;
		return world.getBlockAt(x, y, z);
	}
	
	public Location getLocation()
	{
		World world = this.getWorld();
		if ( world == null) return null;
		return new Location(world, x, y, z);
	}
	
	public String toString()
	{
		return "WorldCoord3["+worldName+","+x+","+y+","+z+"]";
	}
	
	public String toPersistString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(this.worldName);
		sb.append('|');
		sb.append(this.x);
		sb.append('|');
		sb.append(this.y);
		sb.append('|');
		sb.append(this.z);
		return sb.toString();
	}
	
	public static <T extends Collection<WorldCoord3>> T populateBlocks(T target, Collection<Block> blocks)
	{
		for (Block block : blocks)
		{
			target.add(new WorldCoord3(block));
		}
		return target;
	}
	
	//----------------------------------------------//
	// COMPARISON
	//----------------------------------------------//
	
	public int hashCode()
	{
		int hash = 3;
        hash = 19 * hash + (this.worldName != null ? this.worldName.hashCode() : 0);
        hash = 19 * hash + this.x;
        hash = 19 * hash + this.y;
        hash = 19 * hash + this.z;
        return hash;
	};
	
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		if (!(obj instanceof WorldCoord3)) return false;

		WorldCoord3 that = (WorldCoord3) obj;
		return this.x == that.x && this.y == that.y && this.z == that.z && ( this.worldName==null ? that.worldName==null : this.worldName.equals(that.worldName) );
	}
	
	//----------------------------------------------//
	// CLONE
	//----------------------------------------------//
	
	@Override
	public WorldCoord3 clone()
	{
		return new WorldCoord3(this);
	}

}