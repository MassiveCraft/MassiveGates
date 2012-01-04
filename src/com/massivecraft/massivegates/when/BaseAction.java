package com.massivecraft.massivegates.when;

public abstract class BaseAction implements Action
{
	protected final String id;
	@Override public String getId() { return id; }
	
	protected final String name;
	@Override public String getName() { return name; }
	
	protected final String desc;
	@Override public String getDesc() { return desc; }
	
	protected BaseAction(final String id, final String name, final String desc)
	{
		this.id = id;
		this.name = name;
		this.desc = desc;
	}
	
	@Override
	public boolean equals(Object that)
	{
		if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        return this.getId().equals(((BaseAction)that).getId());
	}
	
	@Override
	public int hashCode()
	{
		return this.getId().hashCode();
	}
}
