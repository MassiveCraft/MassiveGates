package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeString;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;

import java.util.ArrayList;
import java.util.List;

public class CmdGateEditClear extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateEditClear()
	{
		// Parameters
		this.addParameter(TypeString.get(), "frame|content|all");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.EDIT_CLEAR.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateEditClear;
	}
	
	@Override
	public void perform()
	{
		List<String> messages = new ArrayList<String>();
		
		// Args
		Gate gate = gsender.getSelectedGate();
		
		char firstArgChar = this.argAt(0).toLowerCase().charAt(0);
		
		// Apply
		if (firstArgChar == 'f')
		{
			gate.clearFrame();
			messages.add(Txt.parse("<i>Gate %s<i>: All frame blocks were removed (but not the content).", gate.getIdNameStringShort()));
		}
		else if (firstArgChar == 'c')
		{
			gate.clearContent();
			messages.add(Txt.parse("<i>Gate %s<i>: All content blocks were removed (but not the frame).", gate.getIdNameStringShort()));
		}
		else if (firstArgChar == 'a')
		{
			gate.clearFrame();
			gate.clearContent();
			messages.add(Txt.parse("<i>Gate %s<i>: All blocks were removed (both frame and content).", gate.getIdNameStringShort()));
		}
		else
		{
			messages.add(Txt.parse("<b>Arg must be frame|content|all."));
		}
		
		// Inform
		message(messages);
		
	}
	
}
