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

import tinyos.dlrc.environment.basic.path.IPathRequest;
import tinyos.dlrc.environment.basic.path.IPathSet;
import tinyos.dlrc.environment.basic.progress.ICancellation;

/**
 * Some algorithm collecting a set of files.
 * @author Benjamin Sigg
 */
public interface ICollectStep{
	public String getName();
	
    /**
     * Collects all files that are reachable and stores them using
     * {@link IPathSet#store(java.io.File)}.
     * @param request where to search the files
     * @param paths the storage
     * @param cancellation to cancel this operation
     */
    public void collect( IPathRequest request, IPathSet paths, ICancellation cancellation );
}
