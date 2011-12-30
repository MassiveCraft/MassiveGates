package com.massivecraft.massivegates.privatelistener.alterabs;

import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;

/**
 * These event listeners should only be registered if the action in question is forbidden in
 * the gate part (content/frame) the subclass cares about.
 */

public abstract class GateAlterCancelEntityListener extends EntityListener
{
	// SHARED COORD
	protected WorldCoord3 coord;
	
	P p;
	public GateAlterCancelEntityListener(P p)
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
		for (Block block : event.blockList())
		{
			if (this.getGateAt(block) != null)
			{
				event.setCancelled(true);
				return;
			}
		}
	}
}
