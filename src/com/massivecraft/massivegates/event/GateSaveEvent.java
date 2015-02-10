package com.massivecraft.massivegates.event;

import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

public class GateSaveEvent extends SingleGateEvent
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
	
	public GateSaveEvent(Gate gate)
	{
		super(gate);
	}
	
}
