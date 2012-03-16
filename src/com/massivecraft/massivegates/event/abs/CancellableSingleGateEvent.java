package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivegates.Gate;

public abstract class CancellableSingleGateEvent extends CancellableGateEvent
{
	// FIELD: gate
	protected Gate gate;
	public Gate getGate() { return this.gate; }
	
	protected CancellableSingleGateEvent(Gate gate)
	{
		this.gate = gate;
	}
}
