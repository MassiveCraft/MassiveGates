package com.massivecraft.massivegates.ta;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.permissions.Permissible;

import com.massivecraft.massivecore.util.PermUtil;
import com.massivecraft.massivegates.entity.Gate;

public class ActionReqPerm extends BaseAction
{
	// -------------------------------------------- //
	// INTANCE AND CONSTRUCT
	// -------------------------------------------- //
	
	protected static ActionReqPerm i = new ActionReqPerm();
	public static ActionReqPerm get() { return i; }
	
	protected ActionReqPerm()
	{
		super("mgcore_reqperm", "reqperm", "Require that player has permission node");
	}
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final static List<String> errorsRequired = Arrays.asList("<b>Please provide the permission node");
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if ( ! hasPerm(arg, entity))
		{
			cancellable.setCancelled(true);
		}
	}
	
	@Override
	public List<String> checkArg(String arg)
	{
		if (arg == null || arg.trim().length() == 0)
		{
			return errorsRequired;
		}
				
		return null;
	}
	
	public static boolean hasPerm(String perm, Entity entity)
	{
		if (entity == null) return false;
		if ( ! (entity instanceof Permissible)) return false;
		Permissible permissible = (Permissible)entity;
		
		return PermUtil.has(permissible, perm, true);
	}
	
}
