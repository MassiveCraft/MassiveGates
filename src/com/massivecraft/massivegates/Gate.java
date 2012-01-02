package com.massivecraft.massivegates;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;

import com.massivecraft.mcore1.persist.Entity;

public class Gate extends Entity<Gate>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	@Override public Gates getManager() { return Gates.i; }
	@Override protected Gate getThis() { return this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// FIELD: open TODO: Hör detta hemma här?
	private boolean open = false;
	public boolean isOpen() { return this.open; } // TODO: Trigger events here?
	public void setOpen(boolean val) { this.open = val; }
	
	// FIELD: name
	private String name = null;
	public String getName() { return this.name; }
	public void setName(String val) { this.name = val; }
	
	// FIELD: desc
	private String desc = null;
	public String getDesc() { return this.desc; }
	public void setDesc(String val) { this.desc = val; }
	
	// FIELD: content
	private Set<WorldCoord3> content = new HashSet<WorldCoord3>();
	public Set<WorldCoord3> getContent() { return this.content; }
	public void setContent(Set<WorldCoord3> val) { this.content = val;       this.getManager().rebuildContentCoordToGate(); }
	public void addContent(Set<WorldCoord3> val) { this.content.addAll(val); this.getManager().rebuildContentCoordToGate(); }
	
	// FIELD: frame
	private Set<WorldCoord3> frame = new HashSet<WorldCoord3>();
	public Set<WorldCoord3> getFrame() { return this.frame;}
	public void setFrame(Set<WorldCoord3> val) { this.frame = val;       this.getManager().rebuildFrameCoordToGate(); }
	public void addFrame(Set<WorldCoord3> val) { this.frame.addAll(val); this.getManager().rebuildFrameCoordToGate(); }
	
	// -------------------------------------------- //
	// GATE UTILITIES
	// -------------------------------------------- //
	
	// -------------------------------------------- //
	// STRING GEN
	// -------------------------------------------- //
	
	public String getIdNameStringShort()
	{
		String ret = "<h>"+this.getId();
		if (this.getName() != null)
		{
			ret += " <c>"+this.getName();
		}
		return ret;
	}
	
	public String getIdNameStringLong()
	{
		String ret = "<p>id <h>"+this.getId();
		if (this.getName() != null)
		{
			ret += " <p>name <h>"+this.getName()+"<i>";
		}
		return ret;
	}
	
	
	
	// -------------------------------------------- //
	// FILL
	// -------------------------------------------- //
	
	public static void fillCoords(Collection<WorldCoord3> coords, Material material, byte data)
	{
		for (WorldCoord3 coord : coords)
		{
			Block block = coord.getBlock();
			if (block == null) continue;
			block.setType(material);
			block.setData(data);
		}
	}
	
	public static void fillCoords(Collection<WorldCoord3> coords, Material material)
	{
		fillCoords(coords, material, (byte) 0);
	}
	
	public void fillContent(Material material, byte data)
	{
		fillCoords(this.getContent(), material, data);
	}
	
	public void fillContent(Material material)
	{
		fillCoords(this.getContent(), material);
	}
	
	public void fillFrame(Material material, byte data)
	{
		fillCoords(this.getFrame(), material, data);
	}
	
	public void fillFrame(Material material)
	{
		fillCoords(this.getFrame(), material);
	}
	
}