package tinyos.dlrc.refactoring.abstractrefactoring.rename;

import org.eclipse.jface.text.ITextSelection;

import tinyos.dlrc.editors.NesCEditor;
import tinyos.dlrc.nesc12.ep.NesC12AST;
import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.ast.ASTPositioning;
import tinyos.dlrc.refactoring.utilities.ActionHandlerUtil;

public abstract class RenameAvailabilityTester implements tinyos.dlrc.refactoring.IRefactoringAvailabilityTester {

	@Override
	public boolean test(ITextSelection receiver) {
		NesCEditor editor = ActionHandlerUtil.getActiveEditor().getNesCEditor();
		NesC12AST ast = (NesC12AST) editor.getAST();
		ASTPositioning util = new ASTPositioning(ast);
		Identifier selectedIdentifier = util.getASTLeafAtPos(receiver.getOffset(),receiver.getLength(), Identifier.class);
		if(selectedIdentifier==null){
			return false;
		}
		return isSelectionAppropriate(selectedIdentifier);
	}
	
	/**
	 * This function has to check, if the selected Identifier allows the Refactoring to proceed.
	 * @param selectedIdentifier
	 * @return
	 */
	abstract protected boolean isSelectionAppropriate(Identifier selectedIdentifier);
}