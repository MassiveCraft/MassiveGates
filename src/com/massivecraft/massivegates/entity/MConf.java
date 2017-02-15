package com.massivecraft.massivegates.entity;

import java.util.List;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

@EditorName("config")
public class MConf extends Entity<MConf>
{
	// -------------------------------------------- //
	// META
	// -------------------------------------------- //
	
	protected static transient MConf i;
	public static MConf get() { return i; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// Aliases
	public List<String> aliasesGate = MUtil.list("g");
	public List<String> aliasesGateNew = MUtil.list("new", "create");
	public List<String> aliasesGateDelete = MUtil.list("delete", "rm", "remove");
	public List<String> aliasesGateList = MUtil.list("list");
	public List<String> aliasesGateSel = MUtil.list("select", "use");
	
	public List<String> aliasesGateName = MUtil.list("name");
	public List<String> aliasesGateNameSet = MUtil.list("set");
	public List<String> aliasesGateNameRemove = MUtil.list("remove");
	
	public List<String> aliasesGateOpen = MUtil.list("open");
	public List<String> aliasesGateOpenSet = MUtil.list("set");
	
	public List<String> aliasesGateEdit = MUtil.list("edit");
	public List<String> aliasesGateEditThat = MUtil.list("that");
	public List<String> aliasesGateEditFlood = MUtil.list("flood");
	public List<String> aliasesGateEditClear = MUtil.list("clear");
	
	public List<String> aliasesGateMato = MUtil.list("mo", "mato");
	public List<String> aliasesGateMatc = MUtil.list("mc", "matc");
	public List<String> aliasesGateSee = MUtil.list("see");
	
	public List<String> aliasesGateTarget = MUtil.list("target");
	public List<String> aliasesGateTargetHere = MUtil.list("here", "set");
	public List<String> aliasesGateTargetGate = MUtil.list("gate");
	public List<String> aliasesGateTargetGoto = MUtil.list("goto");
	public List<String> aliasesGateTargetRemove = MUtil.list("remove");
	
	public List<String> aliasesGateExit = MUtil.list("exit");
	public List<String> aliasesGateExitHere = MUtil.list("here", "set");
	public List<String> aliasesGateExitGoto = MUtil.list("goto");
	public List<String> aliasesGateExitRemove = MUtil.list("rm", "remove");
	
	public List<String> aliasesGateTa = MUtil.list("ta");
	public List<String> aliasesGateTaAlt = MUtil.list("alt");
	public List<String> aliasesGateTaList = MUtil.list("list");
	public List<String> aliasesGateTaAdd = MUtil.list("add");
	public List<String> aliasesGateTaDel = MUtil.list("del");
	
	public List<String> aliasesGateFx = MUtil.list("fx");
	public List<String> aliasesGateFxAlt = MUtil.list("alt");
	public List<String> aliasesGateFxTest = MUtil.list("test");
	
	public List<String> aliasesGateConfig = MUtil.list("config");
	public List<String> aliasesGateVersion = MUtil.list("v", "version");
	
	public boolean disableVanillaGates = false;
	
	public int floodFillLimit = 200;
	
	public int lineOfSightLimit = 100;

}
