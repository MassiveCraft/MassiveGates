package com.massivecraft.massivegates.event.abs;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Target;

public abstract class GateTeleportEvent extends SingleGateEvent
{	
	// FIELD: user
	private Entity user;
	public Entity getUser() { return this.user; }
	
	// FIELD: from
	private Location from;
	public Location getFrom() { return this.from; }
	
	// FIELD: to
	private Target to;
	public Target getTo() { return this.to; }
	
	public GateTeleportEvent(Gate gate, Entity user, Location from, Target to)
	{
		super(gate);
		this.user = user;
		this.from = from;
		this.to = to;
	}
}
