package com.massivecraft.massivegates.cmd;

import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.block.Block;

import com.massivecraft.massivegates.Conf;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.util.FloodOrientation;
import com.massivecraft.massivegates.util.FloodUtil;
import com.massivecraft.massivegates.util.VisualizeUtil;
import com.massivecraft.mcore2.cmd.req.ReqHasPerm;
import com.massivecraft.mcore2.cmd.req.ReqIsPlayer;

public class CmdGateEditFlood extends GateCommand
{
	public CmdGateEditFlood()
	{
		super();
		this.addAliases("flood");
		this.addOptionalArg("frame", "true");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.EDIT_FLOOD.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		Boolean addFrame = this.argAs(0, Boolean.class, true);
		if (addFrame == null) return;
		
		Entry<FloodOrientation, Set<Block>> flood = gme.getBestFloodHere(true);
		if (flood == null) return;
		
		FloodOrientation orientation = flood.getKey();
		Set<Block> content = flood.getValue();
		
		if (addFrame)
		{
			Set<Block> frame = FloodUtil.getFrameFor(content, orientation);
			gate.addFrameBlocks(frame);
			VisualizeUtil.addBlocks(me, frame, Conf.visFrame);
		}
		
		gate.addContentBlocks(content);
		VisualizeUtil.addBlocks(me, content, Conf.visContent);
		
		this.msg("<i>The flood found an added a gate shape <h>"+orientation.getDesc()+"<i>.");
		if (addFrame)
		{
			this.msg("<i>The corresponding frame was added as well.");
		}
		else
		{
			this.msg("<i>No frame blocks was added as per your request.");
		}
	}
}