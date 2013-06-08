package tinyos.dlrc.refactoring.entities.variable.rename.global;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;

public class GlobalVariableAvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		VariableSelectionIdentifier selectionIdentifier=new VariableSelectionIdentifier(selectedIdentifier);
		return selectionIdentifier.isGlobalVariable();
//		ASTUtil4Variables astUtil4Variables=new ASTUtil4Variables();
//		return astUtil4Variables.isGlobalVariable(selectedIdentifier);
	}
	

}