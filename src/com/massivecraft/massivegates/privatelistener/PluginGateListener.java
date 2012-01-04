package com.massivecraft.massivegates.privatelistener;

import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.event.GateBeforeTeleportEvent;
import com.massivecraft.massivegates.event.GateListener;
import com.massivecraft.massivegates.fx.GateFxMoment;

public class PluginGateListener extends GateListener
{
	P p;
	public PluginGateListener(P p)
	{
		this.p = p;
	}
	
	@Override
	public void onGateBeforeTeleport(GateBeforeTeleportEvent event)
	{
		// Run Before Teleport Fxs
		event.getGate().runFx(GateFxMoment.BEFORE_TELEPORT, event.getUser());
	}
	
	@Override
	public void onGateAfterTeleport(GateAfterTeleportEvent event)
	{
		// Run After Teleport Fxs
		event.getGate().runFx(GateFxMoment.AFTER_TELEPORT, event.getUser());
	}
}
