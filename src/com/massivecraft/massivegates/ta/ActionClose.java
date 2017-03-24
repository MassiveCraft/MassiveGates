package com.massivecraft.massivegates.ta;

import com.massivecraft.massivegates.entity.Gate;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class ActionClose extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionClose i = new ActionClose();
	public static ActionClose get() { return i; }
	
	protected ActionClose()
	{
		super("mgcore_close", "Close", "Ensure the gate is closed.");
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (gate.isOpen() == false) return;
		gate.setOpen(false);
	}
	
}
