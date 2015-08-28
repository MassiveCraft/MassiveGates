package com.massivecraft.massivegates.engine;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.EventMassiveGatesPlayerWalk;
import com.massivecraft.massivegates.event.EventMassiveGatesPlayerWalk.GatePlayerWalkType;
import com.massivecraft.massivegates.event.EventMassiveGatesUse;
import com.massivecraft.massivegates.ta.TriggerEnter;
import com.massivecraft.massivegates.ta.TriggerUse;

public class EngineGate extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static EngineGate i = new EngineGate();
	public static EngineGate get() { return i; }
	private EngineGate() {}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return MassiveGates.get();
	}

	// -------------------------------------------- //
	// GATE EVENTS
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalGateUseEvent(EventMassiveGatesUse event)
	{
		event.getGate().trigger(TriggerUse.get(), event.getUser(), event);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void normalGatePlayerWalkEvent(EventMassiveGatesPlayerWalk event)
	{
		if (event.getWalkType() == GatePlayerWalkType.INTO)
		{
			event.getGateTo().trigger(TriggerEnter.get(), event.getPlayer(), event);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void recalculatePortalOrientation(EventMassiveGatesPlayerWalk event)
	{
		Gate gateFrom = event.getGateFrom();
		Gate gateTo = event.getGateTo();
		
		if (gateFrom != null)
		{
			gateFrom.recalculatePortalOrientation();
		}
		
		if (gateTo != null)
		{
			gateTo.recalculatePortalOrientation();
		}
	}
	
}
