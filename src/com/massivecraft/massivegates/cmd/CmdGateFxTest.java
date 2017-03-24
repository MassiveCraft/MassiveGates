package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.util.Fx;
import org.bukkit.Location;

import java.util.List;

public class CmdGateFxTest extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	public CmdGateFxTest()
	{
		// Arg
		this.addParameter(TypeString.get(), "fxstring");
		
		// Requirements
		this.addRequirements(RequirementIsPlayer.get());
		this.addRequirements(RequirementHasPerm.get(Perm.FX_TEST.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateFxTest;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Location location = gsender.getThatBlock(false).getLocation();
		String fx = this.readArg();
		
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
