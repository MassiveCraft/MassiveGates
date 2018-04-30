package com.massivecraft.massivegates.entity;

import com.massivecraft.massivecore.Named;
import com.massivecraft.massivecore.collections.MassiveSet;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.ps.PSFormatDesc;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Const;
import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.Target;
import com.massivecraft.massivegates.event.EventMassiveGatesPlayerWalk;
import com.massivecraft.massivegates.event.EventMassiveGatesPlayerWalk.GatePlayerWalkType;
import com.massivecraft.massivegates.event.EventMassiveGatesUse;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.Trigger;
import com.massivecraft.massivegates.ta.TriggerAtp;
import com.massivecraft.massivegates.ta.TriggerBtp;
import com.massivecraft.massivegates.ta.TriggerClose;
import com.massivecraft.massivegates.ta.TriggerOpen;
import com.massivecraft.massivegates.ta.TriggerPowerOff;
import com.massivecraft.massivegates.ta.TriggerPowerOn;
import com.massivecraft.massivegates.util.VisualizeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

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

public class Gate extends Entity<Gate> implements Named
{
	// -------------------------------------------- //
	// OVERRIDE: ENTITY
	// -------------------------------------------- //
	
	@Override
	public Gate load(Gate that)
	{
		this.open = that.open;
		this.name = that.name;
		this.desc = that.desc;
		this.matopen = that.matopen;
		this.dataopen = that.dataopen;
		this.matclosed = that.matclosed;
		this.dataclosed = that.dataclosed;
		this.exit = that.exit;
		this.target = that.target;
		this.content = that.content;
		this.frame = that.frame;
		this.powercoords = that.powercoords;
		this.trigger2ActionIdArgs = that.trigger2ActionIdArgs;
		return this;
	}
	
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
			
	public Gate()
	{
		this.open = false;
		this.matclosed = Material.AIR;
		this.dataclosed = 0;
		this.matopen = Material.PORTAL;
		this.dataopen = 0;
		this.content = new HashSet<>();
		this.frame = new HashSet<>();
		this.powercoords = new MassiveSet<>();
		this.trigger2ActionIdArgs = new HashMap<>();
	}
	
	// -------------------------------------------- //
	// FIELDS: RAW
	// -------------------------------------------- //
	// In this section of the source code we place the field declarations only.
	// Each field has it's own section further down since just the getter and setter logic takes up quite some place.

	// The boolean whether or not a gate is open
	protected boolean open;
	
	// The name of the gate
	protected String name;
	
	// The description of the gate
	protected String desc;
	
	// The material and data it can be opened with
	protected Material matopen;
	protected Byte dataopen;
	
	// The material and data it can be closed with
	protected Material matclosed;
	protected Byte dataclosed;
	
	// The physical state (location, pitch, yaw, etc) of its exit
	protected PS exit;
	
	// The target it is pointing at
	protected Target target = new Target();
	
	// Set of the blocks which are considered content of this gate
	protected Set<PS> content;
	
	// Set of the blocks which are considered frame of this gate
	protected Set<PS> frame;
	
	// Set of the power coordinates locations
	protected Set<PS> powercoords;
	
	// The trigger Action Map defines what happens when entering the gate
	protected Map<String, List<List<String>>> trigger2ActionIdArgs;
	
	// -------------------------------------------- //
	// FIELD: open
	// -------------------------------------------- //

	// Raw
	public void setOpen(boolean open)
	{
		this.changed(this.open, open);
		
		this.open = open;
		
		this.fillContent();
		
		// Trigger!
		if (open)
		{
			this.trigger(TriggerOpen.get(), null, null);
		}
		else
		{
			this.trigger(TriggerClose.get(), null, null);
		}
		
		// If someone is standing in a gate while it opens that should be considered as entering the gate.
		if (this.open)
		{
			PS coord = null;
			for (Player player : MUtil.getOnlinePlayers())
			{
				coord = PS.valueOf(player.getLocation().getBlock());
				if (this != GateColl.get().getGateAtContentCoord(coord)) continue;
				new EventMassiveGatesPlayerWalk(player, null, this, GatePlayerWalkType.INTO).run();
			}
		}
	}
	
	// Finer
	public boolean isOpen() { return this.open; }
	
	// -------------------------------------------- //
	// FIELD: name
	// -------------------------------------------- //
	
	// Raw
	@Override
	public String getName()
	{
		return this.name;
	}
	public void setName(String val)
	{
		this.changed(this.name, val);
		this.name = val;
	}
	
	// Finer
	public String getIdNameStringShort()
	{
		String ret = "<h>"+this.getId();
		if (this.getName() != null)
		{
			ret += " <aqua>"+this.getName();
		}
		return Txt.parse(ret);
	}
	
