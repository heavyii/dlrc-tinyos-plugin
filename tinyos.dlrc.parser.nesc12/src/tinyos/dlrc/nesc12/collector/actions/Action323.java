package tinyos.dlrc.nesc12.collector.actions;
import tinyos.dlrc.nesc12.parser.StringRepository;
import tinyos.dlrc.nesc12.parser.ScopeStack;
import tinyos.dlrc.nesc12.parser.RawParser;
import tinyos.dlrc.nesc12.lexer.Token;
import tinyos.dlrc.nesc12.lexer.Lexer;
import tinyos.dlrc.nesc12.parser.ast.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.declaration.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.definition.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.error.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.expression.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.general.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.nesc.*;
import tinyos.dlrc.nesc12.parser.ast.nodes.statement.*;
import tinyos.dlrc.nesc12.collector.*;
public final class Action323 implements ParserAction{
	public final java_cup.runtime.Symbol do_action(
		int                        CUP$parser$act_num,
		java_cup.runtime.lr_parser CUP$parser$parser,
		java.util.Stack            CUP$parser$stack,
		int                        CUP$parser$top,
		parser                     parser)
		throws java.lang.Exception{
	java_cup.runtime.Symbol CUP$parser$result;
 // n_external_definitions ::= n_external_definitions error 
            {
              NesCExternalDefinitionList RESULT =null;
		NesCExternalDefinitionList s = (NesCExternalDefinitionList)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 RESULT = s; s.addError( parser.errorNode( "external definition list", "external definition" )); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("n_external_definitions",178, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

	}
}
