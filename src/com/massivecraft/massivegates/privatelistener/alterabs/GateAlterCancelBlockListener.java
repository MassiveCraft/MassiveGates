package com.massivecraft.massivegates.privatelistener.alterabs;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;

/**
 * These event listeners should only be registered if the action in question is forbidden in
 * the gate part (content/frame) the subclass cares about.
 */

public abstract class GateAlterCancelBlockListener extends BlockListener
{
	// SHARED COORD
	protected WorldCoord3 coord;
	
	P p;
	public GateAlterCancelBlockListener(P p)
	{
		this.p = p;
		this.coord = new WorldCoord3();
	}
	
	// Get content or frame
	public abstract Gate getGateAt(Block block);
	
	// The two parameters should be the same event. It's splitted for type safety only.
	public void standardHandlerSingle(Block block, Cancellable event)
	{
		if (event.isCancelled()) return;
		Gate gate = this.getGateAt(block);
		if (gate == null) return;
		event.setCancelled(true);
	}
	
	@Override
	public void onBlockPlace(BlockPlaceEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockBreak(BlockBreakEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockIgnite(BlockIgniteEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockPhysics(BlockPhysicsEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockFromTo(BlockFromToEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
		this.standardHandlerSingle(event.getToBlock(), event);
	}
	
	@Override
	public void onBlockForm(BlockFormEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockFade(BlockFadeEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockBurn(BlockBurnEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockSpread(BlockSpreadEvent event)
	{
		this.standardHandlerSingle(event.getSource(), event);
		this.standardHandlerSingle(event.getBlock(), event);
	}
	
	@Override
	public void onBlockPistonExtend(BlockPistonExtendEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
		if (event.isCancelled()) return;
		for (Block block : event.getBlocks())
		{
			if (this.getGateAt(block) != null)
			{
				event.setCancelled(true);
				return;
			}
		}
	}
	
	@Override
	public void onBlockPistonRetract(BlockPistonRetractEvent event)
	{
		this.standardHandlerSingle(event.getBlock(), event);
		this.standardHandlerSingle(event.getRetractLocation().getBlock(), event);
	}
}
