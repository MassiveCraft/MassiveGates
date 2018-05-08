package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.requirement.RequirementIsPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivecore.util.SmokeUtil;
import com.massivecraft.massivegates.Const;
import com.massivecraft.massivegates.GateStructureChange;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmd.type.TypeGateStructureChange;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.util.VisualizeUtil;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

public class CmdGateEditThat extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateEditThat()
	{
		// Parameters
		this.addParameter(TypeGateStructureChange.get(), "frame|content|del");
		
		// Requirements
		this.addRequirements(RequirementIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(RequirementHasPerm.get(Perm.EDIT_THAT.id));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public List<String> getAliases()
	{
		return MConf.get().aliasesGateEditThat;
	}
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		Block thatBlock = gsender.getThatBlock(true);
		if (thatBlock == null) return;
		
		Location thatLoc = thatBlock.getLocation();
		PS thatCoord = PS.valueOf(thatBlock);
		
		GateStructureChange edit = this.readArg();
		
		// Apply
		if (edit == GateStructureChange.FRAME)
		{
			gate.addFrame(thatCoord);
			VisualizeUtil.addLocation(me, thatLoc, Const.visFrame);
			SmokeUtil.spawnCloudSimple(thatLoc);
		}
		else if (edit == GateStructureChange.CONTENT)
		{
			gate.addContent(thatCoord);
			VisualizeUtil.addLocation(me, thatLoc, Const.visContent);
			SmokeUtil.spawnCloudSimple(thatLoc);	
		}
		else if (edit == GateStructureChange.DELETE)
		{
			gate.delContent(thatCoord);
			gate.delFrame(thatCoord);
			SmokeUtil.spawnCloudSimple(thatLoc);	
		}
	}
	
}
