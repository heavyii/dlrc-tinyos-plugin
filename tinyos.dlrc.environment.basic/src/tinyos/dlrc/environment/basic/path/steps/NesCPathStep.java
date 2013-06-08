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
package tinyos.dlrc.environment.basic.path.steps;

import tinyos.dlrc.Debug;
import tinyos.dlrc.environment.basic.path.IPathRequest;
import tinyos.dlrc.environment.basic.path.IPathSet;
import tinyos.dlrc.environment.basic.progress.ICancellation;

public class NesCPathStep implements ISearchStep{
    boolean warned = false;
    
	public String getName(){
		return "NesCPathStep";
	}
    
    public void collect( IPathRequest request, IPathSet paths, ICancellation cancellation ) {
        // String path = System.getenv( "NESCPATH" );
        
        if( !warned ){
            warned = true;
            Debug.warning( "ignoring NESCPATH" );
        }
    }
    
    public void locate( String fileName, IPathRequest request, IPathSet paths, ICancellation cancellation ) {
        if( !warned ){
            warned = true;
            Debug.warning( "ignoring NESCPATH" );
        }
    }
}
