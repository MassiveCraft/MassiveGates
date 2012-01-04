package com.massivecraft.massivegates.privatelistener;

import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.event.GateBeforeTeleportEvent;
import com.massivecraft.massivegates.event.GateCloseEvent;
import com.massivecraft.massivegates.event.GateListener;
import com.massivecraft.massivegates.event.GateOpenEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkType;
import com.massivecraft.massivegates.event.GateUseEvent;
import com.massivecraft.massivegates.when.TriggerAtp;
import com.massivecraft.massivegates.when.TriggerBtp;
import com.massivecraft.massivegates.when.TriggerClose;
import com.massivecraft.massivegates.when.TriggerOpen;
import com.massivecraft.massivegates.when.TriggerPlayerEnter;
import com.massivecraft.massivegates.when.TriggerUse;

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
	public void onGateOpen(GateOpenEvent event)
	{
		// TODO:_ Improve with user and cause.
		event.getGate().trigger(TriggerOpen.getInstance(), null, event);
	}
	
	@Override
	public void onGateClose(GateCloseEvent event)
	{
		event.getGate().trigger(TriggerClose.getInstance(), null, event);
	}
	
	@Override
	public void onGatePlayerWalk(GatePlayerWalkEvent event)
	{
		if (event.getWalkType() == GatePlayerWalkType.INTO)
		{
			event.getGateTo().trigger(TriggerPlayerEnter.getInstance(), event.getPlayer(), event);
		}
	}
}
