package com.massivecraft.massivegates.ta;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.entity.Gate;

public class ActionUseForced extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionUseForced instance = new ActionUseForced();
	public static ActionUseForced get() { return instance; }
	
	protected ActionUseForced()
	{
		super("use_forced", "UseForced", "Use the gate regardless of open state.");
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
