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
public final class Action1004 implements ParserAction{
	public final java_cup.runtime.Symbol do_action(
		int                        CUP$parser$act_num,
		java_cup.runtime.lr_parser CUP$parser$parser,
		java.util.Stack            CUP$parser$stack,
		int                        CUP$parser$top,
		parser                     parser)
		throws java.lang.Exception{
	java_cup.runtime.Symbol CUP$parser$result;
 // c_labeled_statement ::= c_identifier P_COLON c_statement 
            {
              Statement RESULT =null;
		Identifier i = (Identifier)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		Statement s = (Statement)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = new LabeledStatement( i, s ); 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("c_labeled_statement",122, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

	}
}
