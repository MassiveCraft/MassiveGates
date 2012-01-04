package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore1.cmd.req.ReqHasPerm;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore1.util.Txt;

public class CmdGateMato extends GateCommand
{
	public CmdGateMato()
	{
		super();
		this.addAliases("mato");
		this.addOptionalArg("material", "get");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.MATO.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		Material mat = gate.getMatopen();
		
		if ( ! this.argIsSet(0))
		{
			this.msg("<i>Current open material: <h>"+Txt.getMaterialName(mat)+"<i>.");
			return;
		}
		
		mat = this.argAs(0, Material.class);
		if (mat == null) return;
		gate.setMatopen(mat);
		this.msg("<i>New open material: <h>"+Txt.getMaterialName(mat)+"<i>.");
	}
}