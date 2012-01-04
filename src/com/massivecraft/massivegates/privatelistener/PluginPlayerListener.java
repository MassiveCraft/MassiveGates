package com.massivecraft.massivegates.privatelistener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.massivegates.P;
import com.massivecraft.massivegates.WorldCoord3;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkType;
import com.massivecraft.massivegates.util.VisualizeUtil;

public class PluginPlayerListener extends PlayerListener
{
	P p;
	public PluginPlayerListener(P p)
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
		
		Player player = event.getPlayer();
		
		VisualizeUtil.clear(player);
		
		WorldCoord3 coordFrom = new WorldCoord3(blockFrom);
		WorldCoord3 coordTo = new WorldCoord3(blockTo);
		
		Gate gateFrom = Gates.i.getGateAtContentCoord(coordFrom);
		Gate gateTo = Gates.i.getGateAtContentCoord(coordTo);
		
		GatePlayerWalkEvent gateEvent;
		
		if (gateFrom != null & gateFrom == gateTo)
		{
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.WITHIN);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
			
			return;
		}
		
		if (gateFrom != null)
		{
			// OUT
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.OUT);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
		}
		
		if (gateTo != null)
		{
			// INTO
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.INTO);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
			/*else if (gateTo.isOpen())
			{
				// Trigger usage of the gate :)
				gateTo.use(event.getPlayer());
			}*/
		}
	}
	
	@Override
	public void onPlayerPortal(PlayerPortalEvent event)
	{
		event.setCancelled(false);
	}
}
