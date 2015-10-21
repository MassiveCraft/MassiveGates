package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;


// In the sub commands we will often have rows like this:
// trigger index action arg desc
//
// We use the corresponding colors:
// <lime> <rose ><k> <v> <i>

public class CmdGateTa extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdGateTaAlt cmdMassiveGatesTaAlt = new CmdGateTaAlt();
	public CmdGateTaList cmdMassiveGatesTaList = new CmdGateTaList();
	public CmdGateTaAdd cmdMassiveGatesTaAdd = new CmdGateTaAdd();
	public CmdGateTaDel cmdMassiveGatesTaDel = new CmdGateTaDel();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateTa()
	{
		// Aliases
		this.addAliases("ta");
		
		// Children
		this.addChild(this.cmdMassiveGatesTaAlt);
		this.addChild(this.cmdMassiveGatesTaList);
		this.addChild(this.cmdMassiveGatesTaAdd);
		this.addChild(this.cmdMassiveGatesTaDel);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.TA.node));
	}

}
