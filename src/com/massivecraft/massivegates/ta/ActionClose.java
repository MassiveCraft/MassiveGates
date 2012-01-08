package com.massivecraft.massivegates.ta;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;

public class ActionClose extends BaseAction
{
	protected static ActionClose instance = new ActionClose();
	public static ActionClose getInstance() { return instance; }
	protected ActionClose()
	{
		super("mgcore_close", "Close", "Ensure the gate is closed.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (gate.isOpen() == false) return;
		gate.setOpen(false);
	}
}