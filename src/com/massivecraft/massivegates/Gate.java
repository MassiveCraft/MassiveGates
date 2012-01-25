package com.massivecraft.massivegates;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.event.GateBeforeTeleportEvent;
import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.event.GateOpenChangeEvent;
import com.massivecraft.massivegates.event.GatePowerChangeEvent;
import com.massivecraft.massivegates.event.GateUseEvent;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;
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
	
	protected boolean open;
	public boolean isOpen() { return this.open; }
	public void setOpen(boolean open)
	{
		this.open = open;
		this.fillContent();
		
		// This is safe as you only may save attached entites.
		this.save();
		new GateOpenChangeEvent(this).run();
	}
	
	// FIELD: name
	protected String name;
	public String getName() { return this.name; }
	public void setName(String val) { this.name = val; }
	
	// FIELD: desc
	protected String desc;
	public String getDesc() { return this.desc; }
	public void setDesc(String val) { this.desc = val; }
	
	// FIELD: matopen
	protected Material matopen;
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
	protected Material matclosed;
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
	protected LocWrap exit;
	public LocWrap getExit() { return this.exit; }
	public void setExit(LocWrap val) { this.exit = val; }
	public String getExitDesc()
	{
		String ret = "<v>*NONE*<i>";
		if (this.exit != null)
		{
			ret = "<v>"+this.exit.getVeryShortDesc()+"<i>";
		}
		return ret;
	}
	
	// -------------------------------------------- //
	// FIELD TARGET
	// -------------------------------------------- //
	
	// FIELD: targetFixedLoc - used when the gate is targeting a fixed location
	protected LocWrap targetFixedLoc;
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
	protected String targetGateId;
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
	
	protected Set<WorldCoord3> content;
	public Set<WorldCoord3> getContent() { return Collections.unmodifiableSet(this.content); }
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
		List<WorldCoord3> contentCopy = new ArrayList<WorldCoord3>(this.content);
		for (WorldCoord3 coord : contentCopy)
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
	
	protected Set<WorldCoord3> frame;
	public Set<WorldCoord3> getFrame() { return Collections.unmodifiableSet(this.frame); }
	
	public void addFrame(WorldCoord3 coord)
	{
		// Easy if detached
		if (this.detached())
		{
			// Just add - nothing else
			this.frame.add(coord);
			this.powerCheck(coord);
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
		this.powerCheck(coord);
		
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
			this.powerRemove(coord);
			return;
		}
		
		// Delete
		this.frame.remove(coord);
		this.powerRemove(coord);
		
		// Index
		Gates.i.frameToGate.remove(coord);
	}
	
	public void clearFrame()
	{
		List<WorldCoord3> frameCopy = new ArrayList<WorldCoord3>(this.frame);
		for (WorldCoord3 coord : frameCopy)
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
	// REDSTONE POWER
	// -------------------------------------------- //
	
	protected Set<WorldCoord3> powercoords;
	public Set<WorldCoord3> getPower() { return Collections.unmodifiableSet(this.powercoords); }
	public boolean powerHas() { return this.powercoords.size() > 0; }
	public void powerAdd(WorldCoord3 coord)
	{
		boolean before = this.powerHas();
		if (this.powercoords.add(coord))
		{
			this.powerEventCheck(before);
		}
	}
	public void powerAdd(Collection<WorldCoord3> coords)
	{
		boolean before = this.powerHas();
		if (this.powercoords.addAll(coords))
		{
			this.powerEventCheck(before);
		}
	}
	public void powerRemove(WorldCoord3 coord)
	{
		boolean before = this.powerHas();
		if (this.powercoords.remove(coord))
		{
			this.powerEventCheck(before);
		}
	}
	public void powerRemove(Collection<WorldCoord3> coords)
	{
		boolean before = this.powerHas();
		if (this.powercoords.removeAll(coords))
		{
			this.powerEventCheck(before);
		}
	}
	public void powerSet(Collection<WorldCoord3> coords)
	{
		boolean before = this.powerHas();
		this.powercoords.clear();
		this.powercoords.addAll(coords);
		this.powerEventCheck(before);
	}
	public void powerCheck(WorldCoord3 coord)
	{
		// It is understood from before that this coord is part of the gate frame
		Block block = coord.getBlock();
		if (block == null) return;
		if (block.isBlockIndirectlyPowered())
		{
			this.powerAdd(coord);
		}
		else
		{
			this.powerRemove(coord);
		}
	}
	protected void powerEventCheck(boolean before)
	{
		boolean after = this.powerHas();
		if (before == after) return;
		
		// Call the event
		new GatePowerChangeEvent(this, after).run();
	}
	
	// -------------------------------------------- //
	// TRIGGER ACTIONS
	// -------------------------------------------- //
	
	// So to clarify: an "ActionIdArg" is an "entry" containing "actionId" and "arg" (the custom argument/data for the action)
	// So to clarify: an "ActionArg"   is an "entry" containing "action"   and "arg" (the custom argument/data for the action)
	
	protected Map<String, List<List<String>>> trigger2ActionIdArgs;
	protected void ensureTriggerListExists(Trigger trigger)
	{
		if (this.trigger2ActionIdArgs.containsKey(trigger.getId())) return;
		this.trigger2ActionIdArgs.put(trigger.getId(), new ArrayList<List<String>>());
	}
	public List<Entry<Action, String>> getActionArgs(Trigger trigger)
	{
		this.ensureTriggerListExists(trigger);
		List<List<String>> actionIdArgs = this.trigger2ActionIdArgs.get(trigger.getId());
		List<Entry<Action, String>> ret = new ArrayList<Entry<Action, String>>(actionIdArgs.size());
		for (List<String> actionIdArg : actionIdArgs)
		{
			String actionId = actionIdArg.get(0);
			String arg = actionIdArg.get(1);
			Action action = Gates.i.getActionId(actionId);
			if (action == null) continue;
			Entry<Action, String> actionArg = new SimpleEntry<Action, String>(action, arg);
			ret.add(actionArg);
		}
		return ret;
	}
	public void addAction(Trigger trigger, Action action, String arg)
	{
		this.ensureTriggerListExists(trigger);
		this.trigger2ActionIdArgs.get(trigger.getId()).add(Arrays.asList(action.getId(), arg));
	}
	public boolean delAction(Trigger trigger, int index)
	{
		this.ensureTriggerListExists(trigger);
		List<List<String>> actionIdArgs = this.trigger2ActionIdArgs.get(trigger.getId());
		if (index < 0 || index >= actionIdArgs.size())
		{
			return false;
		}
		actionIdArgs.remove(index);
		return true;
	}
	public int delActions(Trigger trigger, Action action)
	{
		int ret = 0;
		this.ensureTriggerListExists(trigger);
		Iterator<List<String>> iter = this.trigger2ActionIdArgs.get(trigger.getId()).iterator();
		while (iter.hasNext())
		{
			List<String> actionIdArg = iter.next();
			String actionId = actionIdArg.get(0);
			if ( ! actionId.equals(action.getId())) continue;
			iter.remove();
			ret += 1;
		}
		return ret;
	}
	public int delActions(Trigger trigger)
	{
		if ( ! this.trigger2ActionIdArgs.containsKey(trigger.getId())) return 0;
		int ret = this.trigger2ActionIdArgs.get(trigger.getId()).size();
		this.trigger2ActionIdArgs.remove(trigger.getId());
		return ret;
	}
	public void delActions()
	{
		this.trigger2ActionIdArgs.clear();
	}
	public void trigger(Trigger trigger, Entity entity, Cancellable cancellable)
	{
		List<Entry<Action, String>> actionArgs = this.getActionArgs(trigger);
		for (Entry<Action, String> actionArg : actionArgs)
		{
			Action action = actionArg.getKey();
			String arg = actionArg.getValue();
			action.perform(arg, this, entity, cancellable);
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
		this.powercoords = new LinkedHashSet<WorldCoord3>();
		this.trigger2ActionIdArgs = new HashMap<String, List<List<String>>>();
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
	
	public World calcGateWorld()
	{
		Location center = this.calcGateCenter();
		if (center == null) return null;
		return center.getWorld();
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
		// Is there even a target?
		if (this.getTarget() == null) return;
				
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
		TeleportUtil.safeTeleport(user, to);
		
		// Call the after teleport event a bit later
		new GateAfterTeleportEvent(this, user, from, to).run(1);
	}
	
	// -------------------------------------------- //
	// VISUAL
	// -------------------------------------------- //
	
	public void visualizeFor(Player player)
	{
		VisualizeUtil.addCoords(player, this.getFrame(), Conf.visFrame);
		VisualizeUtil.addCoords(player, this.getContent(), Conf.visContent);
		VisualizeUtil.addCoords(player, this.getPower(), Conf.visPower);
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
	
	public void fillContent()
	{
		if (this.isOpen())
		{
			fillContent(this.getMatopen());
		}
		else
		{
			fillContent(this.getMatclosed());
		}
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