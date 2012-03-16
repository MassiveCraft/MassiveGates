package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivegates.Gate;

public abstract class SingleGateEvent extends GateEvent
{
	// FIELD: gate
	protected Gate gate;
	public Gate getGate() { return this.gate; }
	
	protected SingleGateEvent(Gate gate)
	{
		this.gate = gate;
	}
}
