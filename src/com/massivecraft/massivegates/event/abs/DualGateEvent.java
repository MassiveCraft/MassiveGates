package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivegates.entity.Gate;

public abstract class DualGateEvent extends GateEvent
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected Gate gateFrom;
	public Gate getGateFrom() { return this.gateFrom; }
	
	protected Gate gateTo;
	public Gate getGateTo() { return this.gateTo; }
	
}
