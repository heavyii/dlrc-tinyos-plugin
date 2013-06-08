package tinyos.dlrc.refactoring.entities.interfaces.alias.rename;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;
import tinyos.dlrc.refactoring.entities.interfaces.rename.InterfaceSelectionIdentifier;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		InterfaceSelectionIdentifier selectionIdentifier=new InterfaceSelectionIdentifier(selectedIdentifier);
		return selectionIdentifier.isInterfaceAlias();
	}
	


}
