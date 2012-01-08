package com.massivecraft.massivegates.event;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

@SuppressWarnings("serial")
public class GateOpenChangeEvent extends SingleGateEvent
{	
	public GateOpenChangeEvent(Gate gate)
	{
		super("GateOpenChangeEvent", gate);
	}	
}
