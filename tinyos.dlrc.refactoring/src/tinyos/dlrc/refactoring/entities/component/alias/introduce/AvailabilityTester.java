package tinyos.dlrc.refactoring.entities.component.alias.introduce;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;
import tinyos.dlrc.refactoring.ast.AstAnalyzerFactory;
import tinyos.dlrc.refactoring.ast.ConfigurationAstAnalyzer;
import tinyos.dlrc.refactoring.entities.component.rename.ComponentSelectionIdentifier;
import tinyos.dlrc.refactoring.utilities.ASTUtil;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		AstAnalyzerFactory analyzerFacotry=new AstAnalyzerFactory(selectedIdentifier);
		ComponentSelectionIdentifier selectionIdentifier=new ComponentSelectionIdentifier(selectedIdentifier,analyzerFacotry);
		if(!selectionIdentifier.isComponentDeclaration()){
			return false;
		}
		ASTUtil astUtil=new ASTUtil();
		ConfigurationAstAnalyzer configurationAnalyzer=analyzerFacotry.getConfigurationAnalyzer();
		//If the component is alredy aliased, the refactoring is not available. If it is not already aliased the local2global name map contains the identifier itself in its keyset.
		return astUtil.containsIdentifierInstance(selectedIdentifier, configurationAnalyzer.getComponentLocalName2ComponentGlobalName().keySet());	
	}
	


}
