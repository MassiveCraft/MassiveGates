package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore.cmd.arg.ARByte;
import com.massivecraft.mcore.cmd.arg.ARMaterial;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.util.Txt;

public class CmdGateMatc extends GateCommand
{
	public CmdGateMatc()
	{
		super();
		this.addAliases("mc","matc");
		this.addOptionalArg("material", "get");
		this.addOptionalArg("data", "0");
		
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.MATC.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		Material mat = gate.getMatclosed();
		Byte data = gate.getDataclosed();
		
		if ( ! this.argIsSet(0))
		{
			this.msg("<i>Current closed <k>Material <v>"+Txt.getMaterialName(mat)+" <k>Data <v>"+data+".");
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
		
		gate.setMatclosed(mat, data);
		this.msg("<i>New closed <k>Material <v>"+Txt.getMaterialName(mat)+" <k>Data <v>"+data+".");
	}
}