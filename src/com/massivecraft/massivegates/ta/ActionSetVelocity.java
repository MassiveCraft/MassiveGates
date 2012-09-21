package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.util.Vector;

import com.massivecraft.massivegates.Gate;

public class ActionSetVelocity extends BaseAction
{
	protected static ActionSetVelocity instance = new ActionSetVelocity();
	public static ActionSetVelocity getInstance() { return instance; }
	protected ActionSetVelocity()
	{
		super("mgcore_SetVelocity", "setvel", "Set entity velocity: X Y Z");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (entity == null) return;
		entity.setVelocity(parseVector(arg));
	}
	
	public final static List<String> errorsRequired = Arrays.asList("<b>Please provide three doubles: X Y X");
	@Override
	public List<String> checkArg(String arg)
	{
		if (arg == null || arg.trim().length() == 0 || parseVector(arg) == null)
		{
			return errorsRequired;
		}
				
		return null;
	}
	
	protected static Vector parseVector(String arg)
	{
		try
		{
			String[] parts = arg.split("\\s+");
			double x = Double.valueOf(parts[0]);
			double y = Double.valueOf(parts[1]);
			double z = Double.valueOf(parts[2]);
			return new Vector(x, y, z);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}