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
		// Children
		this.addChild(this.cmdMassiveGatesNew);
		this.addChild(this.cmdMassiveGatesDelete);
		this.addChild(this.cmdMassiveGatesList);
		this.addChild(this.cmdMassiveGatesSel);
		this.addChild(this.cmdMassiveGatesName);
		this.addChild(this.cmdMassiveGatesOpen);
		this.addChild(this.cmdMassiveGatesEdit);
		this.addChild(this.cmdMassiveGatesMato);
		this.addChild(this.cmdMassiveGatesMatc);
		this.addChild(this.cmdMassiveGatesSee);
		this.addChild(this.cmdMassiveGatesTarget);
		this.addChild(this.cmdMassiveGatesExit);
		this.addChild(this.cmdMassiveGatesTa);
		this.addChild(this.cmdMassiveGatesFx);
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
