package com.massivecraft.massivegates.privatelistener;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPhysicsEvent;
//import org.bukkit.event.block.BlockRedstoneEvent;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;

public class PluginBlockListener extends BlockListener
{
	public P p;
	public WorldCoord3 coord;
	public PluginBlockListener(P p)
	{
		this.p = p;
		this.coord = new WorldCoord3();
	}
	
	@Override
	public void onBlockPhysics(BlockPhysicsEvent event)
	{
		Block block = event.getBlock();
		this.coord.load(block);
		
		Gate gate = Gates.i.getGateAtFrameCoord(coord);
		if (gate == null) return;
		
		if (block.isBlockIndirectlyPowered())
		{
			gate.powerAdd(this.coord);
		}
		else
		{
			gate.powerRemove(this.coord);
		}
	}
}
