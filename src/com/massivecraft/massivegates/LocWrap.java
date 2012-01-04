package com.massivecraft.massivegates;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class LocWrap
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
    private double x;
	public double getX() { return this.x; }
	public void setX(double val) { this.x = val; }
    
    // FIELD: y
    private double y;
	public double getY() { return this.y; }
	public void setY(double val) { this.y = val; }
	
    // FIELD: z
    private double z;
	public double getZ() { return this.z; }
	public void setZ(double val) { this.z = val; }
	
    // FIELD: pitch
    private float pitch;
    public float getPitch() { return this.pitch; }
	public void setPitch(float val) { this.pitch = val; }
    
    // FIELD: yaw
    private float yaw;
    public float getYaw() { return this.yaw; }
	public void setYaw(float val) { this.yaw = val; }
	
	//----------------------------------------------//
	// LOADERS
	//----------------------------------------------//
	
	public void load(String persistString)
	{
		String[] parts = persistString.split("\\|");
		this.worldName = parts[0];
		this.x = Double.parseDouble(parts[1]);
		this.y = Double.parseDouble(parts[2]);
		this.z = Double.parseDouble(parts[3]);
		this.pitch = Float.parseFloat(parts[4]);
		this.yaw = Float.parseFloat(parts[5]);
	}
	
	public void load(LocWrap locw)
	{
		this.worldName = locw.worldName;
		this.x = locw.x;
		this.y = locw.y;
		this.z = locw.z;
		this.pitch = locw.pitch;
		this.yaw = locw.yaw;
	}
	
	public void load(String worldName, double x, double y, double z, float pitch, float yaw)
	{
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}
	
	public void load(World world, double x, double y, double z, float pitch, float yaw)
	{
		this.load(world.getName(), x, y, z, pitch, yaw);
	}
	
	public void load(Location location)
	{
		this.load(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
	}
	
	public void load(Entity entity)
	{
		this.load(entity.getLocation());
	}
	
	//----------------------------------------------//
	// CONSTRUCTORS
	//----------------------------------------------//
	
	public LocWrap()
	{
		this.worldName = null;
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.pitch = 0;
		this.yaw = 0;
	}
	
	public LocWrap(LocWrap locw)
	{
		this.load(locw);
	}
	
	public LocWrap(String persistString)
	{
		this.load(persistString);
	}
	
	public LocWrap(String worldName, double x, double y, double z, float pitch, float yaw)
	{
		this.load(worldName, x, y, z, pitch, yaw);
	}
	
	public LocWrap(World world, double x, double y, double z, float pitch, float yaw)
	{
		this.load(world, x, y, z, pitch, yaw);
	}
	
	public LocWrap(Location location)
	{
		this.load(location);
	}
	
	public LocWrap(Entity entity)
	{
		this.load(entity.getLocation());
	}
	
	//----------------------------------------------//
	// CONVERTERS
	//----------------------------------------------//
	
	public Location getLocation()
	{
		World world = this.getWorld();
		if ( world == null) return null;
		return new Location(world, x, y, z, yaw, pitch);
	}
	
	public String toString()
	{
		return "LocWrap["+worldName+","+x+","+y+","+z+","+pitch+","+yaw+"]";
	}
	
	public String getVeryShortDesc()
	{
		return worldName+", "+((int)x)+", "+((int)y)+", "+((int)z);
	}
	
	protected transient static DecimalFormat twoDForm = new DecimalFormat("#.##");
	public List<String> getDesc()
	{
		List<String> ret = new ArrayList<String>(5);
		ret.add("<h>World <a>"+this.worldName);
		ret.add("<h>X <a>"+twoDForm.format(this.x));
		ret.add("<h>Y <a>"+twoDForm.format(this.y));
		ret.add("<h>Z <a>"+twoDForm.format(this.z));
		ret.add("<h>Pitch <a>"+twoDForm.format(this.pitch));
		ret.add("<h>Yaw <a>"+twoDForm.format(this.yaw));
		return ret;
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
		sb.append('|');
		sb.append(this.pitch);
		sb.append('|');
		sb.append(this.yaw);
		return sb.toString();
	}
	
	public static <T extends Collection<LocWrap>> T populateLocations(T target, Collection<Location> locations)
	{
		for (Location location : locations)
		{
			target.add(new LocWrap(location));
		}
		return target;
	}
	
	//----------------------------------------------//
	// COMPARISON
	//----------------------------------------------//
	
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		
        if (getClass() != obj.getClass()) return false;
        
        final LocWrap other = (LocWrap) obj;

        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) return false;
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) return false;
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) return false;
        if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch)) return false;
        if (Float.floatToIntBits(this.yaw) != Float.floatToIntBits(other.yaw)) return false;
        
        if (this.worldName == null && other.worldName == null) return true;
        if (this.worldName == null)
        {
        	return other.worldName.equals(this);
        }
        else
        {
        	return this.worldName.equals(other);
        }
	}
	
	public int hashCode()
	{
        int hash = 3;
        hash = 19 * hash + (this.worldName != null ? this.worldName.hashCode() : 0);
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 19 * hash + Float.floatToIntBits(this.pitch);
        hash = 19 * hash + Float.floatToIntBits(this.yaw);
        return hash;
	};
	
	//----------------------------------------------//
	// CLONE
	//----------------------------------------------//
	
	@Override
	public LocWrap clone()
	{
		return new LocWrap(this);
	}
}
