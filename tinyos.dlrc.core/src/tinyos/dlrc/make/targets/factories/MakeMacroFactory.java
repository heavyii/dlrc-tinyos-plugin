package tinyos.dlrc.make.targets.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;

import tinyos.dlrc.TinyOSPlugin;
import tinyos.dlrc.ep.parser.IMacro;
import tinyos.dlrc.ep.parser.macros.ConstantMacro;
import tinyos.dlrc.make.MakeMacro;
import tinyos.dlrc.make.targets.IMakeTargetPropertyFactory;
import tinyos.dlrc.make.targets.MakeTargetPropertyKey;
import tinyos.dlrc.utility.XReadStack;
import tinyos.dlrc.utility.XWriteStack;

public class MakeMacroFactory  implements IMakeTargetPropertyFactory<MakeMacro[]>{
	public boolean supportsXML(){
		return true;
	}
	
	public void write( MakeMacro[] value, XWriteStack xml ){
		if( value == null )
			return;
		
		for( MakeMacro make : value ){
			IMacro macro = make.getMacro();
			if( macro instanceof ConstantMacro ){
				ConstantMacro constant = (ConstantMacro)macro;
				
				xml.push( "macro" );
				xml.setAttribute( "name", constant.getName() );
				xml.setAttribute( "content", constant.getConstant() );
				xml.setAttribute( "ncc", make.isIncludeNcc() ? "true" : "false" );
				xml.setAttribute( "dlrc", make.isIncludeDlrc() ? "true" : "false" );
				xml.pop();
			}
		}
	}
	
	public MakeMacro[] read( XReadStack xml ){
		List<MakeMacro> macros = new ArrayList<MakeMacro>();
		
		while( xml.go( "macro" )){
			IMacro macro = new ConstantMacro(
				xml.getString( "name", "name" ),
				xml.getString( "content", "content" ));
			
			boolean includeDlrc = xml.getBoolean( "dlrc", true );
			boolean includeNcc = xml.getBoolean( "ncc", false );
			
			macros.add( new MakeMacro( macro, includeDlrc, includeNcc ) );
			
			xml.pop();
		}
		
		return macros.toArray( new MakeMacro[ macros.size() ] );
	}
	
	public void write( MakeMacro[] value, MakeTargetPropertyKey<MakeMacro[]> key,
			ILaunchConfigurationWorkingCopy configuration ){

	 	List<String> macroNames = new ArrayList<String>();
	 	List<String> macroValues = new ArrayList<String>();
	 	List<String> includeDlrc = new ArrayList<String>();
	 	List<String> includeNcc = new ArrayList<String>();
	 	
	 	if( value != null ){
	 		for( MakeMacro make : value ){
	 			IMacro macro = make.getMacro();
	 			if( macro instanceof ConstantMacro ){
	 				ConstantMacro constant = (ConstantMacro)macro;
	 				
	 				macroNames.add( constant.getName() );
	 				macroValues.add( constant.getConstant() );
	 				includeDlrc.add( make.isIncludeDlrc() ? "true" : "false" );
	 				includeNcc.add( make.isIncludeNcc() ? "true" : "false" );
	 			}
	 		}
	 	}
	 	
	 	configuration.setAttribute( "tinyos." + key.getName() + ".names", macroNames );
	 	configuration.setAttribute( "tinyos." + key.getName() + ".values", macroValues );
	 	configuration.setAttribute( "tinyos." + key.getName() + ".include.dlrc", includeDlrc );
	 	configuration.setAttribute( "tinyos." + key.getName() + ".include.ncc", includeNcc );
	}
	
	@SuppressWarnings("unchecked")
	public MakeMacro[] read( MakeTargetPropertyKey<MakeMacro[]> key, ILaunchConfiguration configuration ){
		
		try{
			List<String> macroNames = configuration.getAttribute( "tinyos." + key.getName() + ".names", Collections.EMPTY_LIST );
			List<String> macroValues = configuration.getAttribute( "tinyos." + key.getName() + ".values", Collections.EMPTY_LIST );
			List<String> includeDlrc = configuration.getAttribute( "tinyos." + key.getName() + ".include.dlrc", Collections.EMPTY_LIST );
			List<String> includeNcc = configuration.getAttribute( "tinyos." + key.getName() + ".include.ncc", Collections.EMPTY_LIST );
			
			MakeMacro[] result = new MakeMacro[ Math.min( macroNames.size(), macroValues.size() )];
			for( int i = 0; i < result.length; i++ ){
				IMacro macro = new ConstantMacro( macroNames.get( i ), macroValues.get( i ));
				
				boolean dlrc = i >= includeDlrc.size() || Boolean.parseBoolean( includeDlrc.get( i ) );
				boolean ncc = i < includeNcc.size() && Boolean.parseBoolean( includeNcc.get( i ) );
				
				result[i] = new MakeMacro( macro, dlrc, ncc );
			}
			return result;
		}
		catch( CoreException ex ){
			TinyOSPlugin.log( ex.getStatus() );
			return new MakeMacro[]{};
		}
	}
}