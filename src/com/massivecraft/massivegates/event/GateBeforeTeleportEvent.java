package com.massivecraft.massivegates.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.Target;
import com.massivecraft.massivegates.entity.Gate;
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
	// CONSTRUCT
	// -------------------------------------------- //
	
	public GateBeforeTeleportEvent(Gate gate, Player player, Location from, Target to)
	{
		super(gate, player, from, to);
	}
	
}
