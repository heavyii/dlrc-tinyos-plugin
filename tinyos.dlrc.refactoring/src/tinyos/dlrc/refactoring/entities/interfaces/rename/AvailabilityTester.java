package tinyos.dlrc.refactoring.entities.interfaces.rename;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		InterfaceSelectionIdentifier selectionIdentifier=new InterfaceSelectionIdentifier(selectedIdentifier);
		return selectionIdentifier.isInterface();
	}
	


}
