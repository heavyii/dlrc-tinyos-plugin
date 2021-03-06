package tinyos.dlrc.refactoring.entities.variable.rename.local;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;
import tinyos.dlrc.refactoring.utilities.ASTUtil4Variables;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		ASTUtil4Variables astUtil4Variables=new ASTUtil4Variables();
		return astUtil4Variables.isLocalVariable(selectedIdentifier);
	}
	
}
