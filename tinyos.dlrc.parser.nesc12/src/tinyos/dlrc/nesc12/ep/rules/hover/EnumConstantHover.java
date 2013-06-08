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

import tinyos.dlrc.ep.parser.HoverInformation;
import tinyos.dlrc.ep.parser.IHoverInformation;
import tinyos.dlrc.ep.parser.TagSet;
import tinyos.dlrc.nesc12.ep.NesC12AST;
import tinyos.dlrc.nesc12.ep.NesC12ASTModel;
import tinyos.dlrc.nesc12.ep.rules.DocumentRegionInformation;
import tinyos.dlrc.nesc12.parser.ast.elements.Value;
import tinyos.dlrc.nesc12.parser.ast.nodes.ASTNode;
import tinyos.dlrc.nesc12.parser.ast.nodes.declaration.EnumConstant;
import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.utility.Icon;

public class EnumConstantHover implements IHoverInformationRule{
	public IHoverInformation getInformation( NesC12AST ast, DocumentRegionInformation region ){
		ASTNode node = region.getNode();
		if( !(node instanceof Identifier ))
			return null;
		
		Identifier id = (Identifier)node;
		if( !(node.getParent() instanceof EnumConstant ))
			return null;
		
		EnumConstant constant = (EnumConstant)node.getParent();
		
		String name = id.getName();
		Value value = constant.resolveValue();
		
		if( value != null ){
			name = name + " : " + value.toLabel();
		}
		
		return new HoverInformation( new Icon( TagSet.get( NesC12ASTModel.ENUMERATION_CONSTANT ), null ), name, null, null );
	}
}
