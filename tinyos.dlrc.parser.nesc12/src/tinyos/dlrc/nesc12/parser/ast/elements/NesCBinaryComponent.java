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
package tinyos.dlrc.nesc12.parser.ast.elements;

import java.util.Map;

import tinyos.dlrc.ep.parser.inspection.INesCBinaryComponent;
import tinyos.dlrc.nesc12.ep.BindingResolver;
import tinyos.dlrc.nesc12.ep.nodes.BinaryComponentModelNode;
import tinyos.dlrc.nesc12.parser.ast.elements.types.GenericType;

/**
 * Represents a binary component.
 * @author Benjamin Sigg
 */
public class NesCBinaryComponent extends NesCComponent implements INesCBinaryComponent {
    private BinaryComponentModelNode raw;
    
    public NesCBinaryComponent( BinaryComponentModelNode raw, BindingResolver bindings ){
        super( "Binary Component", raw, bindings );
        this.raw = raw;
    }
    
    public NesCBinaryComponent( BinaryComponentModelNode raw, BindingResolver bindings, Map<GenericType, Type> replacements ){
        super( "Binary Component", raw, bindings, replacements );
        this.raw = raw;
    }
    
    @Override
    protected NesCComponent createComponent( Map<GenericType, Type> generics ){
        return new NesCBinaryComponent( raw, bindings, generics );
    }
}
