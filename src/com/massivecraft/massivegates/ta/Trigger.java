package com.massivecraft.massivegates.ta;

import com.massivecraft.massivecore.Named;

public interface Trigger extends Named
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// The id should be unique and never change
	public String getId();
	
	// An nicename for the trigger
	public String getName();
	
	// A description of the trigger
	public String getDesc();
	
}
