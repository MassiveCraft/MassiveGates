package com.massivecraft.massivegates.event;


import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.CancellableSingleGateEvent;

@SuppressWarnings("serial")
public class GateCloseEvent extends CancellableSingleGateEvent
{
	public GateCloseEvent(Gate gate)
	{
		super("GateCloseEvent", gate);
	}
}