	public String getIdNameStringLong()
	{
		String ret = "<h>id <aqua>"+this.getId();
		if (this.getName() != null)
		{
			ret += " <h>name <aqua>"+this.getName()+"<i>";
		}
		return Txt.parse(ret);
	}
	
	// -------------------------------------------- //
	// FIELD: desc
	// -------------------------------------------- //
	
	// Raw
	public String getDesc()
	{
		return this.desc;
	}
	public void setDesc(String val)
	{
		this.changed(this.desc, val);
		this.desc = val;
	}
	
	// -------------------------------------------- //
	// FIELD: matopen
	// -------------------------------------------- //

	// Raw
	public Material getMatopen()
	{
		return this.matopen;
	}
	
	public void setMatopen(Material mat, Byte data)
	{
		this.changed(this.matopen, mat);
		this.changed(this.dataopen, data);
		
		this.matopen = mat;
		this.dataopen = data;
		if (this.open)
		{
			this.fillContent(this.matopen, this.dataopen);
		}
	}
	
	public Byte getDataopen()
	{
		return this.dataopen;
	}

	// -------------------------------------------- //
	// FIELD: matclosed
	// -------------------------------------------- //

	public Material getMatclosed()
	{
		return this.matclosed;
	}
	
	public void setMatclosed(Material mat, Byte data)
	{
		this.changed(this.matclosed, mat);
		this.changed(this.dataclosed, data);
		
		this.matclosed = mat;
		this.dataclosed = data;
		if (this.open == false)
		{
			this.fillContent(this.matclosed, this.dataclosed);
		}
	}
	
	public Byte getDataclosed()
	{
		return this.dataclosed;
	}
	
	// -------------------------------------------- //
	// FIELD: exit
	// -------------------------------------------- //
	
	//Raw
	public PS getExit()
	{
		return this.exit;
	}
	public void setExit(PS val)
	{
		this.changed(this.exit, val);
		this.exit = val;
	}
	
	// Finer
	public String getExitDesc()
	{
		String ret = "<v>*NONE*<i>";
		if (this.exit != null)
		{
			ret = "<v>"+this.exit.toString(PSFormatDesc.get())+"<i>";
		}
		return Txt.parse(ret);
	}
	
	// -------------------------------------------- //
	// FIELD: target
	// -------------------------------------------- //
	
	// Raw
	public Target getTarget() { return this.target; }
	
	// -------------------------------------------- //
	// FIELD: content
	// -------------------------------------------- //
	
	// Raw
	public Set<PS> getContent() { return Collections.unmodifiableSet(this.content); }
	
	public void setContent(Collection<PS> coords)
	{
		this.clearContent();
		this.addContent(coords);
		this.changed();
	}
	
	// Finer
	public void addContent(PS coord)
	{
		this.changed();
		
		// Easy if detached
		if (this.detached())
		{
			// Just add - nothing else
			this.content.add(coord);
			return;
		}
		
		// Overwrite
		Gate currentGateThere = GateColl.get().getGateAtCoord(coord);
		if (currentGateThere != null)
		{
			currentGateThere.delContent(coord);
			currentGateThere.delFrame(coord);
		}
		
		// Add
		this.content.add(coord);
		
		// Index
		GateColl.get().contentToGate.put(coord, this);
		
		// Ensure material
		if (this.isOpen())
		{
			Block block = null;
			try
			{
				block = coord.asBukkitBlock();
			}
			catch (Exception e)
			{
				
			}
			if (block == null) return;
			block.setType(this.matopen);
		}
	}

	public void addContent(Collection<PS> coords)
	{
		for (PS coord : coords)
		{
			this.addContent(coord);
		}
	}
	
	public void addContentBlocks(Collection<Block> blocks)
	{
		List<PS> coords = new ArrayList<>();
		for (Block block : blocks)
		{
			coords.add(PS.valueOf(block));
		}
		this.addContent(coords);
	}
	
	public void delContent(PS coord)
	{
		this.changed();
		 
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
		GateColl.get().contentToGate.remove(coord);
		
		// Ensure material
		if (this.open)
		{
			Block block = null;
			try
			{
				block = coord.asBukkitBlock();
			}
			catch (Exception e)
			{
				
			}
			if (block == null) return;
			block.setType(Material.AIR);
		}
	}
	
	public void clearContent()
	{
		List<PS> contentCopy = new ArrayList<>(this.content);
		for (PS coord : contentCopy)
		{
			this.delContent(coord);
		}
	}
	
	// -------------------------------------------- //
	// FIELD: frame
	// -------------------------------------------- //
	
	// Raw
	public Set<PS> getFrame() { return Collections.unmodifiableSet(this.frame); }
	
