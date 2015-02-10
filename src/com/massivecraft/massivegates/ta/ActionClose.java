package com.massivecraft.massivegates.ta;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.entity.Gate;

public class ActionClose extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionClose instance = new ActionClose();
	public static ActionClose get() { return instance; }
	
	protected ActionClose()
	{
		super("close", "Close", "Ensure the gate is closed.");
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
