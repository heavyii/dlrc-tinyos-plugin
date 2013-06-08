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
package tinyos.dlrc.search.model.group.reference;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import tinyos.dlrc.ProjectTOS;
import tinyos.dlrc.editors.NesCIcons;
import tinyos.dlrc.ep.IParseFile;
import tinyos.dlrc.ep.parser.IASTModelNode;
import tinyos.dlrc.ep.parser.IFileRegion;
import tinyos.dlrc.search.model.ASTReferenceSearchResult;
import tinyos.dlrc.search.model.group.Group;
import tinyos.dlrc.search.model.group.GroupGenerator;
import tinyos.dlrc.search.model.group.declaration.FileGroupGenerator;
import tinyos.dlrc.search.model.group.declaration.ProjectGroupGenerator;

public abstract class ReferenceGroupGenerator<K> extends GroupGenerator<K, ASTReferenceSearchResult.Result>{
	private Object input;
	
	public void setInput( Object input ){
		this.input = input;
	}
	
	
	@Override
	protected Group createGroupFor( K groupKey ){
		if( groupKey instanceof ProjectTOS ){
			ProjectTOS project = (ProjectTOS)groupKey;
			return new Group( project.getProject().getName(), ProjectGroupGenerator.getWorkbenchImage( project.getProject() ), project.getProject() );
		}
		
		if( groupKey instanceof IProject ){
			IProject project = (IProject)groupKey;
			return new Group( project.getName(), ProjectGroupGenerator.getWorkbenchImage( project ), project );
		}
		
		if( groupKey instanceof IParseFile ){
			IParseFile file = (IParseFile)groupKey;
			return FileGroupGenerator.createFileGroup( file, true );
		}
		
		if( groupKey instanceof IASTModelNode ){
			IASTModelNode node = (IASTModelNode)groupKey;
			return new Group( node.getLabel(), getImage( node ), node.getRegion() );
		}
		
		if( groupKey instanceof IFileRegion ){
			IFileRegion region = (IFileRegion)groupKey;
			return new Group( String.valueOf( region.getLine() ), null, region );
		}
		
		return null;
	}

	
	protected Image getImage( IASTModelNode node ){
		IParseFile file = node.getParseFile();
		if( file == null )
			return null;
		
		ProjectTOS project = file.getProject();
		if( project == null )
			return null;
		
		ImageDescriptor image = project.getModel().getParserFactory().getImageFor( node.getTags() );
		return NesCIcons.icons().get( image, true );
	}
	
	@Override
	protected Group createRoot(){
		return new RootGroup( input );
	}
	
	public static class RootGroup extends Group{
		private Object input;
		
		public RootGroup( Object input ){
			super( null, null, null );
			this.input = input;
		}
		
		public Object getInput(){
			return input;
		}
	}
}
