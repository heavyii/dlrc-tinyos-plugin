package tinyos.dlrc.refactoring.entities.functionparameter.rename;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		FunctionParameterSelectionIdentifier selectionIdentifier=new FunctionParameterSelectionIdentifier(selectedIdentifier);
		boolean ret=selectionIdentifier.isFunctionParameter();
		return ret;
	}
	
}
