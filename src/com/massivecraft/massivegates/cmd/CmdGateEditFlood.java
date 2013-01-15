package com.massivecraft.massivegates.cmd;

import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.block.Block;

import com.massivecraft.massivegates.Const;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.util.FloodOrientation;
import com.massivecraft.massivegates.util.FloodUtil;
import com.massivecraft.massivegates.util.VisualizeUtil;
import com.massivecraft.mcore5.cmd.arg.ARBoolean;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;

public class CmdGateEditFlood extends GateCommand
{
	public CmdGateEditFlood()
	{
		super();
		this.addAliases("flood");
		this.addOptionalArg("frame", "true");
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.EDIT_FLOOD.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		Boolean addFrame = this.arg(0, ARBoolean.get(), true);
		if (addFrame == null) return;
		
		Entry<FloodOrientation, Set<Block>> flood = gme.getBestFloodHere(true);
		if (flood == null) return;
		
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