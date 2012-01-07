package com.massivecraft.massivegates.when;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;

public class ActionUseIfOpen extends BaseAction
{
	protected static ActionUseIfOpen instance = new ActionUseIfOpen();
	public static ActionUseIfOpen getInstance() { return instance; }
	protected ActionUseIfOpen()
	{
		super("mgcore_use_if_open", "UseIfOpen", "Make the entity use the gate if it's open.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (entity == null) return;
		if ( ! gate.isOpen() ) return;
		gate.use(entity);
	}
}