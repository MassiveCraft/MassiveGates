package com.massivecraft.massivegates.privatelistener.alterimpl;

import org.bukkit.block.Block;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.privatelistener.alterabs.GateAlterMonitorPlayerListener;

public class GateAlterMonitorFramePlayerListener extends GateAlterMonitorPlayerListener
{
	public GateAlterMonitorFramePlayerListener(P p)
	{
		super(p);
	}

	@Override
	public Gate getGateAt(Block block)
	{
		this.coord.load(block);
		return Gates.i.getGateAtFrameCoord(this.coord);
	}
	
}