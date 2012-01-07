package com.massivecraft.massivegates.when;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.mcore1.util.SmokeUtil;

public class ActionFxExp extends BaseAction
{
	protected static ActionFxExp instance = new ActionFxExp();
	public static ActionFxExp getInstance() { return instance; }
	protected ActionFxExp()
	{
		super("mgcore_fx_exp", "FxExp", "Non damaging TNT explosion effect.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (entity != null)
		{
			SmokeUtil.fakeExplosion(entity.getLocation());
		}
		else if (gate != null)
		{
			SmokeUtil.fakeExplosion(gate.calcGateCenter());
		}
	}
}