	public void setFrame(Collection<PS> coords)
	{
		this.clearFrame();
		this.addFrame(coords);
	}
	
	// Finer
	public void addFrame(Collection<PS> coords)
	{
		for (PS coord : coords)
		{
			this.addFrame(coord);
		}
	}
	
	public void addFrameBlocks(Collection<Block> blocks)
	{
		List<PS> coords = new ArrayList<>();
		for (Block block : blocks)
		{
			coords.add(PS.valueOf(block));
		}
		this.addFrame(coords);
	}
	

	public void addFrame(PS coord)
	{
		this.changed();
		 
		// Easy if detached
		if (this.detached())
		{
			// Just add - nothing else
			this.frame.add(coord);
			this.powerCheck(coord);
			return;
		}
		
		// Overwrite
		Gate currentGateThere = GateColl.get().getGateAtCoord(coord);
		if (currentGateThere != null)
		{
			currentGateThere.delContent(coord);
			currentGateThere.delFrame(coord);
		}
		
		// Add
		this.frame.add(coord);
		this.powerCheck(coord);
		
		// Index
		GateColl.get().frameToGate.put(coord, this);
	}
	
	public void delFrame(PS coord)
	{
		this.changed();
		
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
		GateColl.get().frameToGate.remove(coord);
	}
	
	public void clearFrame()
	{
		List<PS> frameCopy = new ArrayList<>(this.frame);
		for (PS coord : frameCopy)
		{
			this.delFrame(coord);
		}
	}
	
	// -------------------------------------------- //
	// Field: powercoords
	// -------------------------------------------- //
	
	// Raw
	public Set<PS> getPower() { return Collections.unmodifiableSet(this.powercoords); }
	
	public void powerSet(Collection<PS> coords)
	{
		boolean before = this.powerHas();
		this.powercoords.clear();
		this.powercoords.addAll(coords);
		this.powerEventCheck(before);
		this.changed();
	}
	
	// Finer
	public boolean powerHas() { return this.powercoords.size() > 0; }
	
	public void powerAdd(PS coord)
	{
		boolean before = this.powerHas();
		if (this.powercoords.add(coord))
		{
			this.powerEventCheck(before);
		}
		this.changed();
	}
	
	public void powerAdd(Collection<PS> coords)
	{
		boolean before = this.powerHas();
		if (this.powercoords.addAll(coords))
		{
			this.powerEventCheck(before);
		}
		this.changed();
	}
	
	public void powerRemove(PS coord)
	{
		boolean before = this.powerHas();
		if (this.powercoords.remove(coord))
		{
			this.powerEventCheck(before);
		}
		this.changed();
	}
	
	public void powerRemove(Collection<PS> coords)
	{
		boolean before = this.powerHas();
		if (this.powercoords.removeAll(coords))
		{
			this.powerEventCheck(before);
		}
		this.changed();
	}

	public void powerCheck(PS coord)
	{
		// It is understood from before that this coord is part of the gate frame
		Block block = null;
		try
		{
			block = coord.asBukkitBlock();
		}
		catch (Exception e)
		{
			return;
		}
		
		if (block.isBlockIndirectlyPowered())
		{
			this.powerAdd(coord);
		}
		else
		{
			this.powerRemove(coord);
		}
		
		this.changed();
	}
	
	protected void powerEventCheck(boolean before)
	{
		boolean after = this.powerHas();
		if (before == after) return;
		
		if (after)
		{
			this.trigger(TriggerPowerOn.get(), null, null);
		}
		else
		{
			this.trigger(TriggerPowerOff.get(), null, null);
		}
	}
	
	// -------------------------------------------- //
	// Field: trigger2ActionIdArgs
	// -------------------------------------------- //
	
	// So to clarify: an "ActionIdArg" is an "entry" containing "actionId" and "arg" (the custom argument/data for the action)
	// So to clarify: an "ActionArg"   is an "entry" containing "action"   and "arg" (the custom argument/data for the action)
	
	// Finer
	protected void ensureTriggerListExists(Trigger trigger)
	{
		if (this.trigger2ActionIdArgs.containsKey(trigger.getId())) return;
		this.trigger2ActionIdArgs.put(trigger.getId(), new ArrayList<List<String>>());
		this.changed();
	}
	
	public List<Entry<Action, String>> getActionArgs(Trigger trigger)
	{
		this.ensureTriggerListExists(trigger);
		List<List<String>> actionIdArgs = this.trigger2ActionIdArgs.get(trigger.getId());
		List<Entry<Action, String>> ret = new ArrayList<>(actionIdArgs.size());
		for (List<String> actionIdArg : actionIdArgs)
		{
			String actionId = actionIdArg.get(0);
			String arg = actionIdArg.get(1);
			Action action = GateColl.get().getActionId(actionId);
			if (action == null) continue;
			Entry<Action, String> actionArg = new SimpleEntry<>(action, arg);
			ret.add(actionArg);
		}
		return ret;
	}
	
