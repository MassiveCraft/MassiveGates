package com.massivecraft.massivegates.cmd;

import java.util.HashSet;
import java.util.List;

import org.bukkit.block.Block;

import com.massivecraft.massivegates.Conf;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.WorldCoord3;

public class CmdGateSelectSet extends GateCommand
{
	public CmdGateSelectSet()
	{
		this.addAliases("set");
		this.addOptionalArg("idOrName", "*looking at*");
		this.setErrorOnToManyArgs(false);
	}

	@Override
	public void perform()
	{
		Gate gate = null;
		if (this.argIsSet(0))
		{
			gate = this.argAs(0, Gate.class);
			if (gate == null) return;
		}
		else
		{
			// Use the players line of sight to locate a gate.
			List<Block> lineOfSightBlocks = me.getLineOfSight(new HashSet<Byte>(0), Conf.lineOfSightLimit);
			WorldCoord3 coord = new WorldCoord3();
			for (Block lineOfSightBlock : lineOfSightBlocks)
			{
				coord.load(lineOfSightBlock);
				gate = Gates.i.getGateAtCoord(coord);
				if (gate != null) break;
			}
		}
		
		if (gate == null)
		{
			this.msg("<b>You are not looking at any gate.");
		}
		else
		{
			gme.setSelectedGate(gate);
			this.msg("<i>Selected gate "+gate.getIdNameStringLong());
		}
	}
}