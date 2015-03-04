package com.massivecraft.massivegates.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.massivecraft.massivegates.Target;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.abs.EventMassiveGatesSingle;

public abstract class EventMassiveGatesTeleportAbstract extends EventMassiveGatesSingle
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
	
	public EventMassiveGatesTeleportAbstract(Gate gate, Player player, Location from, Target to)
	{
		super(gate);
		this.player = player;
		this.from = from;
		this.to = to;
	}
	
}
