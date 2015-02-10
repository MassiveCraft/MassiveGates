package com.massivecraft.massivegates;

import java.util.List;

import org.bukkit.Bukkit;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivegates.cmd.CmdGate;
import com.massivecraft.massivegates.engine.GateEngine;
import com.massivecraft.massivegates.engine.MainEngine;
import com.massivecraft.massivegates.engine.ProtectionEngine;
import com.massivecraft.massivegates.entity.GSenderColl;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.entity.MConfColl;
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

public class MassiveGates extends MassivePlugin
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //

	private static MassiveGates i;
	public static MassiveGates get() { return i; }
	public MassiveGates() { MassiveGates.i = this; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Command
	public CmdGate cmdGate = new CmdGate() { @Override public List<String> getAliases() { return MConf.get().aliasesG; } };
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnable()
	{
		if ( ! preEnable()) return;
		
		// Init collections
		MConfColl.get().init();
		GateColl.get().init();
		GSenderColl.get().init();
		
		// Register Triggers & Actions
		GateColl.get().registerAction(ActionUse.get());
		GateColl.get().registerAction(ActionUseForced.get());
		GateColl.get().registerAction(ActionOpen.get());
		GateColl.get().registerAction(ActionClose.get());
		GateColl.get().registerAction(ActionFxe.get());
		GateColl.get().registerAction(ActionFxg.get());
		GateColl.get().registerAction(ActionMsg.get());
		GateColl.get().registerAction(ActionCmd.get());
		GateColl.get().registerAction(ActionChat.get());
		GateColl.get().registerAction(ActionSetVelocity.get());
		GateColl.get().registerAction(ActionReqPerm.get());
		
		GateColl.get().registerTrigger(TriggerEnter.get());
		GateColl.get().registerTrigger(TriggerBtp.get());
		GateColl.get().registerTrigger(TriggerAtp.get());
		GateColl.get().registerTrigger(TriggerUse.get());
		GateColl.get().registerTrigger(TriggerOpen.get());
		GateColl.get().registerTrigger(TriggerClose.get());
		GateColl.get().registerTrigger(TriggerPowerOn.get());
		GateColl.get().registerTrigger(TriggerPowerOff.get());
		GateColl.get().registerTrigger(TriggerFrameAlter.get());
		GateColl.get().registerTriggers(TriggerHour.triggerHours.values());
		
		// Add Base Commands
		cmdGate.register(this);
		
		// Register Engines
		MainEngine.get().activate();
		ProtectionEngine.get().activate();
		GateEngine.get().activate();
		
		// Register the HourTriggingTask
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HourTriggingTask(), Const.hourTriggingTaskTicks, Const.hourTriggingTaskTicks);
		
		// Connect to RubberBand
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "RubberBand");
		
		postEnable();
	}
	
}
