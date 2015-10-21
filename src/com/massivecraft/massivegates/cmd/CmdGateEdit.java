package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;


public class CmdGateEdit extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdGateEditThat cmdMassiveGatesEditThat = new CmdGateEditThat();
	public CmdGateEditFlood cmdMassiveGatesEditFlood = new CmdGateEditFlood();
	public CmdGateEditClear cmdMassiveGatesEditClear = new CmdGateEditClear();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateEdit()
	{
		// Aliases
		this.addAliases("edit");
		
		// Children
		this.addChild(this.cmdMassiveGatesEditThat);
		this.addChild(this.cmdMassiveGatesEditFlood);
		this.addChild(this.cmdMassiveGatesEditClear);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.EDIT.node));
	}

}
