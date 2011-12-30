package com.massivecraft.massivegates.event;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;


public class GateListener extends CustomEventListener
{
    @Override
    public void onCustomEvent(Event event)
    {
        if(event instanceof GateLoadEvent)
        {
        	onGateLoad((GateLoadEvent) event);
        }
        else if(event instanceof GateSaveEvent)
        {
        	onGateSave((GateSaveEvent) event);
        }
        else if(event instanceof GateCreateEvent)
        {
        	onGateCreate((GateCreateEvent) event);
        }
        else if(event instanceof GateDeleteEvent)
        {
        	onGateDelete((GateDeleteEvent) event);
        }
        else if(event instanceof GateOpenEvent)
        {
        	onGateOpen((GateOpenEvent) event);
        }
        else if(event instanceof GateCloseEvent)
        {
        	onGateClose((GateCloseEvent) event);
        }
        else if(event instanceof GateUseEvent)
        {
        	onGateUse((GateUseEvent) event);
        }
        else if(event instanceof GatePlayerWalkEvent)
        {
        	onGatePlayerWalk((GatePlayerWalkEvent) event);
        }
    }
    
    public void onGateLoad(GateLoadEvent event) {}
    
    public void onGateSave(GateSaveEvent event) {}
    
    public void onGateCreate(GateCreateEvent event) {}
    
    public void onGateDelete(GateDeleteEvent event) {}
    
    public void onGateOpen(GateOpenEvent event) {}
    
    public void onGateClose(GateCloseEvent event) {}
    
    public void onGateUse(GateUseEvent event) {}
    
    public void onGatePlayerWalk(GatePlayerWalkEvent event) {}
    
}
