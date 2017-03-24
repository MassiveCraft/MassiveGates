package com.massivecraft.massivegates.ta;

import com.massivecraft.massivegates.entity.Gate;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class ActionUse extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionUse i = new ActionUse();
	public static ActionUse get() { return i; }
	
	protected ActionUse()
	{
		super("mgcore_use", "Use", "Use the gate if it's open.");
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (entity == null) return;

		if ( ! gate.isOpen() ) return;
		gate.use(entity);
	}
	
}
