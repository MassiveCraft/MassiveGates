package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.fx.GateFx;
import com.massivecraft.massivegates.fx.GateFxMoment;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore1.util.Txt;

public class CmdGateFxList extends GateCommand
{
	public CmdGateFxList()
	{
		super();
		this.addAliases("list");
		this.addOptionalArg("page", "1");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
	}
	
	@Override
	public void perform()
	{
		Integer pageHumanBased = this.argAs(0, Integer.class, 1);
		if (pageHumanBased == null) return;
		
		Gate gate = gme.getSelectedGate();
		
		List<String> lines = new ArrayList<String>();
		
		for(GateFxMoment fxm : GateFxMoment.values())
		{
			int idx = 0;
			for (String parsie : gate.getFxParsies(fxm))
			{
				idx += 1;
				GateFx gfx = Gates.i.getFx(parsie);
				if (gfx != null)
				{
					Map<String, Object> fxArgs = gfx.parse(parsie);
					lines.add("<aqua>"+idx+". <lime>"+fxm.getShortName()+" <pink>"+parsie+" <i>"+gfx.getDesc(fxArgs));
				}
				else
				{
					lines.add("<aqua>"+idx+". <lime>"+fxm.getShortName()+" <pink>"+parsie+" <b>ERROR: Unknown FX.");
				}
			}
		}
		
		lines = Txt.parseWrap(lines);
		this.sendMessage(Txt.getPage(lines, pageHumanBased, "FX For Gate "+Txt.parse(gate.getIdNameStringShort())));
	}
}