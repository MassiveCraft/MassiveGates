package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateEditClear extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateEditClear()
	{
		// Aliases
		this.addAliases("clear");
		
		// Args
		this.addRequiredArg("frame|content|all");
		
		// Requirements
		this.addRequirements(ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.EDIT_CLEAR.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		List<String> messages = new ArrayList<String>();
		
		// Args
		Gate gate = gsender.getSelectedGate();
		
		char firstArgChar = this.arg(0).toLowerCase().charAt(0);
		
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
		sendMessage(messages);
		
	}
	
}
