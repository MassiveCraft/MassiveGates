package com.massivecraft.massivegates;

import org.bukkit.Bukkit;
import org.bukkit.Effect;

import com.massivecraft.massivegates.adapter.LocWrapAdapter;
import com.massivecraft.massivegates.adapter.WorldCoord3Adapter;
import com.massivecraft.massivegates.cmd.CmdGate;
import com.massivecraft.massivegates.cmdarg.AHAction;
import com.massivecraft.massivegates.cmdarg.AHEffect;
import com.massivecraft.massivegates.cmdarg.AHGate;
import com.massivecraft.massivegates.cmdarg.AHTrigger;
import com.massivecraft.massivegates.ta.Action;
import com.massivecraft.massivegates.ta.ActionChat;
import com.massivecraft.massivegates.ta.ActionClose;
import com.massivecraft.massivegates.ta.ActionCmd;
import com.massivecraft.massivegates.ta.ActionFxe;
import com.massivecraft.massivegates.ta.ActionFxg;
import com.massivecraft.massivegates.ta.ActionMsg;
import com.massivecraft.massivegates.ta.ActionOpen;
import com.massivecraft.massivegates.ta.ActionUse;
import com.massivecraft.massivegates.ta.ActionUseForced;
import com.massivecraft.massivegates.ta.Trigger;
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
import com.massivecraft.mcore2.MPlugin;
import com.massivecraft.mcore2.lib.gson.GsonBuilder;

public class P extends MPlugin
{
	// Our single plugin instance
	public static P p;
	
	// Listener
	protected TheListener theListener;
	
	// Command
	public CmdGate cmdGate;
	
	public P()
	{
		P.p = this;
	}
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		// Register Triggers & Actions
		Gates.i.registerAction(ActionUse.getInstance());
		Gates.i.registerAction(ActionUseForced.getInstance());
		Gates.i.registerAction(ActionOpen.getInstance());
		Gates.i.registerAction(ActionClose.getInstance());
		Gates.i.registerAction(ActionFxe.getInstance());
		Gates.i.registerAction(ActionFxg.getInstance());
		Gates.i.registerAction(ActionMsg.getInstance());
		Gates.i.registerAction(ActionCmd.getInstance());
		Gates.i.registerAction(ActionChat.getInstance());
		
		Gates.i.registerTrigger(TriggerEnter.getInstance());
		Gates.i.registerTrigger(TriggerBtp.getInstance());
		Gates.i.registerTrigger(TriggerAtp.getInstance());
		Gates.i.registerTrigger(TriggerUse.getInstance());
		Gates.i.registerTrigger(TriggerOpen.getInstance());
		Gates.i.registerTrigger(TriggerClose.getInstance());
		Gates.i.registerTrigger(TriggerPowerOn.getInstance());
		Gates.i.registerTrigger(TriggerPowerOff.getInstance());
		Gates.i.registerTrigger(TriggerFrameAlter.getInstance());
		Gates.i.registerTriggers(TriggerHour.triggerHours.values());
		
		// Load Conf from disk
		Conf.load();
		
		// Add Base Commands
		this.cmdGate = new CmdGate();
		this.cmd.addCommand(this.cmdGate);
		
		// Add Argument Handlers
		this.cmd.setArgHandler(Gate.class, AHGate.getInstance());
		this.cmd.setArgHandler(Trigger.class, AHTrigger.getInstance());
		this.cmd.setArgHandler(Action.class, AHAction.getInstance());
		this.cmd.setArgHandler(Effect.class, AHEffect.getInstance());		
		
		// Register events
		this.theListener = new TheListener(this);
		
		// Register the HourTriggingTask
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HourTriggingTask(), Conf.hourTriggingTaskTicks, Conf.hourTriggingTaskTicks);
		
		postEnable();
	}
	
	@Override
	public GsonBuilder getGsonBuilder()
	{
		return super.getGsonBuilder()
		.registerTypeAdapter(WorldCoord3.class, WorldCoord3Adapter.getInstance())
		.registerTypeAdapter(LocWrap.class, LocWrapAdapter.getInstance())
		;
	}
}