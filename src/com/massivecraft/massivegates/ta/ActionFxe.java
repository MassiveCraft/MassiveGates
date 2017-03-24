package com.massivecraft.massivegates.ta;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.util.Fx;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import java.util.Arrays;
import java.util.List;

public class ActionFxe extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionFxe i = new ActionFxe();
	public static ActionFxe get() { return i; }
	
	protected ActionFxe()
	{
		super("mgcore_fxe", "FXE", "Perform FX at the entity");
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
		if (entity == null) return;
		Fx.perform(arg, entity);
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
