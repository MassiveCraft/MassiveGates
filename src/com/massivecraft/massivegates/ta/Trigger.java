package com.massivecraft.massivegates.ta;

import com.massivecraft.massivecore.Named;

public interface Trigger extends Named
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// The id should be unique and never change
	String getId();
	
	// An nicename for the trigger
	String getName();
	
	// A description of the trigger
	String getDesc();
	
}
