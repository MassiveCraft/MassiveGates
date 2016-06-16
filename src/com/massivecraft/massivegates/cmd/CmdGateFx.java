package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivegates.Perm;


public class CmdGateFx extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdGateFxAlt cmdMassiveGatesFxAlt = new CmdGateFxAlt();
	public CmdGateFxTest cmdMassiveGatesFxTest = new CmdGateFxTest();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateFx()
	{
		// Aliases
		this.addAliases("fx");
		
		// Children
		this.addChild(this.cmdMassiveGatesFxAlt);
		this.addChild(this.cmdMassiveGatesFxTest);
		
		// Requirements
		this.addRequirements(RequirementHasPerm.get(Perm.FX.id));
		
		// Help
		this.setHelp(
		"<i>This system is used by the triggeractions \"FXE\" and \"FXG\".",
		"<i>Effects are defined with a comma-list of base effects.",
		"<i>Some effects use a datavalue. Put an int after effect name.",
		"<i>Example: \"<h>smoke,potion5,fire<i>\". (potion got d�ata=5)"
		);
	}
	
}
