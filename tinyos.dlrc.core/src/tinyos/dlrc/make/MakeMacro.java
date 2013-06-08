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
package tinyos.dlrc.make;

import tinyos.dlrc.ep.parser.IMacro;

/**
 * Information about a macro that may be included in all files.
 * @author Benjamin Sigg
 */
public class MakeMacro{
	private IMacro macro;
	private boolean includeDlrc;
	private boolean includeNcc;
	
	/**
	 * Creates a new macro.
	 * @param macro the basic information about this macro
	 * @param includeDlrc whether the macro is included in all files within dlrc
	 * @param includeNcc whether the macro is included in ncc
	 */
	public MakeMacro( IMacro macro, boolean includeDlrc, boolean includeNcc ){
		this.macro = macro;
		this.includeNcc = includeNcc;
		this.includeDlrc = includeDlrc;
	}
	
	public IMacro getMacro(){
		return macro;
	}
	
	public boolean isIncludeNcc(){
		return includeNcc;
	}
	
	public boolean isIncludeDlrc(){
		return includeDlrc;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + (includeNcc ? 1231 : 1237);
		result = prime * result + (includeDlrc ? 1231 : 1237);
		result = prime * result + ((macro == null) ? 0 : macro.hashCode());
		return result;
	}

	@Override
	public boolean equals( Object obj ){
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		MakeMacro other = (MakeMacro)obj;
		if( includeNcc != other.includeNcc )
			return false;
		if( includeDlrc != other.includeDlrc )
			return false;
		if( macro == null ){
			if( other.macro != null )
				return false;
		}
		else if( !macro.equals( other.macro ) )
			return false;
		return true;
	}
}
