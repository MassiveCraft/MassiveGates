package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.util.Fx;

public class ActionFxg extends BaseAction
{
	protected static ActionFxg instance = new ActionFxg();
	public static ActionFxg getInstance() { return instance; }
	protected ActionFxg()
	{
		super("mgcore_fxg", "FXG", "Perform FX at the gate");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		Fx.perform(arg, gate);
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