package com.massivecraft.massivegates.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.abs.EventMassiveGatesDual;

public class EventMassiveGatesPlayerWalk extends EventMassiveGatesDual
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
	
	// FIELD: walkType
	protected GatePlayerWalkType walkType;
	public GatePlayerWalkType getWalkType() { return this.walkType; }
	
	// FIELD: player
	private Player player;
	public Player getPlayer() { return this.player; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public EventMassiveGatesPlayerWalk(Player player, Gate gateFrom, Gate gateTo, GatePlayerWalkType walkType)
	{
		this.player = player;
		this.gateFrom = gateFrom;
		this.gateTo = gateTo;
		this.walkType = walkType;
	}
	
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	public enum GatePlayerWalkType
	{
		INTO,
		WITHIN,
		OUT,
		
		// END OF LIST
		;
	}
	
}
