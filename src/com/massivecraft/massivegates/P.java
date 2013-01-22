package com.massivecraft.massivegates;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import com.massivecraft.massivegates.cmd.CmdGate;
import com.massivecraft.massivegates.event.GateAfterTeleportEvent;
import com.massivecraft.massivegates.ta.ActionChat;
import com.massivecraft.massivegates.ta.ActionClose;
import com.massivecraft.massivegates.ta.ActionCmd;
import com.massivecraft.massivegates.ta.ActionFxe;
import com.massivecraft.massivegates.ta.ActionFxg;
import com.massivecraft.massivegates.ta.ActionMsg;
import com.massivecraft.massivegates.ta.ActionOpen;
import com.massivecraft.massivegates.ta.ActionReqPerm;
import com.massivecraft.massivegates.ta.ActionSetVelocity;
import com.massivecraft.massivegates.ta.ActionUse;
import com.massivecraft.massivegates.ta.ActionUseForced;
import com.massivecraft.massivegates.ta.TriggerAtp;
import com.massivecraft.massivegates.ta.TriggerBtp;
import com.massivecraft.massivegates.ta.TriggerClose;
import com.massivecraft.massivegates.ta.TriggerEnter;
import com.massivecraft.massivegates.ta.TriggerFrameAlter;
import com.massivecraft.massivegates.ta.TriggerHour;
import com.massivecraft.massivegates.ta.TriggerOpen;
import com.massivecraft.massivegates.ta.TriggerPowerOff;
import com.massivecraft.massivegates.ta.TriggerPowerOn;
import com.massivecraft.massivegates.ta.TriggerUse;
import com.massivecraft.mcore5.MPlugin;

public class P extends MPlugin
{
	// Our single plugin instance
	public static P p;
	
	// Listener
	protected TheListener theListener;
	
	// Command
	public CmdGate cmdGate;
	
	// after teleport todo
	public Map<Entity, GateAfterTeleportEvent> afterTeleportTodo = new HashMap<Entity, GateAfterTeleportEvent>(); 
	
	public P()
	{
		P.p = this;
	}
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Load Conf from disk
		ConfServer.i.load();
		
		// Init collections
		GateColl.i.init();
		GSenderColl.i.init();
		
		// Register Triggers & Actions
		GateColl.i.registerAction(ActionUse.getInstance());
		GateColl.i.registerAction(ActionUseForced.getInstance());
		GateColl.i.registerAction(ActionOpen.getInstance());
		GateColl.i.registerAction(ActionClose.getInstance());
		GateColl.i.registerAction(ActionFxe.getInstance());
		GateColl.i.registerAction(ActionFxg.getInstance());
		GateColl.i.registerAction(ActionMsg.getInstance());
		GateColl.i.registerAction(ActionCmd.getInstance());
		GateColl.i.registerAction(ActionChat.getInstance());
		GateColl.i.registerAction(ActionSetVelocity.getInstance());
		GateColl.i.registerAction(ActionReqPerm.getInstance());
		
		GateColl.i.registerTrigger(TriggerEnter.getInstance());
		GateColl.i.registerTrigger(TriggerBtp.getInstance());
		GateColl.i.registerTrigger(TriggerAtp.getInstance());
		GateColl.i.registerTrigger(TriggerUse.getInstance());
		GateColl.i.registerTrigger(TriggerOpen.getInstance());
		GateColl.i.registerTrigger(TriggerClose.getInstance());
		GateColl.i.registerTrigger(TriggerPowerOn.getInstance());
		GateColl.i.registerTrigger(TriggerPowerOff.getInstance());
		GateColl.i.registerTrigger(TriggerFrameAlter.getInstance());
		GateColl.i.registerTriggers(TriggerHour.triggerHours.values());
		
		// Add Base Commands
		this.cmdGate = new CmdGate();
		this.cmdGate.register(this);	
		
		// Register events
		this.theListener = new TheListener(this);
		Bukkit.getServer().getPluginManager().registerEvents(this.theListener, this);
		
		// Register the HourTriggingTask
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HourTriggingTask(), Const.hourTriggingTaskTicks, Const.hourTriggingTaskTicks);
		
		// Connect to RubberBand
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "RubberBand");
		
		postEnable();
	}
	
}