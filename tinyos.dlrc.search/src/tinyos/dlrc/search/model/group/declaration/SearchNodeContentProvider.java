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
package tinyos.dlrc.search.model.group.declaration;

import org.eclipse.search.ui.SearchResultEvent;

import tinyos.dlrc.ep.parser.IASTModel;
import tinyos.dlrc.ep.parser.IASTModelNode;
import tinyos.dlrc.ep.parser.IASTModelNodeFilter;
import tinyos.dlrc.ep.parser.TagSet;
import tinyos.dlrc.model.ProjectModel;
import tinyos.dlrc.search.model.ISearchTreeContentProvider;
import tinyos.dlrc.search.ui.event.ItemsAddedSearchResultEvent;
import tinyos.dlrc.search.ui.event.ItemsRemovedSearchResultEvent;
import tinyos.dlrc.views.NodeContentProvider;

public class SearchNodeContentProvider extends NodeContentProvider implements ISearchTreeContentProvider{
	public SearchNodeContentProvider( TagSet tags ){
		super( tags );
	}
	
	public SearchNodeContentProvider( IASTModelNodeFilter rootFilter ){
		super( rootFilter );
	}
	
	public boolean handle( SearchResultEvent event ){
		if( event instanceof ItemsAddedSearchResultEvent ){
			IASTModel model = ((ItemsAddedSearchResultEvent)event).getModel();
			ProjectModel backup = ((ItemsAddedSearchResultEvent)event).getProject();
			IASTModelNode[] nodes = ((ItemsAddedSearchResultEvent)event).getItems();
			
			for( IASTModelNode node : nodes ){
				ensureModel( model, backup );
				addRoot( model, node );
			}
			
			return true;
		}
		if( event instanceof ItemsRemovedSearchResultEvent ){
			IASTModelNode[] items = ((ItemsRemovedSearchResultEvent)event).getItems();
			for( IASTModelNode item : items ){
				removed( item );
			}
			return true;
		}
		
		return false;
	}
}
