package com.massivecraft.massivegates.privatelistener.alterimpl;

import org.bukkit.block.Block;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.privatelistener.alterabs.GateAlterMonitorEntityListener;

public class GateAlterMonitorFrameEntityListener extends GateAlterMonitorEntityListener
{
	public GateAlterMonitorFrameEntityListener(P p)
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