package com.massivecraft.massivegates.event.abs;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;

public abstract class GateTeleportEvent extends SingleGateEvent
{	
	// FIELD: user
	private Entity user;
	public Entity getUser() { return this.user; }
	
	// FIELD: from
	private Location from;
	public Location getFrom() { return this.from; }
	
	// FIELD: to
	private Location to;
	public Location getTo() { return this.to; }
	
	public GateTeleportEvent(Gate gate, Entity user, Location from, Location to)
	{
		super(gate);
		this.user = user;
		this.from = from;
		this.to = to;
	}
}
