package tinyos.dlrc.refactoring.entities.field.rename.global;

import tinyos.dlrc.nesc12.parser.ast.nodes.general.Identifier;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.SelectionIdentifier;
import tinyos.dlrc.refactoring.ast.AstAnalyzerFactory;
import tinyos.dlrc.refactoring.entities.function.rename.FunctionSelectionIdentifier;
import tinyos.dlrc.refactoring.entities.variable.rename.global.VariableSelectionIdentifier;

public class GlobalFieldSelectionIdentifier extends SelectionIdentifier {

	private FunctionSelectionIdentifier functionSelection;
	private VariableSelectionIdentifier variableSelection;
	
	public GlobalFieldSelectionIdentifier(Identifier identifier) {
		this(identifier,new AstAnalyzerFactory(identifier));
	}

	public GlobalFieldSelectionIdentifier(Identifier identifier,AstAnalyzerFactory analyzerFactory) {
		super(identifier, analyzerFactory);
		functionSelection=new FunctionSelectionIdentifier(identifier,analyzerFactory);
		variableSelection=new VariableSelectionIdentifier(identifier,analyzerFactory);
	}
	
	/**
	 * Checks if the given identifier represents a global field.
	 * @return
	 */
	public boolean isGlobalField(){
		return functionSelection.isGlobalFunction()||variableSelection.isGlobalVariable();
	}
	

}
