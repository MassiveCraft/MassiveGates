package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore5.cmd.arg.ARByte;
import com.massivecraft.mcore5.cmd.arg.ARMaterial;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore5.util.Txt;

public class CmdGateMato extends GateCommand
{
	public CmdGateMato()
	{
		super();
		this.addAliases("mo","mato");
		this.addOptionalArg("material", "get");
		this.addOptionalArg("data", "0");
		
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.getInstance());
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
		
		mat = this.arg(0, ARMaterial.get());
		if (mat == null) return;
		
		data = this.arg(1, ARByte.get(), (byte) 0);
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