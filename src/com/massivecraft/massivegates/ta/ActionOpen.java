package com.massivecraft.massivegates.ta;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;

public class ActionOpen extends BaseAction
{
	protected static ActionOpen instance = new ActionOpen();
	public static ActionOpen getInstance() { return instance; }
	protected ActionOpen()
	{
		super("mgcore_open", "Open", "Ensure the gate is open.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (gate.isOpen() == true) return;
		gate.setOpen(true);
	}
}