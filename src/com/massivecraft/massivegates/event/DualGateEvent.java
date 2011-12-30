package com.massivecraft.massivegates.event;

import org.bukkit.event.Event;

import com.massivecraft.massivegates.Gate;

@SuppressWarnings("serial")
public abstract class DualGateEvent extends Event
{
	// FIELD: gateFrom
	protected Gate gateFrom;
	public Gate getGateFrom() { return this.gateFrom; }
	
	// FIELD: gateTo
	protected Gate gateTo;
	public Gate getGateTo() { return this.gateTo; }
	
	protected DualGateEvent(String name)
	{
		super(name);
	}
	
}
