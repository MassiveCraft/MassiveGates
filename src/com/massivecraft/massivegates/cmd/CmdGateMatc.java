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

public class CmdGateMatc extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCTOR
	// -------------------------------------------- //
	
	public CmdGateMatc()
	{
		// Aliases
		this.addAliases("mc","matc");
		
		// Args
		this.addArg(ARMaterial.get(), "material", "get");
		this.addArg(ARByte.get(), "data", "0");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.MATC.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Internal Args
		Gate gate = gsender.getSelectedGate();
		Material mat = gate.getMatclosed();
		Byte data = gate.getDataclosed();
		
		if ( ! this.argIsSet(0))
		{
			this.msg("<i>Current closed <k>Material <i>is <v>%s <i>with <k>Data <v>%s<i>.", Txt.getMaterialName(mat), data);
			return;
		}
		
		// Args
		mat = this.readArg();
		data = this.readArg((byte) 0);
		
		if ( ! mat.isBlock())
		{
			this.msg("<h>%s <b>is an item and not a block.", Txt.getMaterialName(mat));
			return;
		}
		
		// Apply
		gate.setMatclosed(mat, data);
		
		// Inform
		this.msg("<i>New closed <k>Material <i>is <v>%s <i>with <k>Data <v>%s<i>.", Txt.getMaterialName(mat), data);
	}
	
}
