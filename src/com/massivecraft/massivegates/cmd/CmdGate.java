package com.massivecraft.massivegates.cmd;

import java.util.List;

import com.massivecraft.massivegates.entity.MConf;

public class CmdGate extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdGateNew cmdMassiveGatesNew = new CmdGateNew();
	public CmdGateDelete cmdMassiveGatesDelete = new CmdGateDelete();
	public CmdGateList cmdMassiveGatesList = new CmdGateList();
	public CmdGateSel cmdMassiveGatesSel = new CmdGateSel();
	public CmdGateName cmdMassiveGatesName = new CmdGateName();
	public CmdGateOpen cmdMassiveGatesOpen = new CmdGateOpen();
	public CmdGateEdit cmdMassiveGatesEdit = new CmdGateEdit();
	public CmdGateMato cmdMassiveGatesMato = new CmdGateMato();
	public CmdGateMatc cmdMassiveGatesMatc = new CmdGateMatc();
	public CmdGateSee cmdMassiveGatesSee = new CmdGateSee();
	public CmdGateTarget cmdMassiveGatesTarget = new CmdGateTarget();
	public CmdGateExit cmdMassiveGatesExit = new CmdGateExit();
	public CmdGateTa cmdMassiveGatesTa = new CmdGateTa();
	public CmdGateFx cmdMassiveGatesFx = new CmdGateFx();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGate()
	{
		// SubCommands
		this.addSubCommand(this.cmdMassiveGatesNew);
		this.addSubCommand(this.cmdMassiveGatesDelete);
		this.addSubCommand(this.cmdMassiveGatesList);
		this.addSubCommand(this.cmdMassiveGatesSel);
		this.addSubCommand(this.cmdMassiveGatesName);
		this.addSubCommand(this.cmdMassiveGatesOpen);
		this.addSubCommand(this.cmdMassiveGatesEdit);
		this.addSubCommand(this.cmdMassiveGatesMato);
		this.addSubCommand(this.cmdMassiveGatesMatc);
		this.addSubCommand(this.cmdMassiveGatesSee);
		this.addSubCommand(this.cmdMassiveGatesTarget);
		this.addSubCommand(this.cmdMassiveGatesExit);
		this.addSubCommand(this.cmdMassiveGatesTa);
		this.addSubCommand(this.cmdMassiveGatesFx);
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases() 
	{
		return MConf.get().aliasesG;
	}
	
}
