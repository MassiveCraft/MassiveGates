package com.massivecraft.massivegates.ta;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;

public class ActionUseForced extends BaseAction
{
	protected static ActionUseForced instance = new ActionUseForced();
	public static ActionUseForced getInstance() { return instance; }
	protected ActionUseForced()
	{
		super("mgcore_use_forced", "UseForced", "Use the gate regardless of open state.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (entity == null) return;
		gate.use(entity);
	}
}