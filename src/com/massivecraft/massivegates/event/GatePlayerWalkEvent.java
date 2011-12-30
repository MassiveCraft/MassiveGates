package com.massivecraft.massivegates.event;

import com.massivecraft.massivegates.Gate;

@SuppressWarnings("serial")
public class GatePlayerWalkEvent extends DualGateEvent
{
	// FIELD: walkType
	protected GatePlayerWalkType walkType;
	public GatePlayerWalkType getWalkType() { return this.walkType; }
	
	public GatePlayerWalkEvent(Gate gateFrom, Gate gateTo, GatePlayerWalkType walkType)
	{
		super("GatePlayerWalkEvent");
		this.gateFrom = gateFrom;
		this.gateTo = gateTo;
		this.walkType = walkType;
	}
}
