package com.massivecraft.massivegates.cmd;

import java.util.List;

import com.massivecraft.massivegates.entity.MConf;

public class CmdGate extends GateCommand
{
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static CmdGate i = new CmdGate();
	public static CmdGate get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdGateNew cmdGateNew = new CmdGateNew();
	public CmdGateDelete cmdGateDelete = new CmdGateDelete();
	public CmdGateList cmdGateList = new CmdGateList();
	public CmdGateSel cmdGateSel = new CmdGateSel();
	public CmdGateName cmdGateName = new CmdGateName();
	public CmdGateOpen cmdGateOpen = new CmdGateOpen();
	public CmdGateEdit cmdGateEdit = new CmdGateEdit();
	public CmdGateMato cmdGateMato = new CmdGateMato();
	public CmdGateMatc cmdGateMatc = new CmdGateMatc();
	public CmdGateSee cmdGateSee = new CmdGateSee();
	public CmdGateTarget cmdGateTarget = new CmdGateTarget();
	public CmdGateExit cmdGateExit = new CmdGateExit();
	public CmdGateTa cmdGateTa = new CmdGateTa();
	public CmdGateFx cmdGateFx = new CmdGateFx();
	public CmdGateConfig cmdGateConfig = new CmdGateConfig();
	public CmdGateVersion cmdGateVersion = new CmdGateVersion();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGate()
	{
		// Children
		this.addChild(this.cmdGateNew);
		this.addChild(this.cmdGateDelete);
		this.addChild(this.cmdGateList);
		this.addChild(this.cmdGateSel);
		this.addChild(this.cmdGateName);
		this.addChild(this.cmdGateOpen);
		this.addChild(this.cmdGateEdit);
		this.addChild(this.cmdGateMato);
		this.addChild(this.cmdGateMatc);
		this.addChild(this.cmdGateSee);
		this.addChild(this.cmdGateTarget);
		this.addChild(this.cmdGateExit);
		this.addChild(this.cmdGateTa);
		this.addChild(this.cmdGateFx);
		this.addChild(this.cmdGateConfig);
		this.addChild(this.cmdGateVersion);
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases() 
	{
		return MConf.get().aliasesGate;
	}
	
}
