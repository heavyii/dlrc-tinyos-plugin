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
package tinyos.dlrc.nesc12.ep.rules;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import tinyos.dlrc.ProjectTOS;
import tinyos.dlrc.ep.IParseFile;
import tinyos.dlrc.ep.fix.ISingleMarkerResolution;
import tinyos.dlrc.ep.fix.ISingleQuickFixer;
import tinyos.dlrc.nesc12.ep.rules.quickfix.ISingleQuickfixRule;
import tinyos.dlrc.nesc12.ep.rules.quickfix.QuickfixCollector;
import tinyos.dlrc.preprocessor.output.Insight;
import tinyos_parser.NesC12ParserPlugin;

/**
 * A {@link ISingleQuickFixer} that is used to generate quick-fixes for errors
 * which were reported by this parser.
 * @author Benjamin Sigg
 */
public class NesC12SingleQuickFixer implements ISingleQuickFixer{

    @SuppressWarnings("unchecked")
    public ISingleMarkerResolution[] getResolutions( IMarker marker, IParseFile parseFile, ProjectTOS project ){
        try{
            ISingleQuickfixRule[] rules = NesC12ParserPlugin.getDefault().getSingleQuickfixRules();

            Insight error = new Insight( marker.getAttributes() );

            QuickfixCollector collector = new QuickfixCollector( parseFile, project );
            
            for( ISingleQuickfixRule rule : rules ){
                rule.suggest( error, collector );
            }

            return collector.getSingleResolutions();
        }
        catch( CoreException ex ){
            NesC12ParserPlugin.getDefault().getLog().log( ex.getStatus() );
            return null;
        }
    }
}
