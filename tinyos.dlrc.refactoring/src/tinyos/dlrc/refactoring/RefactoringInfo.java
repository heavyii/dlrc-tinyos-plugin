package tinyos.dlrc.refactoring;

import org.eclipse.jface.text.ITextSelection;

import tinyos.dlrc.editors.NesCEditor;
import tinyos.dlrc.nesc12.ep.NesC12AST;
import tinyos.dlrc.refactoring.ast.ASTPositioning;
import tinyos.dlrc.refactoring.utilities.ActionHandlerUtil;
import tinyos.dlrc.refactoring.utilities.ProjectUtil;

/**
 * Carries around all Information needed to execute a Refactoring
 * This Class is meant to be extended for each Refactoring
 */
public class RefactoringInfo {

	private String inputPageName;
	private String inputWizardName;
	private NesCEditor editor;
	private ITextSelection selection;
	private ASTPositioning astPositioning;
	private ProjectUtil projectUtil;
	
	public RefactoringInfo(NesCEditor editor, String inputPageName, String inputWizardName) {
		this.editor = editor;
		this.inputPageName = inputPageName;
		this.inputWizardName = inputWizardName;
		setSelection(ActionHandlerUtil.getSelection(editor));
	}

	public void setInputPageName(String inputPageName) {
		this.inputPageName = inputPageName;
	}

	public String getInputPageName() {
		return inputPageName;
	}

	public void setInputWizardName(String inputWizardName) {
		this.inputWizardName = inputWizardName;
	}

	public String getInputWizardName() {
		return inputWizardName;
	}

	public void setEditor(NesCEditor editor) {
		this.editor = editor;
	}

	public NesCEditor getEditor() {
		return editor;
	}

	private void setSelection(ITextSelection selection) {
		this.selection = selection;
	}

	public ITextSelection getSelection() {
		return selection;
	}
	
	/**
	 * Returns the Ast for the currently selected Editor.
	 * @return
	 */
	public NesC12AST getAst(){
		return (NesC12AST)getEditor().getAST();
	}
	
	/**
	 * Often the first initialization of the Class is before the AST is ready.
	 * This getter makes sure the AST is used, as soon as it is available.
	 * 
	 * @return
	 */
	public ASTPositioning getAstPositioning() {
		if (astPositioning == null) {
			NesC12AST ast = getAst();
			if (ast != null) {
				astPositioning = new ASTPositioning(ast);
			}
		}
		return astPositioning;
	}
	
	public ProjectUtil getProjectUtil(){
		if(projectUtil==null){
			projectUtil=new ProjectUtil(getEditor());
		}
		return projectUtil;
	}

}