	public void addAction(Trigger trigger, Action action, String arg)
	{
		this.ensureTriggerListExists(trigger);
		this.trigger2ActionIdArgs.get(trigger.getId()).add(Arrays.asList(action.getId(), arg));
		this.changed();
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
		this.changed();
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
			this.changed();
			ret += 1;
		}
		return ret;
	}
	public int delActions(Trigger trigger)
	{
		if ( ! this.trigger2ActionIdArgs.containsKey(trigger.getId())) return 0;
		int ret = this.trigger2ActionIdArgs.get(trigger.getId()).size();
		this.trigger2ActionIdArgs.remove(trigger.getId());
		this.changed();
		return ret;
	}
	public void delActions()
	{
		if (!this.trigger2ActionIdArgs.isEmpty()) this.changed();
		this.trigger2ActionIdArgs.clear();
	}
	public void trigger(Trigger trigger, org.bukkit.entity.Entity entity, Cancellable cancellable)
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
	// GATE UTILITIES
	// -------------------------------------------- //
	
	public Location calcGateCenter()
	{
		// TODO: IMPROVE
		if ( ! this.getContent().isEmpty())
		{
			try
			{
				return this.getContent().iterator().next().asBukkitLocation(true);
			}
			catch (Exception e)
			{
				
			}
		}
		
		if ( ! this.getFrame().isEmpty())
		{
			try
			{
				return this.getFrame().iterator().next().asBukkitLocation(true);
			}
			catch (Exception e)
			{
				
			}
		}
		
		return null;
	}
	
	public World calcGateWorld()
	{
		Location center = this.calcGateCenter();
		if (center == null) return null;
		return center.getWorld();
	}
	
	public void visualizeFor(Player player)
	{
		VisualizeUtil.addCoords(player, this.getFrame(), Const.visFrame);
		VisualizeUtil.addCoords(player, this.getContent(), Const.visContent);
		VisualizeUtil.addCoords(player, this.getPower(), Const.visPower);
	}

	// -------------------------------------------- //
	// ACTIONS AND EVENTS
	// -------------------------------------------- //
	
	public void use(final org.bukkit.entity.Entity user)
	{
		// Is there even a target?
		if ( ! this.getTarget().exists()) return;
				
		// Call the use event, perform all actions with the trigger "use" on the user
		EventMassiveGatesUse useEvent = new EventMassiveGatesUse(this, user);
		useEvent.run();
		if (useEvent.isCancelled()) return;
		
		// Teleport is only for players
		if ( ! (user instanceof Player)) return;
		final Player player = (Player) user;
		
		// Call the before event
		this.trigger(TriggerBtp.get(), player, null);
		
		// Do safe teleport
		final Gate gate = this;
		Bukkit.getScheduler().scheduleSyncDelayedTask(MassiveGates.get(), new Runnable()
		{
			public void run()
			{				
				if (target.teleport(player))
				{
					gate.trigger(TriggerAtp.get(), player, null);
				}
			}
		});
	}

	// -------------------------------------------- //
	// FILL: frame|content
	// -------------------------------------------- //
	
	@SuppressWarnings("deprecation")
	public static void fillCoords(Collection<PS> coords, Material material, byte data)
	{
		for (PS coord : coords)
		{
			Block block = null;
			try
			{
				block = coord.asBukkitBlock();
			}
			catch (Exception e)
			{
				continue;
			}
			
			// Do orientation check
			if (material == Material.PORTAL)
			{
				try
				{
					Block blockSouth = block.getRelative(BlockFace.SOUTH);
					PS psSouth = PS.valueOf(blockSouth);
					
					Block blockNorth = block.getRelative(BlockFace.NORTH);
					PS psNorth = PS.valueOf(blockNorth);
					
					if (coords.contains(psNorth) || coords.contains(psSouth))
					{
						data = 2;
					}
					else
					{
						data = 0;
					}
				}
				catch (Exception e)
				{
					
				}
			}
			
			block.setType(material);
			block.setData(data);
		}
	}
	
	public static void fillCoords(Collection<PS> coords, Material material)
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
			fillContent(this.getMatopen(), this.getDataopen());
		}
		else
		{
			fillContent(this.getMatclosed(), this.getDataclosed());
		}
	}
	
	public void recalculatePortalOrientation()
	{
		if (this.getMatopen() != Material.PORTAL) return;
		
		this.fillContent();
	}
	
}
