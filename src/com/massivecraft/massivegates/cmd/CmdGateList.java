package com.massivecraft.massivegates.cmd;

import java.util.List;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARInteger;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.pager.PagerSimple;
import com.massivecraft.massivecore.pager.Stringifier;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;

public class CmdGateList extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public CmdGateList()
	{
		// Aliases
		this.addAliases("l","list");
		
		// Args
		this.addOptionalArg("page", "1");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.LIST.node));
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Integer pageHumanBased = this.arg(0, ARInteger.get(), 1);
		
		// Create Pager
		final PagerSimple<Gate> pager = new PagerSimple<Gate>(GateColl.get().getAll(), sender);
		
		// Use Pager
		List<String> messages = pager.getPageTxt(pageHumanBased, "List Of Gates", new Stringifier<Gate>(){
			
			@Override
			public String toString(Gate gate, int index)
			{
				return gate.getIdNameStringShort();
			}
			
		});
		
		// Send Lines
		this.sendMessage(messages);	
	}
	
}
