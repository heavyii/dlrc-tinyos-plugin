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
package tinyos.dlrc.ep.figures;

import tinyos.dlrc.ep.parser.IASTFigure;

public final class ExpandCallbackUtility {
    private ExpandCallbackUtility(){
        // nothing
    }

    public static IExpandCallback forward( final IASTFigure figure, final IExpandCallback callback ){
        if( callback == null )
            return null;

        return new IExpandCallback(){
            public void canceled( IASTFigure miss ){
                callback.canceled( figure );
            }

            public void expanded( IASTFigure miss ){
                callback.expanded( figure );
            }
        };
    }
}
