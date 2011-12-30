package com.massivecraft.massivegates.privatelistener.alterabs;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerListener;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;

/**
 * These event listeners should only be registered if the action in question is forbidden in
 * the gate part (content/frame) the subclass cares about.
 */

public abstract class GateAlterCancelPlayerListener extends PlayerListener
{
	// SHARED COORD
	protected WorldCoord3 coord;
	
	P p;
	public GateAlterCancelPlayerListener(P p)
	{
		this.p = p;
		this.coord = new WorldCoord3();
	}
	
	// Get content or frame
	public abstract Gate getGateAt(Block block);
	
	public void standardHandlerSingle(Block block, Cancellable event)
	{
		if (event.isCancelled()) return;
		Gate gate = this.getGateAt(block);
		if (gate == null) return;
		event.setCancelled(true);
	}
	
	@Override
	public void onPlayerBucketFill(PlayerBucketFillEvent event)
	{
		this.standardHandlerSingle(event.getBlockClicked(), event);
	}
	
	@Override
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event)
	{
		this.standardHandlerSingle(event.getBlockClicked().getRelative(event.getBlockFace()), event);
	}
}
