package com.massivecraft.massivegates.event;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.abs.EventMassiveGatesSingle;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

/**
 * Should be fired when someone uses a gate. Some gates might be used just by
 * walking into them. Other gates might need the user to type a command to initiate transfer.
 */

public class EventMassiveGatesUse extends EventMassiveGatesSingle
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
	
	// FIELD: user
	private Entity user;
	public Entity getUser() { return this.user; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public EventMassiveGatesUse(Gate gate, Entity user)
	{
		super(gate);
		this.user = user;
	}
	
}
