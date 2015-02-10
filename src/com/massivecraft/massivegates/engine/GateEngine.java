package com.massivecraft.massivegates.engine;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.event.GateBeforeTeleportEvent;
import com.massivecraft.massivegates.event.GateOpenChangeEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent;
import com.massivecraft.massivegates.event.GatePowerChangeEvent;
import com.massivecraft.massivegates.event.GateUseEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent.GatePlayerWalkType;
import com.massivecraft.massivegates.ta.TriggerAtp;
import com.massivecraft.massivegates.ta.TriggerBtp;
import com.massivecraft.massivegates.ta.TriggerClose;
import com.massivecraft.massivegates.ta.TriggerEnter;
import com.massivecraft.massivegates.ta.TriggerOpen;
import com.massivecraft.massivegates.ta.TriggerPowerOff;
import com.massivecraft.massivegates.ta.TriggerPowerOn;
import com.massivecraft.massivegates.ta.TriggerUse;

public class GateEngine extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static GateEngine i = new GateEngine();
	public static GateEngine get() { return i; }
	private GateEngine() {}
	
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
	public void normalGateUseEvent(GateUseEvent event)
	{
		event.getGate().trigger(TriggerUse.get(), event.getUser(), event);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateBeforeTeleportEvent(GateBeforeTeleportEvent event)
	{
		event.getGate().trigger(TriggerBtp.get(), event.getPlayer(), null);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateAfterTeleportEvent(GateAfterTeleportEvent event)
	{
		event.getGate().trigger(TriggerAtp.get(), event.getPlayer(), null);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGateOpenChangeEvent(GateOpenChangeEvent event)
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
	public void normalGatePlayerWalkEvent(GatePlayerWalkEvent event)
	{
		if (event.getWalkType() == GatePlayerWalkType.INTO)
		{
			event.getGateTo().trigger(TriggerEnter.get(), event.getPlayer(), event);
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorGatePowerChangeEvent(GatePowerChangeEvent event)
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
