package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.util.Fx;

public class ActionFxe extends BaseAction
{
	protected static ActionFxe instance = new ActionFxe();
	public static ActionFxe getInstance() { return instance; }
	protected ActionFxe()
	{
		super("mgcore_fxe", "FXE", "Perform FX at the entity");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (entity == null) return;
		Fx.perform(arg, entity);
	}
	
	public final static List<String> errorsRequired = Arrays.asList("<b>You must describe the FX in the argument!");
	@Override
	public List<String> checkArg(String arg)
	{
		if (arg == null || arg.trim().length() == 0)
		{
			return errorsRequired;
		}
		
		Fx.parseMultiFxString(arg);
		if (Fx.parseMultiErrors.size() != 0)
		{
			return Fx.parseMultiErrors;
		}
		
		return null;
	}
}