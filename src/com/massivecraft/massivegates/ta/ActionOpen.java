package com.massivecraft.massivegates.ta;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.entity.Gate;

public class ActionOpen extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionOpen instance = new ActionOpen();
	public static ActionOpen get() { return instance; }
	
	protected ActionOpen()
	{
		super("open", "Open", "Ensure the gate is open.");
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
