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
package tinyos.dlrc.search.ui.event;

import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.SearchResultEvent;

import tinyos.dlrc.search.model.ASTReferenceSearchResult;

public class ReferencesAddedSearchResultEvent extends SearchResultEvent{
	private ASTReferenceSearchResult.Result[] references;
	
	public ReferencesAddedSearchResultEvent( ISearchResult searchResult, ASTReferenceSearchResult.Result[] references ){
		super( searchResult );
		this.references = references;
	}
	
	public ASTReferenceSearchResult.Result[] getReferences(){
		return references;
	}
}
