package com.massivecraft.massivegates.privatelistener.alterabs;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
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
import com.massivecraft.massivegates.event.GateAlterEvent;
import com.massivecraft.massivegates.event.GateAlterType;

/**
 * These event listeners should only be registered if the action in question is forbidden in
 * the gate part (content/frame) the subclass cares about.
 */

public abstract class GateAlterMonitorBlockListener extends BlockListener
{
	// SHARED COORD
	protected WorldCoord3 coord;
	
	P p;
	public GateAlterMonitorBlockListener(P p)
	{
		this.p = p;
		this.coord = new WorldCoord3();
	}
	
	// Get content or frame
	public abstract Gate getGateAt(Block block);
	
	public void standardHandlerSingle(Cancellable event, Block block, GateAlterType alterType, Player player)
	{
		if (event.isCancelled()) return;
		Gate gate = this.getGateAt(block);
		if (gate == null) return;
		GateAlterEvent alterEvent = new GateAlterEvent(gate, alterType, player);
		Bukkit.getPluginManager().callEvent(alterEvent);
	}
	
	@Override
	public void onBlockPlace(BlockPlaceEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.PLACE, event.getPlayer());
	}
	
	@Override
	public void onBlockBreak(BlockBreakEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.BREAK, event.getPlayer());
	}
	
	@Override
	public void onBlockIgnite(BlockIgniteEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.IGNITE, event.getPlayer());
	}
	
	@Override
	public void onBlockPhysics(BlockPhysicsEvent event)
	{
		//this.standardHandlerSingle(event, event.getBlock(), GateAlterType.PHYSICS, null);
		// WILL TRIGGER FAR TO OFTEN ???
	}
	
	@Override
	public void onBlockFromTo(BlockFromToEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.FLOW, null);
		this.standardHandlerSingle(event, event.getToBlock(), GateAlterType.FLOW, null);
	}
	
	@Override
	public void onBlockForm(BlockFormEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.FORM, null);
	}
	
	@Override
	public void onBlockFade(BlockFadeEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.FADE, null);
	}
	
	@Override
	public void onBlockBurn(BlockBurnEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.BURN, null);
	}
	
	@Override
	public void onBlockSpread(BlockSpreadEvent event)
	{
		this.standardHandlerSingle(event, event.getSource(), GateAlterType.SPREAD, null);
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.SPREAD, null);
	}
	
	@Override
	public void onBlockPistonExtend(BlockPistonExtendEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.PISTON_EXTEND, null);
		for (Block block : event.getBlocks())
		{
			this.standardHandlerSingle(event, block, GateAlterType.PISTON_EXTEND, null);
		}
	}
	
	@Override
	public void onBlockPistonRetract(BlockPistonRetractEvent event)
	{
		this.standardHandlerSingle(event, event.getBlock(), GateAlterType.PISTON_EXTEND, null);
		this.standardHandlerSingle(event, event.getRetractLocation().getBlock(), GateAlterType.PISTON_EXTEND, null);
	}
}
