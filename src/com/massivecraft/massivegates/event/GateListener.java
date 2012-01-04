package com.massivecraft.massivegates.event;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;



public class GateListener extends CustomEventListener
{
    @Override
    public void onCustomEvent(Event event)
    {
    	if (event instanceof GateAfterTeleportEvent)
        {
        	this.onGateAfterTeleport((GateAfterTeleportEvent) event);
        }
    	else if (event instanceof GateAlterEvent)
        {
        	this.onGateAlter((GateAlterEvent) event);
        }
    	else if(event instanceof GateAttachEvent)
        {
        	onGateAttach((GateAttachEvent) event);
        }
    	else if(event instanceof GateBeforeTeleportEvent)
        {
    		onGateBeforeTeleport((GateBeforeTeleportEvent) event);
        }
    	else if(event instanceof GateCloseEvent)
        {
        	onGateClose((GateCloseEvent) event);
        }
    	else if(event instanceof GateDetachEvent)
        {
    		onGateDetach((GateDetachEvent) event);
        }
		else if(event instanceof GateOpenEvent)
		{
			onGateOpen((GateOpenEvent) event);
		}
		else if(event instanceof GatePlayerWalkEvent)
        {
        	onGatePlayerWalk((GatePlayerWalkEvent) event);
        }
		else if(event instanceof GateSaveEvent)
        {
        	onGateSave((GateSaveEvent) event);
        }
    	else if(event instanceof GateUseEvent)
        {
        	onGateUse((GateUseEvent) event);
        }
    }
    
    public void onGateAfterTeleport(GateAfterTeleportEvent event) {};
    public void onGateAlter(GateAlterEvent event) {}
    public void onGateAttach(GateAttachEvent event) {}
    public void onGateBeforeTeleport(GateBeforeTeleportEvent event) {};
    public void onGateClose(GateCloseEvent event) {}
    public void onGateDetach(GateDetachEvent event) {}
    public void onGateOpen(GateOpenEvent event) {}
    public void onGatePlayerWalk(GatePlayerWalkEvent event) {}
    public void onGateSave(GateSaveEvent event) {}
    public void onGateUse(GateUseEvent event) {}
    
    //public void onGateCreate(GateAttachEvent event) {}
    
}
