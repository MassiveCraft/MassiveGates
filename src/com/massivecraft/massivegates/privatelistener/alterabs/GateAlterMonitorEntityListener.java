package com.massivecraft.massivegates.privatelistener.alterabs;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.massivegates.event.GateAlterEvent;
import com.massivecraft.massivegates.event.GateAlterType;

/**
 * These event listeners should only be registered if the action in question is forbidden in
 * the gate part (content/frame) the subclass cares about.
 */

public abstract class GateAlterMonitorEntityListener extends EntityListener
{
	// SHARED COORD
	protected WorldCoord3 coord;
	
	P p;
	public GateAlterMonitorEntityListener(P p)
	{
		this.p = p;
		this.coord = new WorldCoord3();
	}
	
	// Get content or frame
	public abstract Gate getGateAt(Block block);
	
	@Override
	public void onEntityExplode(EntityExplodeEvent event)
	{
		if (event.isCancelled()) return;
		
		Set<Gate> alteredGates = new HashSet<Gate>();
		
		for (Block block : event.blockList())
		{
			Gate gate = this.getGateAt(block);
			if (gate == null) continue;
			alteredGates.add(gate);
		}
		
		for (Gate alteredGate : alteredGates)
		{
			GateAlterEvent alterEvent = new GateAlterEvent(alteredGate, GateAlterType.EXPLODE, null);
			Bukkit.getPluginManager().callEvent(alterEvent);
		}		
	}
}
