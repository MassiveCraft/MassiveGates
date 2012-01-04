package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Conf;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.HelpCommand;

public class CmdGate extends GateCommand
{
	public CmdGate()
	{
		super();
		this.addAliases(Conf.baseCommandAliases);
		this.addSubCommand(HelpCommand.getInstance());
		this.addSubCommand(new CmdGateNew());
		this.addSubCommand(new CmdGateDel());
		this.addSubCommand(new CmdGateList());
		this.addSubCommand(new CmdGateSel());
		this.addSubCommand(new CmdGateOpen());
		this.addSubCommand(new CmdGateEdit());
		this.addSubCommand(new CmdGateMato());
		this.addSubCommand(new CmdGateMatc());
		this.addSubCommand(new CmdGateSee());
		this.addSubCommand(new CmdGateTarget());
		this.addSubCommand(new CmdGateExit());
		this.addSubCommand(new CmdGateWhen());
	}
	
	@Override
	public void perform()
	{
		this.getCommandChain().add(this);
		HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
	}
}