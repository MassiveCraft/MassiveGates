package com.massivecraft.massivegates.event;

import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.CancellableSingleGateEvent;

/**
 * Should be fired when someone uses a gate. Some gates might be used just by
 * walking into them. Other gates might need the user to type a command to initiate transfer.
 */

@SuppressWarnings("serial")
public class GateUseEvent extends CancellableSingleGateEvent
{	
	// FIELD: user
	private Entity user;
	public Entity getUser() { return this.user; }
	
	public GateUseEvent(Gate gate, Entity user)
	{
		super("GateUseEvent", gate);
		this.user = user;
	}
}
