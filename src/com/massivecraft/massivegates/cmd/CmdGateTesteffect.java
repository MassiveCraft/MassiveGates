package com.massivecraft.massivegates.cmd;

import org.bukkit.Effect;
import org.bukkit.Location;

import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.mcore1.cmd.VisibilityMode;
import com.massivecraft.mcore1.cmd.req.ReqIsPlayer;

public class CmdGateTesteffect extends GateCommand
{
	public CmdGateTesteffect()
	{
		super();
		this.addAliases("testeffect");
		this.addRequiredArg("effect");
		this.addOptionalArg("data", "0");
		this.addRequirements(ReqIsPlayer.getInstance());
		this.setVisibilityMode(VisibilityMode.SECRET);
	}
	
	@Override
	public void perform()
	{
		Effect effect = this.argAs(0, Effect.class);
		if (effect == null) return;
		
		Integer data = this.argAs(1, Integer.class, 1);
		if (data == null) return;
		
		Location location = me.getLastTwoTargetBlocks(null, 3).get(1).getLocation();
		location.getWorld().playEffect(location, effect, data);
		
		this.msg("<i>Played effect <h>"+effect+" <i>with data <h>"+data+"<i>.");
	}
}