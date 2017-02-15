package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.entity.MConf;

import java.util.List;


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
		// Children
		this.addChild(this.cmdMassiveGatesEditThat);
		this.addChild(this.cmdMassiveGatesEditFlood);
		this.addChild(this.cmdMassiveGatesEditClear);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.EDIT.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateEdit;
	}

}
