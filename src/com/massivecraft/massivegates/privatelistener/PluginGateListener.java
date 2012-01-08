package com.massivecraft.massivegates.privatelistener;

import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.event.GateBeforeTeleportEvent;
import com.massivecraft.massivegates.event.GateListener;
import com.massivecraft.massivegates.event.GateOpenChangeEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkType;
import com.massivecraft.massivegates.event.GatePowerChangeEvent;
import com.massivecraft.massivegates.event.GateUseEvent;
import com.massivecraft.massivegates.ta.TriggerAtp;
import com.massivecraft.massivegates.ta.TriggerBtp;
import com.massivecraft.massivegates.ta.TriggerClose;
import com.massivecraft.massivegates.ta.TriggerEnter;
import com.massivecraft.massivegates.ta.TriggerOpen;
import com.massivecraft.massivegates.ta.TriggerPowerOff;
import com.massivecraft.massivegates.ta.TriggerPowerOn;
import com.massivecraft.massivegates.ta.TriggerUse;

public class PluginGateListener extends GateListener
{
	P p;
	public PluginGateListener(P p)
	{
		this.p = p;
	}
	
	@Override
	public void onGateUse(GateUseEvent event)
	{
		event.getGate().trigger(TriggerUse.getInstance(), event.getUser(), event);
	}
	
	@Override
	public void onGateBeforeTeleport(GateBeforeTeleportEvent event)
	{
		event.getGate().trigger(TriggerBtp.getInstance(), event.getUser(), null);
	}
	
	@Override
	public void onGateAfterTeleport(GateAfterTeleportEvent event)
	{
		event.getGate().trigger(TriggerAtp.getInstance(), event.getUser(), null);
	}
	
	@Override
	public void onGateOpenChange(GateOpenChangeEvent event)
	{
		// TODO: Improve with user and cause ???
		if (event.getGate().isOpen())
		{
			event.getGate().trigger(TriggerOpen.getInstance(), null, null);
		}
		else
		{
			event.getGate().trigger(TriggerClose.getInstance(), null, null);
		}
	}
	
	@Override
	public void onGatePlayerWalk(GatePlayerWalkEvent event)
	{
		if (event.getWalkType() == GatePlayerWalkType.INTO)
		{
			event.getGateTo().trigger(TriggerEnter.getInstance(), event.getPlayer(), event);
		}
	}
	
	@Override
	public void onGatePowerChange(GatePowerChangeEvent event)
	{
		if (event.powerHas())
		{
			event.getGate().trigger(TriggerPowerOn.getInstance(), null, null);
		}
		else
		{
			event.getGate().trigger(TriggerPowerOff.getInstance(), null, null);
		}
	}
}
