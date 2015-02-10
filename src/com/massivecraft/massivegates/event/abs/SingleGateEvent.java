package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivegates.entity.Gate;

public abstract class SingleGateEvent extends GateEvent
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //

	protected Gate gate;
	public Gate getGate() { return this.gate; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	protected SingleGateEvent(Gate gate)
	{
		this.gate = gate;
	}
	
}
