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
public final class Action367 implements ParserAction{
	public final java_cup.runtime.Symbol do_action(
		int                        CUP$parser$act_num,
		java_cup.runtime.lr_parser CUP$parser$parser,
		java.util.Stack            CUP$parser$stack,
		int                        CUP$parser$top,
		parser                     parser)
		throws java.lang.Exception{
	java_cup.runtime.Symbol CUP$parser$result;
 // n_configuration ::= NK_CONFIGURATION c_identifier n_component_parameters n_attributes_no_init n_uses_or_provides_block 
            {
              Configuration RESULT =null;
		Token k = (Token)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		Identifier i = (Identifier)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		TemplateParameterList p = (TemplateParameterList)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		AttributeList a = (AttributeList)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		AccessList u = (AccessList)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new Configuration(false,i,p,a,u,parser.missing( "configuration", "implementation", u.getRange().getRight())); 			RESULT.setLeft( k.getLeft() ); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("n_configuration",180, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

	}
}
