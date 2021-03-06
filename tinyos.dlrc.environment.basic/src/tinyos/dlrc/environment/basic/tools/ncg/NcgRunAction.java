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
package tinyos.dlrc.environment.basic.tools.ncg;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;

import tinyos.dlrc.ProjectTOS;
import tinyos.dlrc.environment.basic.tools.BaseRunAction;
import tinyos.dlrc.utility.XReadStack;

public class NcgRunAction extends BaseRunAction{
    
    @Override
    protected IResource getFileOrProject( ISelection selection ){
        return NcgUtility.getFileOrProject( selection );
    }
    
    @Override
    protected void run( ProjectTOS project, XReadStack xml ) throws CoreException, IOException{
        NcgSetting setting = new NcgSetting( project );
        if( xml.search( "setting" ) ){
            setting.read( xml );
        }
        xml.pop();
        
        NcgUtility.executeNCG( setting );
    }
}
