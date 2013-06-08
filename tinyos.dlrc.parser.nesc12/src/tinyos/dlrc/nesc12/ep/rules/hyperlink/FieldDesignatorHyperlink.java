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
package tinyos.dlrc.nesc12.ep.rules.hyperlink;

import tinyos.dlrc.ep.parser.IFileRegion;
import tinyos.dlrc.ep.parser.standard.FileHyperlink;
import tinyos.dlrc.nesc12.ep.NesC12AST;
import tinyos.dlrc.nesc12.ep.rules.RuleUtility;
import tinyos.dlrc.nesc12.parser.ast.elements.Field;
import tinyos.dlrc.nesc12.parser.ast.nodes.ASTNode;
import tinyos.dlrc.nesc12.parser.ast.nodes.declaration.FieldDesignator;
import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.preprocessor.RangeDescription;

public class FieldDesignatorHyperlink implements IHyperlinkRule{
    public void search( NesC12AST ast, HyperlinkCollector collector ){
        ASTNode node = collector.getNode();
        
        if( !(node instanceof Identifier))
            return;
        
        ASTNode parent = node.getParent();
        if( !(parent instanceof FieldDesignator ))
            return;
        
        FieldDesignator designator = (FieldDesignator)parent;
        Field field = designator.resolveField();
        if( field == null )
            return;
        
        IFileRegion source = collector.getSourceRegion();
        if( source == null )
            return;
        
        RangeDescription rangeDescription = field.getRange();
        if( rangeDescription == null )
            return;
        
        IFileRegion targetRegion = RuleUtility.source( rangeDescription );
        if( targetRegion != null ){
        	collector.add( new FileHyperlink( source, targetRegion ) );
        }
    }
    
}
