package com.massivecraft.massivegates.event;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.CancellableSingleGateEvent;

/**
 * This should be called when a gate is opened. There are many reasons to why a gate would open.
 * Some might open because it turned night or a player ate bread nearby.
 * 
 * TODO: Should the reason be sent with the event? That might be complex data to send along.
 */

@SuppressWarnings("serial")
public class GateOpenEvent extends CancellableSingleGateEvent
{	
	public GateOpenEvent(Gate gate)
	{
		super("GateOpenEvent", gate);
	}	
}
