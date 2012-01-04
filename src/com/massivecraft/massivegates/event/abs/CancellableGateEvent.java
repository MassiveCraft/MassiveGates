package com.massivecraft.massivegates.event.abs;

import org.bukkit.event.Cancellable;

@SuppressWarnings("serial")
public class CancellableGateEvent extends GateEvent implements Cancellable
{
	// FIELD: cancelled
	private boolean cancelled = false;
	@Override public boolean isCancelled() { return this.cancelled; }
	@Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
	
	public CancellableGateEvent(String name)
	{
		super(name);
	}
}
