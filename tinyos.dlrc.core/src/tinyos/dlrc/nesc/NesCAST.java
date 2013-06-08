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
package tinyos.dlrc.nesc;

import java.util.ArrayList;
import java.util.Iterator;

import tinyos.dlrc.Debug;
import tinyos.dlrc.ep.IParseFile;
import tinyos.dlrc.ep.parser.IDocumentRegion;
import tinyos.dlrc.ep.parser.IFileHyperlink;
import tinyos.dlrc.ep.parser.IHoverInformation;
import tinyos.dlrc.ep.parser.INesCAST;
import tinyos.dlrc.ep.parser.INesCCompletionProposal;
import tinyos.dlrc.ep.parser.ProposalLocation;
import tinyos.dlrc.nesc.parser.language.elements.Element;

public class NesCAST implements INesCAST{
    private IParseFile file;
    private Element root;

    public NesCAST( IParseFile file, Element root ){
        this.file = file;
        this.root = root;
    }
    
    public IParseFile getParseFile() {
        return file;
    }
    
    public IFileHyperlink[] getHyperlinks( IDocumentRegion location ){
        // don't do that
        return null;
    }
    
    public IHoverInformation getHoverInformation( IDocumentRegion location ){
    	return null;
    }
    
    public INesCCompletionProposal[] getProposals( ProposalLocation location ) {
        ArrayList<INesCCompletionProposal> result = new ArrayList<INesCCompletionProposal>();
        
        Element bestMatch = root;
        if (bestMatch == null) return null;
        boolean makeSense = true;
        // search if children have better match.
        while(makeSense) {
            makeSense = false;
            Iterator iter = bestMatch.getChildren().iterator();
            int offset = location.getOffset();
            while(iter.hasNext()) {
                Element e = (Element) iter.next();
                if ((e.getStart() <= offset)&&(e.getEnd() >= offset)) {
                    bestMatch = e;
                    ArrayList<INesCCompletionProposal> temp = null;
                    try {
                        Debug.info( "Query class: "+bestMatch.getClass()+" for completion proposals" );
                        temp = bestMatch.getCompletionProposals( location );
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } 
                    if (temp !=null) {
                        result.addAll(temp);
                    }
                    makeSense = true;
                    break;
                }   
            }
        }
        
        if( result.size() == 0 )
            return null;
        
        return result.toArray( new INesCCompletionProposal[ result.size() ] );
    }
}
