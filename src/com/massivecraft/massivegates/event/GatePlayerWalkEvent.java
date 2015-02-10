package com.massivecraft.massivegates.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.abs.DualGateEvent;

public class GatePlayerWalkEvent extends DualGateEvent implements Cancellable
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
	
	// FIELD: cancelled
	private boolean cancelled = false;
	@Override public boolean isCancelled() { return this.cancelled; }
	@Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	// FIELD: walkType
	protected GatePlayerWalkType walkType;
	public GatePlayerWalkType getWalkType() { return this.walkType; }
	
	// FIELD: player
	private Player player;
	public Player getPlayer() { return this.player; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public GatePlayerWalkEvent(Player player, Gate gateFrom, Gate gateTo, GatePlayerWalkType walkType)
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
