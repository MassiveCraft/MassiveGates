package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivecore.event.EventMassiveCore;
import com.massivecraft.massivegates.entity.Gate;

public abstract class EventMassiveGatesSingle extends EventMassiveCore
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected Gate gate;
	public Gate getGate() { return this.gate; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	protected EventMassiveGatesSingle(Gate gate)
	{
		this.gate = gate;
	}
	
}
