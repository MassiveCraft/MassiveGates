package com.massivecraft.massivegates.event.abs;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.massivegates.Target;
import com.massivecraft.massivegates.entity.Gate;

public abstract class GateTeleportEvent extends SingleGateEvent
{	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	private Player player;
	public Player getPlayer() { return this.player; }
	
	private Location from;
	public Location getFrom() { return this.from; }
	
	private Target to;
	public Target getTo() { return this.to; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public GateTeleportEvent(Gate gate, Player player, Location from, Target to)
	{
		super(gate);
		this.player = player;
		this.from = from;
		this.to = to;
	}
	
}
