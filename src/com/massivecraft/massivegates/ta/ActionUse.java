package com.massivecraft.massivegates.ta;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.entity.Gate;

public class ActionUse extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionUse instance = new ActionUse();
	public static ActionUse get() { return instance; }
	
	protected ActionUse()
	{
		super("use", "Use", "Use the gate if it's open.");
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
