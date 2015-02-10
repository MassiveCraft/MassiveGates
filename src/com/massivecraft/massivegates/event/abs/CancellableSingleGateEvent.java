package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivegates.entity.Gate;

public abstract class CancellableSingleGateEvent extends CancellableGateEvent
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected Gate gate;
	public Gate getGate() { return this.gate; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	protected CancellableSingleGateEvent(Gate gate)
	{
		this.gate = gate;
	}
	
}
