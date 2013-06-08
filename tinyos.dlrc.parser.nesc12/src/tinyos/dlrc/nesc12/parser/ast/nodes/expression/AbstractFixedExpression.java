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
package tinyos.dlrc.nesc12.parser.ast.nodes.expression;

import tinyos.dlrc.nesc12.parser.ast.elements.Generic;
import tinyos.dlrc.nesc12.parser.ast.elements.Value;
import tinyos.dlrc.nesc12.parser.ast.nodes.AbstractFixedASTNode;

public abstract class AbstractFixedExpression extends AbstractFixedASTNode implements Expression{
    public AbstractFixedExpression( String name, String... fields ) {
        super( name, fields );
    }

    public Generic resolveGeneric() {
        return resolveConstantValue();
    }
    
    public Value[] resolveConstantValues() {
        if( isResolved( "values" ))
            return resolved( "values" );
        
        Value[] result = { resolveConstantValue() };
        return resolved( "values", result );
    }
}