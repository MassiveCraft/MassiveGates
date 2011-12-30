package com.massivecraft.massivegates.privatelistener.alterabs;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerListener;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.massivegates.event.GateAlterEvent;
import com.massivecraft.massivegates.event.GateAlterType;

public abstract class GateAlterMonitorPlayerListener extends PlayerListener
{
	// SHARED COORD
	protected WorldCoord3 coord;
	
	P p;
	public GateAlterMonitorPlayerListener(P p)
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
	public void onPlayerBucketFill(PlayerBucketFillEvent event)
	{
		this.standardHandlerSingle(event, event.getBlockClicked(), GateAlterType.BUCKET_FILL, event.getPlayer());
	}
	
	@Override
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event)
	{
		this.standardHandlerSingle(event, event.getBlockClicked().getRelative(event.getBlockFace()), GateAlterType.BUCKET_EMPTY, event.getPlayer());
	}
}
