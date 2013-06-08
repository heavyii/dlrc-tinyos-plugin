package tinyos.dlrc.nesc12.ep.rules.proposals;

import tinyos.dlrc.nesc12.ep.NesC12AST;
import tinyos.dlrc.nesc12.ep.rules.ProposalUtility;
import tinyos.dlrc.nesc12.parser.CompletionProposalCollector;
import tinyos.dlrc.nesc12.parser.ast.elements.Field;
import tinyos.dlrc.nesc12.parser.ast.elements.Type;
import tinyos.dlrc.nesc12.parser.ast.elements.types.DataObjectType;
import tinyos.dlrc.nesc12.parser.ast.elements.types.TypeUtility;

public class AccessDataObjectPointerRuleByField extends AccessRuleByField{
	public AccessDataObjectPointerRuleByField(){
		super( "->" );
	}
	
	@Override
	protected void propose( Field beforeAccessSign, NesC12AST ast, CompletionProposalCollector collector ){
		Type type = beforeAccessSign.getType();
	    DataObjectType data = TypeUtility.object( TypeUtility.raw( TypeUtility.pointer( type ) ) );
	    if( data == null )
	    	return;
	    
        for( Field field : data.getAllFields() ){
            collector.add( ProposalUtility.createProposal( field, collector.getLocation(), ast ) );
        }
	}
}
