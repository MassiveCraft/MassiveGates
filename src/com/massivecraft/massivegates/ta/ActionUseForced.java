package com.massivecraft.massivegates.ta;

import com.massivecraft.massivegates.entity.Gate;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class ActionUseForced extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionUseForced i = new ActionUseForced();
	public static ActionUseForced get() { return i; }
	
	protected ActionUseForced()
	{
		super("mgcore_use_forced", "UseForced", "Use the gate regardless of open state.");
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (entity == null) return;

		gate.use(entity);
	}
	
}
