package com.massivecraft.massivegates.event;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

@SuppressWarnings("serial")
public class GateDetachEvent extends SingleGateEvent
{
	public GateDetachEvent(Gate gate)
	{
		super("GateDetachEvent", gate);
	}
}
