package com.massivecraft.massivegates.ta;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.util.Fx;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import java.util.Arrays;
import java.util.List;

public class ActionFxg extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionFxg i = new ActionFxg();
	public static ActionFxg get() { return i; }
	
	protected ActionFxg()
	{
		super("mgcore_fxg", "FXG", "Perform FX at the gate");
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final static List<String> errorsRequired = Arrays.asList("<b>You must describe the FX in the argument!");
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		Fx.perform(arg, gate);
	}
	
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
