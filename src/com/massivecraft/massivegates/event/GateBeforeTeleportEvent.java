package com.massivecraft.massivegates.event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.GateTeleportEvent;

public class GateBeforeTeleportEvent extends GateTeleportEvent
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
	
	public GateBeforeTeleportEvent(Gate gate, Entity user, Location from, Location to)
	{
		super(gate, user, from, to);
	}
}
