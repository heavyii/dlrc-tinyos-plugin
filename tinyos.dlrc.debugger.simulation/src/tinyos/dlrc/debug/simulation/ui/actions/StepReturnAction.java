package tinyos.dlrc.debug.simulation.ui.actions;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jface.viewers.Viewer;

import tinyos.dlrc.debug.simulation.manager.ISimulationManager;
import tinyos.dlrc.debug.simulation.manager.cooja.Mote;

public class StepReturnAction extends AbstractSimulatorAction 
{

	public StepReturnAction(Viewer viewer) 
	{
		super("Step Return", DebugUITools.getImageDescriptor(IDebugUIConstants.IMG_LCL_DISCONNECT));
		setViewer(viewer);
	}
	
	public void run()
	{
		Object selectedObject = getSelectedObject();
		ISimulationManager manager = getManager(getSelectedObject());
		
		try 
		{
			if(selectedObject instanceof IStackFrame)
			{
				manager.stepMote((Mote)selectedObject);
				((IStackFrame)selectedObject).stepReturn();
				return;
			}
			if(selectedObject instanceof Mote)
			{
				manager.stepMote((Mote)selectedObject);
				((Mote)selectedObject).getTarget().getThreads()[0].stepReturn();
				return;
			}
		} catch (DebugException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {	}
		
		viewer.setSelection(null);
	}

}
