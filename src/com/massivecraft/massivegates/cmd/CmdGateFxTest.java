package com.massivecraft.massivegates.cmd;

import org.bukkit.Location;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.util.Fx;
import com.massivecraft.mcore3.cmd.req.ReqHasPerm;
import com.massivecraft.mcore3.cmd.req.ReqIsPlayer;

public class CmdGateFxTest extends GateCommand
{
	public CmdGateFxTest()
	{
		super();
		this.addAliases("test");
		this.addRequiredArg("fxstring");
		this.addRequirements(ReqIsPlayer.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.FX_TEST.node));
	}
	
	@Override
	public void perform()
	{
		Location location = me.getLastTwoTargetBlocks(null, 5).get(0).getLocation();
		
		boolean success = Fx.perform(this.arg(0), location);
		if (success)
		{
			this.msg("<i>Just performed the fx \"<h>"+this.arg(0)+"<i>\" where you looked.");
		}
		else
		{
			this.msg(Fx.parseMultiErrors);
		}
	}
}