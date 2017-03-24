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
	String getId();
	
	//  An nice name for the action
	String getName();
	
	// A description of the action
	String getDesc();
	
	// Perform the action
	void perform(String arg, Gate gate, Entity entity, Cancellable cancellable);
	
	// Check the arg to see if it looks ok. Returns errors.
	List<String> checkArg(String arg);
	
}
