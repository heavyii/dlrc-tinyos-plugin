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
package tinyos.dlrc.environment.basic.test;

import java.io.File;
import java.io.OutputStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import tinyos.dlrc.environment.basic.AbstractEnvironment;
import tinyos.dlrc.environment.basic.TinyOSAbstractEnvironmentPlugin;

public class TosdirValid extends AbstractTest{
	private AbstractEnvironment environment;
	
	public TosdirValid( AbstractEnvironment environment ){
		this.environment = environment;
		
		setName( "Tosdir valid" );
		setDescription( "Checks whether TOSDIR points to an existing location" );
	}
	
	public IStatus run(OutputStream out, OutputStream err, IProgressMonitor monitor) throws Exception {
		String path = environment.getPathManager().getTosDirectoryPath();
		if( path == null ){
			return new Status( IStatus.ERROR, TinyOSAbstractEnvironmentPlugin.PLUGIN_ID, "TOSDIR not set" );
		}
		
		File file = environment.modelToSystem( path );
		if( file == null || !file.exists() ){
			return new Status( IStatus.ERROR, TinyOSAbstractEnvironmentPlugin.PLUGIN_ID, "TOSDIR does not point to an existing directory" );
		}
		if( !file.isDirectory() ){
			return new Status( IStatus.ERROR, TinyOSAbstractEnvironmentPlugin.PLUGIN_ID, "TOSDIR does not point to a directory but another file" );
		}
		if( !file.canRead() ){
			return new Status( IStatus.ERROR, TinyOSAbstractEnvironmentPlugin.PLUGIN_ID, "TOSDIR points to a directory which is not readable" );
		}
		
		return Status.OK_STATUS;
	}
}
