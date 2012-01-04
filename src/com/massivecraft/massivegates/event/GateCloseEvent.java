package com.massivecraft.massivegates.event;

import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

/**
 * The reasons for gates to close can vary just as much as for opening.
 */

@SuppressWarnings("serial")
public class GateCloseEvent extends SingleGateEvent implements Cancellable
{
	// FIELD: cancelled
	private boolean cancelled = false;
	@Override public boolean isCancelled() { return this.cancelled; }
	@Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	public GateCloseEvent(Gate gate)
	{
		super("GateCloseEvent", gate);
	}
}
