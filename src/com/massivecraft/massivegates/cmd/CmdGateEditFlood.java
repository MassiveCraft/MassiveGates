package com.massivecraft.massivegates.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.massivecraft.massivegates.entity.MConf;
import org.bukkit.block.Block;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.command.type.primitive.TypeBooleanTrue;
import com.massivecraft.massivecore.util.Txt;
import com.massivecraft.massivegates.Const;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.util.FloodOrientation;
import com.massivecraft.massivegates.util.FloodUtil;
import com.massivecraft.massivegates.util.VisualizeUtil;

public class CmdGateEditFlood extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateEditFlood()
	{
		// Parameters
		this.addParameter(TypeBooleanTrue.get(), "frame", "true");
		
		// Requirements
		this.addRequirements(RequirementIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.EDIT_FLOOD.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateEditFlood;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		boolean addFrame = this.readArg(true);
		Entry<FloodOrientation, Set<Block>> flood = gsender.getBestFloodHere(true);
		if (flood == null) return;
		
		// Apply
		FloodOrientation orientation = flood.getKey();
		Set<Block> content = flood.getValue();
		
		if (addFrame)
		{
			Set<Block> frame = FloodUtil.getFrameFor(content, orientation);
			gate.addFrameBlocks(frame);
			VisualizeUtil.addBlocks(me, frame, Const.visFrame);
		}
		
		gate.addContentBlocks(content);
		VisualizeUtil.addBlocks(me, content, Const.visContent);
		
		// Create Messages
		List<String> messages = new ArrayList<String>();
		messages.add(Txt.parse("<i>The flooding worked and the gate shape <h>%s<i> was added .", orientation.getDesc()));
		if (addFrame)
		{
			messages.add(Txt.parse("<i>The corresponding frame was added as well."));
		}
		else
		{
			messages.add(Txt.parse("<i>No frame blocks were added as per your request."));
		}
		
		// Send Messages
		message(messages);
	}
	
}
