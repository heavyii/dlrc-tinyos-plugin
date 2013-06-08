package tinyos.dlrc.refactoring.abstractrefactoring.rename;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ltk.core.refactoring.participants.RenameRefactoring;
import org.eclipse.ltk.ui.refactoring.RefactoringWizardOpenOperation;

import tinyos.dlrc.editors.NesCEditor;
import tinyos.dlrc.nesc12.ep.NesC12AST;
import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.DefaultRefactoringWizard;
import tinyos.dlrc.refactoring.ast.ASTPositioning;
import tinyos.dlrc.refactoring.utilities.ActionHandlerUtil;

public abstract class RenameActionHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		NesCEditor editor = ActionHandlerUtil.getNesCEditor(event);
		ITextSelection selection = ActionHandlerUtil.getSelection(editor);
		ASTPositioning util = new ASTPositioning((NesC12AST) editor.getAST());
		Identifier id=util.getASTLeafAtPos(selection.getOffset(),selection.getLength(), Identifier.class);
		if(id==null){
			return null;
		}
		String oldName =id.getName();
		RenameInfo info = new RenameInfo(oldName,editor);
		RenameInputPage inputPage=new RenameInputPage(info);
		info.setInputPage(inputPage);
		RenameProcessor processor = createProcessor(info);
		RenameRefactoring refactoring = new RenameRefactoring(processor);
		DefaultRefactoringWizard wizard = new DefaultRefactoringWizard(
				refactoring, 
				inputPage, 
				info);
		RefactoringWizardOpenOperation wizardStarter = new RefactoringWizardOpenOperation(wizard);

		try {
			wizardStarter.run(editor.getSite().getShell(),info.getInputWizardName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Return is reserved for Future use. Must be null. Really!! */
		return null;
	}

	/**
	 * Must return the Processor which should be used for this action.
	 * @param info
	 * @return
	 */
	protected abstract RenameProcessor createProcessor(RenameInfo info);
	
	


}
