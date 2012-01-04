package com.massivecraft.massivegates;

import org.bukkit.command.CommandSender;

import com.massivecraft.mcore1.util.Perm;

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
	LIST("list"),
	MATC("matc"),
	MATO("mato"),
	NEW("new"),
	OPEN("open"),
	SEE("see"),
	SEL("sel"),
	TARGET_GET("target.get"),
	TARGET_GATE("target.gate"),
	TARGET_HERE("target.here"),
	TARGET_GOTO("target.goto"),
	WHEN_ADD("when.add"),
	WHEN_ALT("when.alt"),
	WHEN_DEL("when.del"),
	WHEN_LIST("when.list"),
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
