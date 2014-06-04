package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivecore.cmd.arg.ARByte;
import com.massivecraft.massivecore.cmd.arg.ARMaterial;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;

public class CmdGateMato extends GateCommand
{
	public CmdGateMato()
	{
		this.addAliases("mo","mato");
		this.addOptionalArg("material", "get");
		this.addOptionalArg("data", "0");
		
		this.addRequirements(ReqGateSelected.get());
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