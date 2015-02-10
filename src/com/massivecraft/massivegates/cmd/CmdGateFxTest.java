package com.massivecraft.massivegates.cmd;

import org.bukkit.Location;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.util.Fx;

public class CmdGateFxTest extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	public CmdGateFxTest()
	{
		// Aliases
		this.addAliases("test");
		
		// Arg
		this.addRequiredArg("fxstring");
		
		// Requirements
		this.addRequirements(ReqIsPlayer.get());
		this.addRequirements(ReqHasPerm.get(Perm.FX_TEST.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Args
		Location location = gsender.getThatBlock(false).getLocation();
		String fx = this.arg(0);
		
		// Apply
		boolean success = Fx.perform(fx, location);
		
		// Inform
		if (success)
		{
			this.msg("<i>Just performed the fx \"<h>%s<i>\" where you looked.", fx);
		}
		else
		{
			this.msg(Fx.parseMultiErrors);
		}
	}
	
}
