package com.massivecraft.massivegates.event;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

@SuppressWarnings("serial")
public class GatePowerChangeEvent extends SingleGateEvent
{
	protected boolean power;
	public boolean powerHas() { return this.power; }
	public GatePowerChangeEvent(Gate gate, boolean power)
	{
		super("GatePowerChangeEvent", gate);
		this.power = power;
	}
	
}