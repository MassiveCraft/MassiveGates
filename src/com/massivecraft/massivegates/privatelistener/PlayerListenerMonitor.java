package com.massivecraft.massivegates.privatelistener;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkType;

public class PlayerListenerMonitor extends PlayerListener
{
	P p;
	public PlayerListenerMonitor(P p)
	{
		this.p = p;
	}
	
	@Override
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if (event.isCancelled()) return;
		
		Block blockFrom = event.getFrom().getBlock();
		Block blockTo = event.getTo().getBlock();
		
		if (blockFrom.equals(blockTo)) return;
		
		WorldCoord3 coordFrom = new WorldCoord3(blockFrom);
		WorldCoord3 coordTo = new WorldCoord3(blockTo);
		
		Gate gateFrom = Gates.i.getGateAtContentCoord(coordFrom);
		Gate gateTo = Gates.i.getGateAtContentCoord(coordTo);
		
		GatePlayerWalkEvent gateEvent;
		
		if (gateFrom != null & gateFrom == gateTo)
		{
			gateEvent = new GatePlayerWalkEvent(gateFrom, gateTo, GatePlayerWalkType.WITHIN);
			Bukkit.getPluginManager().callEvent(gateEvent);
			return;
		}
		
		if (gateFrom != null)
		{
			// OUT
			gateEvent = new GatePlayerWalkEvent(gateFrom, gateTo, GatePlayerWalkType.OUT);
			Bukkit.getPluginManager().callEvent(gateEvent);
		}
		
		if (gateTo != null)
		{
			// INTO
			gateEvent = new GatePlayerWalkEvent(gateFrom, gateTo, GatePlayerWalkType.INTO);
			Bukkit.getPluginManager().callEvent(gateEvent);
		}
	}
}
