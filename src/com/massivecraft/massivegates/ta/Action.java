package com.massivecraft.massivegates.ta;

import com.massivecraft.massivecore.Named;
import com.massivecraft.massivegates.entity.Gate;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import java.util.List;

public interface Action extends Named
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// The id should be unique and never change
	public String getId();
	
	//  An nice name for the action
	public String getName();
	
	// A description of the action
	public String getDesc();
	
	// Perform the action
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable);
	
	// Check the arg to see if it looks ok. Returns errors.
	public List<String> checkArg(String arg);
	
}
