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

import tinyos.dlrc.nesc12.lexer.Token;
import tinyos.dlrc.nesc12.parser.ast.ASTVisitor;
import tinyos.dlrc.nesc12.parser.ast.AnalyzeStack;
import tinyos.dlrc.nesc12.parser.ast.elements.Generic;
import tinyos.dlrc.nesc12.parser.ast.elements.Type;
import tinyos.dlrc.nesc12.parser.ast.elements.Value;
import tinyos.dlrc.nesc12.parser.ast.elements.values.IntegerValue;
import tinyos.dlrc.nesc12.parser.ast.nodes.AbstractTokenASTNode;

public class IntegerConstant extends AbstractTokenASTNode implements Expression {
    private IntegerValue value;
    
    public IntegerConstant( Token token ){
        super( "IntegerConstant", token );
    }

    public Type resolveType() {
        return value == null ? null : value.getType();
    }
    
    public Generic resolveGeneric() {
        return resolveConstantValue();
    }
    
    public Value[] resolveConstantValues() {
        if( isResolved( "values" ))
            return resolved( "values" );
        return resolved( "values", new Value[]{ resolveConstantValue() } );
    }
    
    public Value resolveConstantValue() {
        return value;
    }
    
    @Override
    public void resolve( AnalyzeStack stack ) {
        // TODO method not implemented
        super.resolve( stack );
        stack.checkCancellation();
        
        if( token != null ){
            value = IntegerValue.valueOf( token.getText(), stack, this );
        }
    }
    
    @Override
    protected boolean visit( ASTVisitor visitor ) {
        return visitor.visit( this );
    }
    
    @Override
    protected void endVisit( ASTVisitor visitor ) {
        visitor.endVisit( this );
    }

    public boolean hasCommas() {
        return false;
    }

    public boolean isConstant() {
        return true;
    }
}
