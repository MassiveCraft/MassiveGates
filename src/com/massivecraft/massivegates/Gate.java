package com.massivecraft.massivegates;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.massivecraft.massivegates.event.GateBeforeTeleportEvent;
import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.event.GateUseEvent;
import com.massivecraft.massivegates.fx.GateFx;
import com.massivecraft.massivegates.fx.GateFxMoment;
import com.massivecraft.massivegates.util.TeleportUtil;
import com.massivecraft.massivegates.util.VisualizeUtil;

public class Gate extends com.massivecraft.mcore1.persist.Entity<Gate>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	@Override public Gates getManager() { return Gates.i; }
	@Override protected Gate getThis() { return this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private boolean open;
	public boolean isOpen() { return this.open; } // TODO: Trigger events here?
	public void setOpen(boolean val)
	{
		this.open = val;
		if (this.open)
		{
			this.fillContent(this.getMatopen());
			this.runFx(GateFxMoment.OPEN, null);
		}
		else
		{
			this.fillContent(this.getMatclosed());
			this.runFx(GateFxMoment.CLOSE, null);
		}
	}
	
	// FIELD: name
	private String name;
	public String getName() { return this.name; }
	public void setName(String val) { this.name = val; }
	
	// FIELD: desc
	private String desc;
	public String getDesc() { return this.desc; }
	public void setDesc(String val) { this.desc = val; }
	
	// FIELD: matopen
	private Material matopen;
	public Material getMatopen()
	{
		return this.matopen;
	}
	public void setMatopen(Material val)
	{
		this.matopen = val;
		if (this.open == true)
		{
			this.fillContent(this.matopen);
		}
	}
	
	// FIELD: matclosed
	private Material matclosed;
	public Material getMatclosed()
	{
		return this.matclosed;
	}
	public void setMatclosed(Material val)
	{
		this.matclosed = val;
		if (this.open == false)
		{
			this.fillContent(this.matclosed);
		}
	}
	
	// FIELD: exit
	private LocWrap exit;
	public LocWrap getExit() { return this.exit; }
	public void setExit(LocWrap val) { this.exit = val; }
	
	// -------------------------------------------- //
	// FIELD TARGET
	// -------------------------------------------- //
	
	// FIELD: targetFixedLoc - used when the gate is targeting a fixed location
	private LocWrap targetFixedLoc;
	public LocWrap getTargetFixedLoc()
	{
		return this.targetFixedLoc;
	}
	public void setTargetFixedLoc(LocWrap val)
	{
		this.targetFixedLoc = val;
		this.targetGateId = null;
	}
	
	// FIELD: targetGateId - Used when the gate is targeting another certain gate
	private String targetGateId;
	public String getTargetGateId()
	{
		return this.targetGateId;
	}
	public void setTargetGateId(String val)
	{
		this.targetGateId = val;
		this.targetFixedLoc = null;
	}
	public Gate getTargetGate()
	{
		return Gates.i.get(this.getTargetGateId());
	}
	public void setTargetGate(Gate val)
	{
		this.setTargetGateId(val.getId());
	}
	
	// MIXED
	public LocWrap getTarget()
	{
		if (this.targetFixedLoc != null) return this.targetFixedLoc;
		if (this.getTargetGate() != null && this.getTargetGate().getExit() != null) return this.getTargetGate().getExit();
		return null;
	}
	
	public TargetType getTargetType()
	{
		if (this.targetFixedLoc != null) return TargetType.LOCATION;
		if (this.getTargetGate() != null && this.getTargetGate().getExit() != null) return TargetType.GATE;
		return TargetType.NONE;
	}
	
	public String getTargetDesc()
	{
		String ret = null;
		TargetType targetType = this.getTargetType();
		switch(targetType)
		{
			case LOCATION: 
				LocWrap wloc = getTargetFixedLoc();
				ret = "<k>Location: <v>"+wloc.getVeryShortDesc();
			break;
			case GATE: 
				Gate gate = getTargetGate();
				ret = "<k>Gate: <v>"+gate.getIdNameStringShort();
			break;
			case NONE: 
				ret = "<v>*NONE*";
			break;
		}
		return ret;
	}
	
	// -------------------------------------------- //
	// FIELD CONTENT
	// -------------------------------------------- //
	
	private Set<WorldCoord3> content;
	public Set<WorldCoord3> getContent() { return this.content; } // TODO: Make immutable?
	public void addContent(WorldCoord3 coord)
	{
		// Easy if detached
		if (this.detached())
		{
			// Just add - nothing else
			this.content.add(coord);
			return;
		}
		
		// Overwrite
		Gate currentGateThere = Gates.i.getGateAtCoord(coord);
		if (currentGateThere != null)
		{
			currentGateThere.delContent(coord);
			currentGateThere.delFrame(coord);
		}
		
		// Add
		this.content.add(coord);
		
		// Index
		Gates.i.contentToGate.put(coord, this);
		
		// Ensure material
		if (this.isOpen())
		{
			Block block = coord.getBlock();
			if (block == null) return;
			block.setType(this.matopen);
		}
	}
	
	public void delContent(WorldCoord3 coord)
	{
		// Easy if detached
		if (this.detached())
		{
			// Just delete - nothing else
			this.content.remove(coord);
			return;
		}
		
		// Delete
		this.content.remove(coord);		
		
		// Index
		Gates.i.contentToGate.remove(coord);
		
		// Ensure material
		if (this.open)
		{
			Block block = coord.getBlock();
			if (block == null) return;
			block.setType(Material.AIR);
		}
	}
	
	public void clearContent()
	{
		for (WorldCoord3 coord : this.getContent())
		{
			this.delContent(coord);
		}
	}
	public void setContent(Collection<WorldCoord3> coords)
	{
		this.clearContent();
		this.addContent(coords);
	}
	public void addContent(Collection<WorldCoord3> coords)
	{
		for (WorldCoord3 coord : coords)
		{
			this.addContent(coord);
		}
	}
	public void addContentBlocks(Collection<Block> blocks)
	{
		this.addContent(WorldCoord3.populateBlocks(new ArrayList<WorldCoord3>(blocks.size()), blocks));
	}
	
	// -------------------------------------------- //
	// FIELD FRAME
	// -------------------------------------------- //
	
	private Set<WorldCoord3> frame;
	public Set<WorldCoord3> getFrame() { return this.frame;} // TODO: Make immutable?
	
	public void addFrame(WorldCoord3 coord)
	{
		// Easy if detached
		if (this.detached())
		{
			// Just add - nothing else
			this.frame.add(coord);
			return;
		}
		
		// Overwrite
		Gate currentGateThere = Gates.i.getGateAtCoord(coord);
		if (currentGateThere != null)
		{
			currentGateThere.delContent(coord);
			currentGateThere.delFrame(coord);
		}
		
		// Add
		this.frame.add(coord);
		
		// Index
		Gates.i.frameToGate.put(coord, this);
	}
	
	public void delFrame(WorldCoord3 coord)
	{
		// Easy if detached
		if (this.detached())
		{
			// Just delete - nothing else
			this.frame.remove(coord);
			return;
		}
		
		// Delete
		this.frame.remove(coord);
		
		// Index
		Gates.i.frameToGate.remove(coord);
	}
	
	public void clearFrame()
	{
		for (WorldCoord3 coord : this.getFrame())
		{
			this.delFrame(coord);
		}
	}
	public void setFrame(Collection<WorldCoord3> coords)
	{
		this.clearFrame();
		this.addFrame(coords);
	}
	public void addFrame(Collection<WorldCoord3> coords)
	{
		for (WorldCoord3 coord : coords)
		{
			this.addFrame(coord);
		}
	}
	public void addFrameBlocks(Collection<Block> blocks)
	{
		this.addFrame(WorldCoord3.populateBlocks(new ArrayList<WorldCoord3>(blocks.size()), blocks));
	}
	
	// -------------------------------------------- //
	// FIELD FX
	// -------------------------------------------- //
	
	protected Map<GateFxMoment, List<String>> fxMoment2Parsies;
	protected void ensureFxListExists(GateFxMoment fxMoment)
	{
		if (this.fxMoment2Parsies.containsKey(fxMoment)) return;
		this.fxMoment2Parsies.put(fxMoment, new ArrayList<String>());
	}
	public List<String> getFxParsies(GateFxMoment fxMoment)
	{
		this.ensureFxListExists(fxMoment);
		return this.fxMoment2Parsies.get(fxMoment);
	}
	public void addFxParsie(GateFxMoment fxMoment, String parsie)
	{
		this.ensureFxListExists(fxMoment);
		this.fxMoment2Parsies.get(fxMoment).add(parsie);
	}
	public boolean delFxParsieByIndex(GateFxMoment fxMoment, int index)
	{
		this.ensureFxListExists(fxMoment);
		List<String> parsies = this.getFxParsies(fxMoment);
		try
		{
			parsies.remove(index);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	public int delFxParsieByStart(GateFxMoment fxMoment, String start)
	{
		int ret = 0;
		this.ensureFxListExists(fxMoment);
		List<String> parsies = this.getFxParsies(fxMoment);
		start = start.toLowerCase();
		for (Iterator<String> iter = parsies.iterator(); iter.hasNext();)
		{
			String parsie = iter.next();
			parsie.toLowerCase();
			if (parsie.startsWith(start))
			{
				iter.remove();
				ret += 1;
			}
		}
		return ret;
	}
	public void runFx(GateFxMoment fxMoment, Entity entity)
	{
		List<String> parsies = this.getFxParsies(fxMoment);
		for (String parsie : parsies)
		{
			GateFx fx = Gates.i.getFx(parsie);
			if (fx == null) continue;
			fx.run(parsie, this, entity);
		}
	}

	// -------------------------------------------- //
	// CONSTRUCTORS
	// -------------------------------------------- //
			
	public Gate()
	{
		this.open = false;
		this.matclosed = Material.AIR;
		this.matopen = Material.PORTAL;
		this.content = new HashSet<WorldCoord3>();
		this.frame = new HashSet<WorldCoord3>();
		this.fxMoment2Parsies = new EnumMap<GateFxMoment, List<String>>(GateFxMoment.class);
	}
			
	// -------------------------------------------- //
	// GATE UTILITIES
	// -------------------------------------------- //
	
	public Location calcGateCenter()
	{
		// TODO: IMPROVE
		
		Location ret = null;
		
		if ( ! this.getContent().isEmpty())
		{
			ret = this.getContent().iterator().next().getLocation();
			if (ret != null) return ret;
		}
		
		if ( ! this.getFrame().isEmpty())
		{
			ret = this.getFrame().iterator().next().getLocation();
			if (ret != null) return ret;
		}
		
		return ret;
	}
	
	// -------------------------------------------- //
	// STRING GEN
	// -------------------------------------------- //
	
	public String getIdNameStringShort()
	{
		String ret = "<h>"+this.getId();
		if (this.getName() != null)
		{
			ret += " <aqua>"+this.getName();
		}
		return ret;
	}
	
	public String getIdNameStringLong()
	{
		String ret = "<h>id <aqua>"+this.getId();
		if (this.getName() != null)
		{
			ret += " <h>name <aqua>"+this.getName()+"<i>";
		}
		return ret;
	}

	// -------------------------------------------- //
	// ACTIONS AND EVENTS
	// -------------------------------------------- //
	
	public void use(Entity user)
	{
		// Call the use event
		GateUseEvent useEvent = new GateUseEvent(this, user);
		Bukkit.getPluginManager().callEvent(useEvent);
		if (useEvent.isCancelled()) return;
		
		// Store from and to locations
		Location from = user.getLocation();
		Location to = this.getTarget().getLocation();
		
		// Call the before event
		new GateBeforeTeleportEvent(this, user, from, to).run();
		
		// Do safe teleport
		TeleportUtil.safeTeleport(user, to, 10);
		
		// Call the after teleport event a bit later
		new GateAfterTeleportEvent(this, user, from, to).run(15);
	}
	
	// -------------------------------------------- //
	// VISUAL
	// -------------------------------------------- //
	
	public void visualizeFor(Player player)
	{
		VisualizeUtil.addCoords(player, this.getFrame(), Conf.visFrame);
		VisualizeUtil.addCoords(player, this.getContent(), Conf.visContent);
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