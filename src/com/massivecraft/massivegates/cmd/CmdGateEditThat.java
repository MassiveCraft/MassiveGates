package com.massivecraft.massivegates.cmd;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.massivecraft.massivecore.cmd.arg.ARString;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.util.SmokeUtil;
import com.massivecraft.massivegates.Const;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.util.VisualizeUtil;

public class CmdGateEditThat extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateEditThat()
	{
		// Aliases
		this.addAliases("that");
		
		// Args
		this.addArg(ARString.get(), "frame|content|del");
		
		// Requirements
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.EDIT_THAT.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		Block thatBlock = gsender.getThatBlock(true);
		if (thatBlock == null) return;
		
		Location thatLoc = thatBlock.getLocation();
		PS thatCoord = PS.valueOf(thatBlock);
		
		char firstArgChar = this.argAt(0).toLowerCase().charAt(0);
		
		// Apply
		if (firstArgChar == 'f')
		{
			gate.addFrame(thatCoord);
			VisualizeUtil.addLocation(me, thatLoc, Const.visFrame);
			SmokeUtil.spawnCloudSimple(thatLoc);
		}
		else if (firstArgChar == 'c')
		{
			gate.addContent(thatCoord);
			VisualizeUtil.addLocation(me, thatLoc, Const.visContent);
			SmokeUtil.spawnCloudSimple(thatLoc);	
		}
		else if (firstArgChar == 'd')
		{
			gate.delContent(thatCoord);
			gate.delFrame(thatCoord);
			SmokeUtil.spawnCloudSimple(thatLoc);	
		}
		else
		{
			// Inform
			this.msg("<b>Arg must be frame|content|del .");
		}
	}
	
}
