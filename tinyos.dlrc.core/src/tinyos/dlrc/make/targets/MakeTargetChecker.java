/*
 * Dlrc 2, NesC development in Eclipse.
 * Copyright (C) 2009 DLRC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Web:  http://tos-ide.ethz.ch
 * Mail: tos-ide@tik.ee.ethz.ch
 */
package tinyos.dlrc.make.targets;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import tinyos.dlrc.TinyOSPlugin;
import tinyos.dlrc.ep.IProjectChecker;
import tinyos.dlrc.ep.IProjectCheckerCallback;
import tinyos.dlrc.make.IMakeTargetListener;
import tinyos.dlrc.make.MakeTarget;
import tinyos.dlrc.make.MakeTargetEvent;
import tinyos.dlrc.make.MakeTargetManager;
import tinyos.dlrc.model.ProjectChecker;

/**
 * Keeps track of the {@link MakeTarget} each project uses and 
 * updates messages related to the target.
 * @author Benjamin Sigg
 */
public class MakeTargetChecker implements IMakeTargetListener, IProjectChecker{
	private MakeTargetManager manager;
	private ProjectChecker checker;
	
	public void connect( ProjectChecker checker ){
		this.checker = checker;
		init();
	}
	
	private void init(){
		manager = TinyOSPlugin.getDefault().getTargetManager();
		manager.addListener( this );
	}
	
	public void targetChanged( MakeTargetEvent event ){
		IProject project = event.getProject();
		if( project == null )
			return;
		
		checker.recheck( project );
	}
	
	public void checkProject( IProject project, IProgressMonitor monitor, IProjectCheckerCallback callback ) throws CoreException{
		monitor.beginTask( "Check make-options", 1 );
		
		IMakeTargetMorpheable morph = manager.getSelectedTarget( project );
		if( morph == null ){
			monitor.done();
			return;
		}
		
		MakeTarget target = morph.toMakeTarget();
		if( target == null ){
			monitor.done();
			return;
		}
		
		IStatus status = target.ready();
		if( status == null ){
			monitor.done();
			return;
		}
		
		switch( status.getSeverity() ){
			case IStatus.ERROR:
				callback.reportError( status.getMessage() );
				break;
			case IStatus.WARNING:
				callback.reportWarning( status.getMessage() );
				break;
			case IStatus.INFO:
				callback.reportInfo( status.getMessage() );
				break;
		}
		
		monitor.done();
	}	
}
