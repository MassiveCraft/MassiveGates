package com.massivecraft.massivegates.event;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.event.abs.GateTeleportEvent;

@SuppressWarnings("serial")
public class GateBeforeTeleportEvent extends GateTeleportEvent
{	
	public GateBeforeTeleportEvent(Gate gate, Entity user, Location from, Location to)
	{
		super("GateBeforeTeleportEvent", gate, user, from, to);
	}
}
