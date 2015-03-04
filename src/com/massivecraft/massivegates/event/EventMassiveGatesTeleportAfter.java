package com.massivecraft.massivegates.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.Target;
import com.massivecraft.massivegates.entity.Gate;

public class EventMassiveGatesTeleportAfter extends EventMassiveGatesTeleportAbstract
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
	
	public EventMassiveGatesTeleportAfter(Gate gate, Player player, Location from, Target to)
	{
		super(gate, player, from, to);
	}
	
}
