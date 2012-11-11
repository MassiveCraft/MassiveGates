package com.massivecraft.massivegates.cmd;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.massivecraft.massivegates.Const;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.util.VisualizeUtil;
import com.massivecraft.mcore5.PS;
import com.massivecraft.mcore5.cmd.req.ReqHasPerm;
import com.massivecraft.mcore5.cmd.req.ReqIsPlayer;
import com.massivecraft.mcore5.util.SmokeUtil;

public class CmdGateEditThat extends GateCommand
{
	public CmdGateEditThat()
	{
		super();
		this.addAliases("that");
		this.addRequiredArg("frame|content|del");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.EDIT_THAT.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		Block thatBlock = gme.getThatBlock(true);
		if (thatBlock == null) return;
		
		Location thatLoc = thatBlock.getLocation();
		PS thatCoord = new PS(thatBlock);
		
		char firstArgChar = this.arg(0).toLowerCase().charAt(0);
		
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
			this.msg("<b>arg must be frame|content|del");
		}
	}
}