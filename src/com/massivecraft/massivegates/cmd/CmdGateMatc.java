package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore1.util.Txt;

public class CmdGateMatc extends GateCommand
{
	public CmdGateMatc()
	{
		super();
		this.addAliases("matc");
		this.addOptionalArg("material", "get");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		Material mat = gate.getMatclosed();
		
		if ( ! this.argIsSet(0))
		{
			this.msg("<i>Current closed material: <h>"+Txt.getMaterialName(mat)+"<i>.");
			return;
		}
		
		mat = this.argAs(0, Material.class);
		if (mat == null) return;
		gate.setMatclosed(mat);
		this.msg("<i>New closed material: <h>"+Txt.getMaterialName(mat)+"<i>.");
	}
}