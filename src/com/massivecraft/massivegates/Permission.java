package com.massivecraft.massivegates;

import org.bukkit.command.CommandSender;

import com.massivecraft.mcore3.util.Perm;

public enum Permission
{
	SIMPLE_CREATE("simple.create"),
	SIMPLE_DESTROY("simple.destroy"),
	SIMPLE_USE("simple.use"),
	DELETE("delete"),
	EDIT_CLEAR("edit.clear"),
	EDIT_FLOOD("edit.flood"),
	EDIT_THAT("edit.that"),
	EXIT_GET("exit.get"),
	EXIT_HERE("exit.here"),
	EXIT_GOTO("exit.goto"),
	EXIT_REMOVE("exit.remove"),
	FX_ALT("fx.alt"),
	FX_TEST("fx.test"),
	LIST("list"),
	MATC("matc"),
	MATO("mato"),
	NAME_GET("name.get"),
	NAME_SET("name.set"),
	NAME_REMOVE("name.remove"),
	NEW("new"),
	OPEN_GET("open.get"),
	OPEN_SET("open.set"),
	SEE("see"),
	SELECT("select"),
	TARGET_GET("target.get"),
	TARGET_GATE("target.gate"),
	TARGET_HERE("target.here"),
	TARGET_GOTO("target.goto"),
	TARGET_REMOVE("target.remove"),
	TA_ADD("ta.add"),
	TA_ALT("ta.alt"),
	TA_DEL("ta.del"),
	TA_LIST("ta.list"),
	ACTION_CHAT("action.mgcore_chat"),
	ACTION_CLOSE("action.mgcore_close"),
	ACTION_CMD("action.mgcore_cmd"),
	ACTION_FXE("action.mgcore_fxe"),
	ACTION_FXG("action.mgcore_fxg"),
	ACTION_MSG("action.mgcore_msg"),
	ACTION_OPEN("action.mgcore_open"),
	ACTION_USE("action.mgcore_use"),
	ACTION_USE_FORCED("action.mgcore_use_forced"),
	;
	
	public final String node;
	
	Permission(final String permissionNode)
	{
		this.node = "massivegates."+permissionNode;
    }
	
	public boolean has(CommandSender sender, boolean informSenderIfNot)
	{
		return Perm.has(sender, this.node, informSenderIfNot);
	}
	
	public boolean has(CommandSender sender)
	{
		return has(sender, false);
	}
}