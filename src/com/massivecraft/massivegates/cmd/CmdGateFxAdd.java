package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.fx.GateFx;
import com.massivecraft.massivegates.fx.GateFxMoment;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateFxAdd extends GateCommand
{
	public CmdGateFxAdd()
	{
		super();
		this.addAliases("add");
		this.addRequiredArg(GateFxMoment.getOrShorts());
		this.addRequiredArg("effect");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		// Fetch the fxMoment
		GateFxMoment fxMoment = this.argAs(0, GateFxMoment.class);
		if (fxMoment == null) return;
		
		// Fetch the fx
		GateFx fx = this.argAs(1, GateFx.class);
		if (fx == null) return;
		
		// Fetch the parsie
		String parsie = this.arg(1);
		
		// Test for errors
		fx.parse(parsie);
		if ( ! fx.getParseErrors().isEmpty())
		{
			this.msg("<b>Error(s) with your "+fx.getName()+":");
			for (String error : fx.getParseErrors())
			{
				this.msg(error);
			}
			return;
		}
		
		gate.addFxParsie(fxMoment, parsie);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>added new fx:");
		this.msg("<lime>"+fxMoment.getShortName()+" <pink>"+parsie+" <i>"+fx.getDesc(parsie));
	}
}