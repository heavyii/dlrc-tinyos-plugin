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

//----------------------------------------------------
// The following code was generated by CUP v0.11a beta 20060608
// Tue Oct 06 11:19:47 CEST 2009
//----------------------------------------------------

package tinyos.dlrc.nesc12.parser;

/** CUP generated class containing symbol constants. */
public class sym {
  /* terminals */
  public static final int K_EXTENSION = 86;
  public static final int K_BREAK = 83;
  public static final int K_STATIC = 52;
  public static final int P_PLUS = 15;
  public static final int IDENTIFIER = 112;
  public static final int ENUMERATION_CONSTANT = 115;
  public static final int NK_INTERFACE = 89;
  public static final int NK_NX_UNION = 97;
  public static final int K_VOLATILE = 71;
  public static final int K_SIGNED = 62;
  public static final int P_SHIFT_LEFT = 21;
  public static final int K_GOTO = 81;
  public static final int FLOATING_CONSTANT = 114;
  public static final int P_STAR = 14;
  public static final int P_ADD_ASSIGN = 41;
  public static final int P_POINT = 9;
  public static final int P_CURLY_CLOSE = 8;
  public static final int NK_GENERIC = 93;
  public static final int K_DEFAULT = 75;
  public static final int NK_TASK = 109;
  public static final int NK_MODULE = 94;
  public static final int S_FOLLOW = 2;
  public static final int P_MUL_ASSIGN = 38;
  public static final int K_DOUBLE = 61;
  public static final int P_LINE = 30;
  public static final int K_SIZEOF = 49;
  public static final int INTEGER_CONSTANT = 113;
  public static final int K_LONG = 59;
  public static final int P_SHIFT_LEFT_ASSIGN = 43;
  public static final int P_NOT_EQ = 28;
  public static final int P_MINUS = 16;
  public static final int K_CHAR = 56;
  public static final int NK_SIGNAL = 108;
  public static final int P_EXCLAMATION = 18;
  public static final int P_QUESTION = 33;
  public static final int NK_AS = 92;
  public static final int CHARACTER_CONSTANT = 116;
  public static final int NK_CONFIGURATION = 98;
  public static final int K_STRUCT = 66;
  public static final int K_VOID = 55;
  public static final int K_ELSE = 77;
  public static final int NK_IMPLEMENTATION = 95;
  public static final int P_AND = 31;
  public static final int K_REGISTER = 54;
  public static final int P_COLON = 34;
  public static final int P_GREATER = 24;
  public static final int NK_ATOMIC = 106;
  public static final int NK_NEW = 101;
  public static final int K_INLINE = 72;
  public static final int K__COMPLEX = 65;
  public static final int P_CARET = 29;
  public static final int P_SEMICOLON = 35;
  public static final int P_PERCENT = 20;
  public static final int NP_AT = 87;
  public static final int K_SHORT = 57;
  public static final int K_ASM = 85;
  public static final int P_SHIFT_RIGHT = 22;
  public static final int P_AMP = 13;
  public static final int P_ROUND_OPEN = 5;
  public static final int P_RECT_CLOSE = 4;
  public static final int NK_COMPONENT = 100;
  public static final int NK_NX_STRUCT = 96;
  public static final int K_TYPEDEF = 50;
  public static final int P_DIV_ASSIGN = 39;
  public static final int P_EQ = 27;
  public static final int K_CONST = 69;
  public static final int K_EXTERN = 51;
  public static final int P_RIGHT_ARROW = 10;
  public static final int K_DO = 79;
  public static final int K__BOOL = 64;
  public static final int P_DECREMENT = 12;
  public static final int NK_POST = 110;
  public static final int NK_COMMAND = 102;
  public static final int P_SHIFT_RIGHT_ASSIGN = 44;
  public static final int P_MOD_ASSIGN = 40;
  public static final int P_CURLY_OPEN = 7;
  public static final int K_SWITCH = 74;
  public static final int P_RECT_OPEN = 3;
  public static final int K_RETURN = 84;
  public static final int K_CASE = 73;
  public static final int P_OR_ASSIGN = 47;
  public static final int EOF = 0;
  public static final int NK_INCLUDES = 111;
  public static final int P_COMMA = 48;
  public static final int P_SMALLER = 23;
  public static final int P_SUB_ASSIGN = 42;
  public static final int NK_COMPONENTS = 99;
  public static final int K_FLOAT = 60;
  public static final int K_AUTO = 53;
  public static final int P_ELLIPSIS = 36;
  public static final int error = 1;
  public static final int P_SMALLER_EQ = 25;
  public static final int K_IF = 76;
  public static final int P_AND_ASSIGN = 45;
  public static final int NK_NORACE = 105;
  public static final int K_RESTRICT = 70;
  public static final int P_ASSIGN = 37;
  public static final int P_TILDE = 17;
  public static final int NK_EVENT = 103;
  public static final int NK_CALL = 107;
  public static final int NK_USES = 91;
  public static final int P_OR = 32;
  public static final int K_FOR = 80;
  public static final int P_ROUND_CLOSE = 6;
  public static final int STRING = 117;
  public static final int P_GREATER_EQ = 26;
  public static final int K_WHILE = 78;
  public static final int K_ENUM = 68;
  public static final int K_UNION = 67;
  public static final int K_INT = 58;
  public static final int NK_ASYNC = 104;
  public static final int TYPEDEF = 118;
  public static final int NP_LEFT_ARROW = 88;
  public static final int P_XOR_ASSIGN = 46;
  public static final int P_INCREMENT = 11;
  public static final int K_CONTINUE = 82;
  public static final int P_SLASH = 19;
  public static final int NK_PROVIDES = 90;
  public static final int K_UNSIGNED = 63;
}
