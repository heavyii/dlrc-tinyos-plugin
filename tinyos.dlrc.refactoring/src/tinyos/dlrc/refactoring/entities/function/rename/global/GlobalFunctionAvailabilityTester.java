package tinyos.dlrc.refactoring.entities.function.rename.global;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;
import tinyos.dlrc.refactoring.entities.function.rename.FunctionSelectionIdentifier;

public class GlobalFunctionAvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		FunctionSelectionIdentifier selectionIdentifier=new FunctionSelectionIdentifier(selectedIdentifier);
		return selectionIdentifier.isGlobalFunction();
	}
	
}
