package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivegates.Gate;

@SuppressWarnings("serial")
public abstract class CancellableSingleGateEvent extends CancellableGateEvent
{
	// FIELD: gate
	protected Gate gate;
	public Gate getGate() { return this.gate; }
	
	protected CancellableSingleGateEvent(String name, Gate gate)
	{
		super(name);
		this.gate = gate;
	}
	
}
