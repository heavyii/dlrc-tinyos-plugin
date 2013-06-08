package tinyos.dlrc.refactoring.entities.function.extract;



import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.text.ITextSelection;

import tinyos.dlrc.editors.NesCEditor;
import tinyos.dlrc.refactoring.IRefactoringAvailabilityTester;
import tinyos.dlrc.refactoring.utilities.ActionHandlerUtil;

public class AvailabilityTester implements IRefactoringAvailabilityTester{

	@Override
	public boolean test(ITextSelection receiver) {
		NesCEditor editor = ActionHandlerUtil.getActiveEditor().getNesCEditor();
		Info info = new Info(editor);
		Processor processor = new Processor(info);

		try {
			return processor.checkInitialConditions(null).isOK();
		} catch (OperationCanceledException e) {
			return false;
		} catch (CoreException e) {
			return false;
		} catch (Throwable t){
			return false;
		}
	}

}
