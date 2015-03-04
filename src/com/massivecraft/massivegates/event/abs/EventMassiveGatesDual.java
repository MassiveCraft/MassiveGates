package com.massivecraft.massivegates.event.abs;

import com.massivecraft.massivecore.event.EventMassiveCore;
import com.massivecraft.massivegates.entity.Gate;

public abstract class EventMassiveGatesDual extends EventMassiveCore
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	protected Gate gateFrom;
	public Gate getGateFrom() { return this.gateFrom; }
	
	protected Gate gateTo;
	public Gate getGateTo() { return this.gateTo; }
	
}
