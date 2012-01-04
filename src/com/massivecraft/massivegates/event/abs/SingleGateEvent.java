package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivegates.Gate;

@SuppressWarnings("serial")
public abstract class SingleGateEvent extends GateEvent
{
	// FIELD: gate
	protected Gate gate;
	public Gate getGate() { return this.gate; }
	
	protected SingleGateEvent(String name, Gate gate)
	{
		super(name);
		this.gate = gate;
	}
	
}
