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
package tinyos.dlrc.nesc12.ep.rules.hover;

import java.util.List;

import org.eclipse.jface.text.BadLocationException;

import tinyos.dlrc.editors.IDocumentMap;
import tinyos.dlrc.ep.parser.HoverInformation;
import tinyos.dlrc.ep.parser.IHoverInformation;
import tinyos.dlrc.ep.parser.TagSet;
import tinyos.dlrc.nesc12.ep.INesC12Location;
import tinyos.dlrc.nesc12.ep.NesC12AST;
import tinyos.dlrc.nesc12.ep.NesC12ASTModel;
import tinyos.dlrc.nesc12.ep.nodes.ModelAttribute;
import tinyos.dlrc.nesc12.ep.rules.DocumentRegionInformation;
import tinyos.dlrc.nesc12.ep.rules.RuleUtility;
import tinyos.dlrc.nesc12.parser.ast.elements.Name;
import tinyos.dlrc.nesc12.parser.ast.elements.types.TypeUtility;
import tinyos.dlrc.nesc12.parser.ast.nodes.ASTNode;
import tinyos.dlrc.nesc12.parser.ast.nodes.declaration.TypedefName;
import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.nesc12.parser.meta.NamedType;
import tinyos.dlrc.utility.Icon;

public class LocalTypenameHover extends AbstractInformationRule implements IHoverInformationRule{
	public IHoverInformation getInformation( NesC12AST ast, DocumentRegionInformation region ){
	    try{
            if( ast.getRanges() == null )
                return null;
            
            // ensure not conflicting with an access rule, no . or -> in front
            IDocumentMap document = region.getLocation().getDocument();
            
            int offset = region.getOffset().getInputfileOffset();
            
            if( RuleUtility.hasBefore( offset, document, "." ))
                return null;
            
            if( RuleUtility.hasBefore( offset, document, "->" ))
                return null;
            
            INesC12Location location = region.getOffset();
            ASTNode node = region.getNode();
            
            if( !(node instanceof Identifier) )
                return null;
            
            ASTNode parent = node.getParent();
            
            boolean typedefName = parent instanceof TypedefName;
            
            if( typedefName ){
                String name = ((Identifier)node).getName();
                
                String content = getFieldDocumentation( (Identifier)node, region );
                // List<NamedType> types = ranges.getTypeTags( location.getInputfileOffset() );
                List<NamedType> types = ast.getTypedefs().get( location.getInputfileOffset() );
                
                for( NamedType type : types ){
                	Name typeName = type.getName();
                	if( typeName != null && typeName.toIdentifier().equals( name )){
                		String title = TypeUtility.toAstNodeLabel( type.getType() );
                		TagSet tags = TagSet.get( NesC12ASTModel.TYPEDEF );
                		ModelAttribute[] attributes = type.getAttributes();
                		
                		return new HoverInformation( new Icon( tags, attributes ), title, content, null );
                	}
                }
            }
        }
        catch( BadLocationException ex ){
            // ignore
        }
        return null;
	}
}