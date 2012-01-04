package com.massivecraft.massivegates.event;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.SingleGateEvent;

@SuppressWarnings("serial")
public class GateAttachEvent extends SingleGateEvent
{
	public GateAttachEvent(Gate gate)
	{
		super("GateAttachEvent", gate);
	}
}
