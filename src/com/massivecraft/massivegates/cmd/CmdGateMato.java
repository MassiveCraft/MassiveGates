package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.arg.ARByte;
import com.massivecraft.massivecore.cmd.arg.ARMaterial;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateMato extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public CmdGateMato()
	{
		// Aliases
		this.addAliases("mo","mato");
		
		// Args
		this.addOptionalArg("material", "get");
		this.addOptionalArg("data", "0");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.MATO.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Internal Args
		Gate gate = gsender.getSelectedGate();
		Material mat = gate.getMatopen();
		Byte data = gate.getDataopen();
		
		if ( ! this.argIsSet(0))
		{
			this.msg("<i>Current open <k>Material <v>"+Txt.getMaterialName(mat)+" <k>Data <v>"+data+".");
			return;
		}
		
		// Args
		mat = this.arg(0, ARMaterial.get());
		data = this.arg(1, ARByte.get(), (byte) 0);
		
		if ( ! mat.isBlock())
		{
			this.msg("<h>%s <b>is an item and not a block.", Txt.getMaterialName(mat));
			return;
		}
		
		// Apply
		gate.setMatopen(mat, data);
		
		// Inform
		this.msg("<i>New closed <k>Material <i>is <v>%s <i>with <k>Data <v>%s<i>.", Txt.getMaterialName(mat), data);
	}
	
}
