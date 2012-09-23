package com.massivecraft.massivegates.event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Target;
import com.massivecraft.massivegates.event.abs.GateTeleportEvent;

public class GateAfterTeleportEvent extends GateTeleportEvent
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	private static final HandlerList handlers = new HandlerList();
	public HandlerList getHandlers() { return handlers; }
	public static HandlerList getHandlerList() { return handlers; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public GateAfterTeleportEvent(Gate gate, Entity user, Location from, Target to)
	{
		super(gate, user, from, to);
	}
}
