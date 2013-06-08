package tinyos.dlrc.refactoring.entities.function.rename.nesc;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		NescFunctionSelectionIdentifier selectionIdentifier=new NescFunctionSelectionIdentifier(selectedIdentifier);
		return selectionIdentifier.isNescFunction(selectedIdentifier);
	}
	


}
