package tinyos.dlrc.refactoring.entities.component.rename;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameAvailabilityTester;

public class AvailabilityTester extends RenameAvailabilityTester{

	@Override
	protected boolean isSelectionAppropriate(Identifier selectedIdentifier) {
		ComponentSelectionIdentifier selectionIdentifier=new ComponentSelectionIdentifier(selectedIdentifier);
		return selectionIdentifier.isComponent();
	}
	


}
