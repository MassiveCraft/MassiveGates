package com.massivecraft.massivegates;

import com.massivecraft.massivecore.Identified;
import com.massivecraft.massivecore.util.PermissionUtil;
import org.bukkit.permissions.Permissible;

public enum Perm implements Identified
{
	// -------------------------------------------- //
	// ENUM
	// -------------------------------------------- //
	
	ACTION_CHAT("action.mgcore_chat"),
	ACTION_CLOSE("action.mgcore_close"),
	ACTION_CMD("action.mgcore_cmd"),
	ACTION_FXE("action.mgcore_fxe"),
	ACTION_FXG("action.mgcore_fxg"),
	ACTION_MSG("action.mgcore_msg"),
	ACTION_OPEN("action.mgcore_open"),
	ACTION_USE("action.mgcore_use"),
	ACTION_USE_FORCED("action.mgcore_use_forced"),
	DELETE("delete"),
	EDIT("edit"),
	EDIT_CLEAR("edit.clear"),
	EDIT_FLOOD("edit.flood"),
	EDIT_THAT("edit.that"),
	EXIT("exit"),
	EXIT_GET("exit.get"),
	EXIT_HERE("exit.here"),
	EXIT_GOTO("exit.goto"),
	EXIT_REMOVE("exit.remove"),
	FX("fx"),
	FX_ALT("fx.alt"),
	FX_TEST("fx.test"),
	LIST("list"),
	MATC("matc"),
	MATO("mato"),
	NAME("name"),
	NAME_GET("name.get"),
	NAME_SET("name.set"),
	NAME_REMOVE("name.remove"),
	NEW("new"),
	OPEN("open"),
	OPEN_GET("open.get"),
	OPEN_SET("open.set"),
	SEE("see"),
	SELECT("select"),
	SIMPLE_CREATE("simple.create"),
	SIMPLE_DESTROY("simple.destroy"),
	SIMPLE_USE("simple.use"),
	TARGET("target"),
	TARGET_GET("target.get"),
	TARGET_GATE("target.gate"),
	TARGET_HERE("target.here"),
	TARGET_GOTO("target.goto"),
	TARGET_REMOVE("target.remove"),
	TA("ta"),
	TA_ADD("ta.add"),
	TA_ALT("ta.alt"),
	TA_DEL("ta.del"),
	TA_LIST("ta.list"),
	CONFIG("config"),
	VERSION("version"),
	
	// END OF LIST
	;
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public final String id;
	@Override public String getId() { return this.id; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	Perm(final String permissionIdTail)
	{
		this.id = "massivegates." + permissionIdTail;
	}
	
	// -------------------------------------------- //
	// HAS
	// -------------------------------------------- //
	
	public boolean has(Permissible permissible, boolean verboose)
	{
		return PermissionUtil.hasPermission(permissible, this, verboose);
	}
	
	public boolean has(Permissible permissible)
	{
		return PermissionUtil.hasPermission(permissible, this);
	}
	
}
