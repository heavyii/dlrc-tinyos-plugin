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
package tinyos.dlrc.nesc12;

import static tinyos.dlrc.ep.parser.ASTNodeFilterFactory.or;
import static tinyos.dlrc.ep.parser.ASTNodeFilterFactory.subset;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Comparator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.resource.ImageDescriptor;

import tinyos.dlrc.ProjectTOS;
import tinyos.dlrc.TinyOSPlugin;
import tinyos.dlrc.editors.NesCIcons;
import tinyos.dlrc.ep.IParseFile;
import tinyos.dlrc.ep.parser.ASTView;
import tinyos.dlrc.ep.parser.IASTModel;
import tinyos.dlrc.ep.parser.IASTModelNodeFactory;
import tinyos.dlrc.ep.parser.IASTModelPath;
import tinyos.dlrc.ep.parser.IDeclaration;
import tinyos.dlrc.ep.parser.IDeclarationFactory;
import tinyos.dlrc.ep.parser.INesCCompletionProposal;
import tinyos.dlrc.ep.parser.INesCDefinitionCollector;
import tinyos.dlrc.ep.parser.INesCInitializer;
import tinyos.dlrc.ep.parser.INesCParser;
import tinyos.dlrc.ep.parser.INesCParserFactory;
import tinyos.dlrc.ep.parser.ProposalLocation;
import tinyos.dlrc.ep.parser.Tag;
import tinyos.dlrc.ep.parser.TagSet;
import tinyos.dlrc.ep.parser.standard.ASTModel;
import tinyos.dlrc.ep.parser.standard.ASTModelPath;
import tinyos.dlrc.ep.storage.IStorage;
import tinyos.dlrc.nature.MissingNatureException;
import tinyos.dlrc.nesc12.collector.IncludingParser;
import tinyos.dlrc.nesc12.ep.DeclarationResolver;
import tinyos.dlrc.nesc12.ep.NesC12ASTModel;
import tinyos.dlrc.nesc12.ep.NesC12DeclarationFactory;
import tinyos.dlrc.nesc12.ep.NesC12GenericStorage;
import tinyos.dlrc.nesc12.ep.NesC12Initializer;
import tinyos.dlrc.nesc12.ep.NesC12ModelNodeFactory;
import tinyos.dlrc.nesc12.parser.ModelDeclarationResolver;
import tinyos.dlrc.nesc12.parser.ProjectDeclarationResolver;
import tinyos.dlrc.nesc12.parser.SynchronizedDeclarationResolver;
import tinyos.dlrc.nesc12.view.comparators.MultiComparator;
import tinyos.dlrc.nesc12.view.comparators.TagComparator;

public class Factory implements INesCParserFactory {
    private NesC12DeclarationFactory declarationFactory = new NesC12DeclarationFactory();
    private NesC12ModelNodeFactory modelNodeFactory = new NesC12ModelNodeFactory();
    
    public ASTView[] getViews(){
        return new ASTView[]{
                new ASTView( "&Files", NesCIcons.icons().get( NesCIcons.ICON_TEMPLATE ), subset( NesC12ASTModel.UNIT ), false ),
                new ASTView( "&Interfaces", NesCIcons.icons().get(NesCIcons.ICON_INTERFACE ), subset( Tag.INTERFACE ), true ),
                new ASTView( "&Configurations", NesCIcons.icons().get(NesCIcons.ICON_CONFIGURATION) , subset( Tag.CONFIGURATION ), false ),
                new ASTView( "&Modules", NesCIcons.icons().get(NesCIcons.ICON_MODULE ),  or( subset( Tag.MODULE ), subset( Tag.BINARY_COMPONENT )), false ),
                //new ASTView( "&Structs", NesCIcons.get(NesCIcons.ICON_STRUCT ), subset( Tag.DATA_OBJECT ), false )
                new ASTView( "&Types", NesCIcons.icons().get( NesCIcons.ICON_STRUCT ), subset( NesC12ASTModel.TYPE ), false )
        };
    }
    
    public ImageDescriptor getImageFor( TagSet tags ){
        return NesC12ASTModel.getImageFor( tags );
    }
    
    public TagSet getSupportedTags(){
    	return NesC12ASTModel.getSupportedTags();
    }
    
    public TagSet getDecoratingTags(){
	    return NesC12ASTModel.getDecoratingTags();
    }
    
    public IStorage createStorage( ProjectTOS project, DataInputStream in, IProgressMonitor monitor ) throws IOException{
        return new NesC12GenericStorage( project, in, monitor );
    }
    
    public IStorage createStorage( ProjectTOS project, DataOutputStream out, IProgressMonitor monitor ) throws IOException{
        return new NesC12GenericStorage( project, out, monitor );
    }
    
    public Comparator<TagSet> createComparator(){
        MultiComparator<TagSet> result = new MultiComparator<TagSet>();
        result.add( new TagComparator( NesC12ASTModel.PARAMETERS ) );
        result.add( new TagComparator( ASTModel.SPECIFICATION ));
        return result;
    }
    
    public IDeclarationFactory getDeclarationFactory(){
        return declarationFactory; 
    }
    
    public IASTModelNodeFactory getModelNodeFactory(){
        return modelNodeFactory;
    }
    
    public INesCDefinitionCollector createCollector( ProjectTOS project, IParseFile parseFile ){
        return new IncludingParser( project, parseFile );
    }
    
    public INesCInitializer createInitializer( IProject project ) {
        return new NesC12Initializer( project );
    }
    
    public INesCParser createParser( IProject project ) {
        return new Parser( project );
    }
    
    public IASTModel createModel( IProject project ) {
        DeclarationResolver resolver = null;
        if( project != null ){
        	try{
	            ProjectTOS tos = TinyOSPlugin.getDefault().getProjectTOS( project );
	            if( tos != null ){
	                resolver = new SynchronizedDeclarationResolver( tos, new ProjectDeclarationResolver( tos ) );
	            }
        	}
        	catch( MissingNatureException ex ){
        		// silent
        	}
        }
        
        if( resolver == null ){
            NesC12ASTModel model = new NesC12ASTModel( project, null );
            model.setDeclarationResolver( new ModelDeclarationResolver( model ) );
            return model;
        }
        else{
            return new NesC12ASTModel( project, resolver );
        }
    }

    public IASTModelPath createRoot( IProject project ) {
        return new ASTModelPath( null );
    }

    public INesCCompletionProposal[] getProposals( ProposalLocation location ) {
        // if we are down to this, then there is no information to gather left...
        return null;
    }
    
    public IDeclaration toBasicType( String type, String name, IDeclaration[] typedefs ) {
        IDeclaration result = Parser.parseType( type, name, typedefs );
        if( result == null )
            throw new IllegalArgumentException( "Can't parse: " + type );
        return result;
    }
}
