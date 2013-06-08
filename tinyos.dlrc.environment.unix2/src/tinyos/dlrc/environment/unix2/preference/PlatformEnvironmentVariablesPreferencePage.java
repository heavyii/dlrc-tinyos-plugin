package tinyos.dlrc.environment.unix2.preference;

import tinyos.dlrc.environment.basic.preferences.AbstractPlatformEnvironmentVariablesPreferencePage;
import tinyos.dlrc.environment.unix2.Environment;
import tinyos.dlrc.environment.unix2.platform.Platform;
import tinyos.dlrc.environment.unix2.platform.PlatformManager;
import tinyos.dlrc.ep.IEnvironment;
import tinyos.dlrc.ep.IPlatform;
import tinyos.dlrc.make.EnvironmentVariable;

public class PlatformEnvironmentVariablesPreferencePage extends
		AbstractPlatformEnvironmentVariablesPreferencePage{

	@Override
	protected EnvironmentVariable[] getDefaults( IPlatform platform ){
		return new EnvironmentVariable[]{};
	}

	@Override
	protected IEnvironment getEnvironment(){
		return Environment.getEnvironment();
	}

	@Override
	protected IPlatform[] getPlatforms(){
		return getEnvironment().getPlatforms();
	}

	@Override
	protected EnvironmentVariable[] getVariables( IPlatform platform ){
		if( platform == null ){
			return getManager().getDefaultEnvironmentVariables();
		}
		else{
			return ((Platform)platform).getEnvironmentVariables();
		}
	}

	@Override
	protected void setVariables( IPlatform platform, EnvironmentVariable[] variables ){
		if( platform == null ){
			getManager().setDefaultVariables( variables );
		}
		else{
			((Platform)platform).setEnvironmentVariables( variables );
		}
	}


    private PlatformManager getManager(){
    	return Environment.getEnvironment().getPlatformManager();
    }
}
