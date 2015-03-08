package com.massivecraft.massivegates.engine;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.EventMassiveGatesOpenChange;
import com.massivecraft.massivegates.event.EventMassiveGatesPlayerWalk;
import com.massivecraft.massivegates.event.EventMassiveGatesPlayerWalk.GatePlayerWalkType;
import com.massivecraft.massivegates.event.EventMassiveGatesPowerChange;
import com.massivecraft.massivegates.event.EventMassiveGatesTeleportAfter;
import com.massivecraft.massivegates.event.EventMassiveGatesTeleportBefore;
import com.massivecraft.massivegates.event.EventMassiveGatesUse;
import com.massivecraft.massivegates.ta.TriggerAtp;
import com.massivecraft.massivegates.ta.TriggerBtp;
import com.massivecraft.massivegates.ta.TriggerClose;
import com.massivecraft.massivegates.ta.TriggerEnter;
import com.massivecraft.massivegates.ta.TriggerOpen;
import com.massivecraft.massivegates.ta.TriggerPowerOff;
import com.massivecraft.massivegates.ta.TriggerPowerOn;
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
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateBeforeTeleportEvent(EventMassiveGatesTeleportBefore event)
	{
		event.getGate().trigger(TriggerBtp.get(), event.getPlayer(), null);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateAfterTeleportEvent(EventMassiveGatesTeleportAfter event)
	{
		event.getGate().trigger(TriggerAtp.get(), event.getPlayer(), null);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateOpenChangeEvent(EventMassiveGatesOpenChange event)
	{
		// TODO: Improve with user and cause ???
		if (event.getGate().isOpen())
		{
			event.getGate().trigger(TriggerOpen.get(), null, null);
		}
		else
		{
			event.getGate().trigger(TriggerClose.get(), null, null);
		}
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
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGatePowerChangeEvent(EventMassiveGatesPowerChange event)
	{
		if (event.powerHas())
		{
			event.getGate().trigger(TriggerPowerOn.get(), null, null);
		}
		else
		{
			event.getGate().trigger(TriggerPowerOff.get(), null, null);
		}
	}
	
}
