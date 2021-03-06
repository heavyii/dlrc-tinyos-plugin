/*
 * Dlrc 2, NesC development in Eclipse.
 * Copyright (C) 2009 DLRC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Web:  http://tos-ide.ethz.ch
 * Mail: tos-ide@tik.ee.ethz.ch
 */
package tinyos.dlrc.nesc.parser;

import java.io.IOException;

import tinyos.dlrc.nesc.scanner.Scanner;

public interface IParser {
	
	public void setScanner(Scanner s);
	public Object yyparse (yyInput yyLex) throws IOException, yyException;
	public Object yyparse (yyInput yyLex, Object yydebug) throws IOException, yyException;
	public Object yyparse (yyInput yyLex, ParserError pe) throws IOException, yyException;
	public int type_of_name(String string);
	public boolean get_idents_only();

	
}