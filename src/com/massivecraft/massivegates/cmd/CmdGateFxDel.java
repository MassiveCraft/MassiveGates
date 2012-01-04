package com.massivecraft.massivegates.cmd;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.fx.GateFxMoment;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateFxDel extends GateCommand
{
	public CmdGateFxDel()
	{
		super();
		this.addAliases("del");
		this.addRequiredArg(GateFxMoment.getOrShorts());
		this.addRequiredArg("startOrIndex");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
	}
	
	protected final static Pattern integerPattern = Pattern.compile("^\\d*$");
	public static boolean isNumeric(String str)
	{
		Matcher matchesInteger = integerPattern.matcher(str);
		return matchesInteger.matches();
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		// Fetch the fxMoment
		GateFxMoment fxMoment = this.argAs(0, GateFxMoment.class);
		if (fxMoment == null) return;
		
		// Fetch the startOrIndex
		String startOrIndex = this.arg(1);
		
		if (isNumeric(startOrIndex))
		{
			int index = Integer.parseInt(startOrIndex);
			index -= 1; // From human to zero-based.
			boolean deleted = gate.delFxParsieByIndex(fxMoment, index);
			if (deleted)
			{
				this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> - Deleted "+fxMoment.getShortName()+"-fx at index <h>"+(index+1)+"<i>.");
			}
			else
			{
				this.msg("<b>Gate "+gate.getIdNameStringShort()+"<b> - There is no "+fxMoment.getShortName()+"-fx at index <h>"+(index+1)+"<b>.");
			}
		}
		else
		{
			int deleted = gate.delFxParsieByStart(fxMoment, startOrIndex);
			this.msg("<i>Gate "+gate.getIdNameStringShort()+"<i> - Deleted <h>"+deleted+" <i>"+fxMoment.getShortName()+"-fx that started with \"<h>"+startOrIndex+"<i>\".");
		}
	}
}