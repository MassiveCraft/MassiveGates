package com.massivecraft.massivegates.cmd;

import java.util.List;

import com.massivecraft.massivecore.cmd.HelpCommand;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.MConf;

public class CmdGate extends GateCommand
{
	public CmdGate()
	{
		this.addSubCommand(HelpCommand.get());
		this.addSubCommand(new CmdGateNew());
		this.addSubCommand(new CmdGateDelete());
		this.addSubCommand(new CmdGateList());
		this.addSubCommand(new CmdGateSel());
		this.addSubCommand(new CmdGateName());
		this.addSubCommand(new CmdGateOpen());
		this.addSubCommand(new CmdGateEdit());
		this.addSubCommand(new CmdGateMato());
		this.addSubCommand(new CmdGateMatc());
		this.addSubCommand(new CmdGateSee());
		this.addSubCommand(new CmdGateTarget());
		this.addSubCommand(new CmdGateExit());
		this.addSubCommand(new CmdGateTa());
		this.addSubCommand(new CmdGateFx());
	}
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesG;
	}

}