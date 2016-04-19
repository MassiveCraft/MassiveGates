package com.massivecraft.massivegates;


import org.bukkit.Bukkit;

import com.massivecraft.massivecore.MassivePlugin;
import com.massivecraft.massivegates.cmd.CmdGate;
import com.massivecraft.massivegates.engine.EngineGate;
import com.massivecraft.massivegates.engine.EngineMain;
import com.massivecraft.massivegates.engine.EngineProtection;
import com.massivecraft.massivegates.entity.GSenderColl;
import com.massivecraft.massivegates.entity.GateColl;
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
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void onEnableInner()
	{
		// Activate
		this.activate(
			// Coll
			MConfColl.class,
			GateColl.class,
			GSenderColl.class,
		
			// Engine
			EngineMain.class,
			EngineProtection.class,
			EngineGate.class,
			
			// Command
			CmdGate.class
		);

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
		
		// Register the HourTriggingTask
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new HourTriggingTask(), Const.hourTriggingTaskTicks, Const.hourTriggingTaskTicks);
	}
	
}
