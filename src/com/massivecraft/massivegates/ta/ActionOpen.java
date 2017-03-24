package com.massivecraft.massivegates.ta;

import com.massivecraft.massivegates.entity.Gate;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

public class ActionOpen extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionOpen i = new ActionOpen();
	public static ActionOpen get() { return i; }
	
	protected ActionOpen()
	{
		super("mgcore_open", "Open", "Ensure the gate is open.");
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (gate.isOpen() == true) return;
		gate.setOpen(true);
	}
	
}
