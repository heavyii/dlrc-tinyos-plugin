package tinyos.dlrc.refactoring.entities.variable.rename.implementation;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;
import tinyos.dlrc.refactoring.entities.variable.rename.global.VariableSelectionIdentifier;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		VariableSelectionIdentifier selectionIdentifier=new VariableSelectionIdentifier(selectedIdentifier);
		return selectionIdentifier.isImplementationLocalVariable();
	}
	
}