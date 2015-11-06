package com.massivecraft.massivegates.cmd;

import org.bukkit.Material;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.enumeration.TypeMaterial;
import com.massivecraft.massivecore.command.type.primitive.TypeByte;
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
		
		// Parameters
		this.addParameter(TypeMaterial.get(), "material", "get");
		this.addParameter(TypeByte.get(), "data", "0");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.MATO.node));
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
		mat = this.readArg();
		data = this.readArg((byte) 0);
		
		if ( ! mat.isBlock())
		{
			this.msg("<h>%s <b>is an item and not a block.", Txt.getMaterialName(mat));
			return;
		}
		
		// Apply
		gate.setMatopen(mat, data);
		
		// Inform
		this.msg("<i>New open <k>Material <i>is <v>%s <i>with <k>Data <v>%s<i>.", Txt.getMaterialName(mat), data);
	}
	
}
