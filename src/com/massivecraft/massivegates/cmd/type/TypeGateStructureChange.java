package com.massivecraft.massivegates.cmd.type;

import com.massivecraft.massivecore.command.type.enumeration.TypeEnum;
import com.massivecraft.massivegates.GateStructureChange;

public class TypeGateStructureChange extends TypeEnum<GateStructureChange>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeGateStructureChange i = new TypeGateStructureChange();
	public static TypeGateStructureChange get() { return i; }
	public TypeGateStructureChange() { super(GateStructureChange.class); }
	
}
