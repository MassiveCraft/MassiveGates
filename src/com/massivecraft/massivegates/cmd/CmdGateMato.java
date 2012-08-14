package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;
import com.massivecraft.mcore4.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore4.util.Txt;

public class CmdGateMato extends GateCommand
{
	public CmdGateMato()
	{
		super();
		this.addAliases("mo","mato");
		this.addOptionalArg("material", "get");
		this.addOptionalArg("data", "0");
		
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.MATO.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		Material mat = gate.getMatopen();
		Byte data = gate.getDataopen();
		
		if ( ! this.argIsSet(0))
		{
			this.msg("<i>Current open <k>Material <v>"+Txt.getMaterialName(mat)+" <k>Data <v>"+data+".");
			return;
		}
		
		mat = this.argAs(0, Material.class);
		if (mat == null) return;
		
		data = this.argAs(1, Byte.class, (byte) 0);
		if (data == null) return;
		
		if ( ! mat.isBlock())
		{
			this.msg("<h>asdf <b>is an item and not a block.");
			return;
		}
		
		gate.setMatopen(mat, data);
		this.msg("<i>New open <k>Material <v>"+Txt.getMaterialName(mat)+" <k>Data <v>"+data+".");
	}
}