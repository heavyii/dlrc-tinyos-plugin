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
package tinyos.dlrc.launch.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

import tinyos.dlrc.ProjectTOS;
import tinyos.dlrc.ep.IEnvironment;
import tinyos.dlrc.ep.IPlatform;
import tinyos.dlrc.launch.LaunchConverter;
import tinyos.dlrc.make.MakeTarget;
import tinyos.dlrc.make.dialog.IMakeTargetInformation;
import tinyos.dlrc.make.dialog.pages.ComponentPage;
import tinyos.dlrc.make.dialog.pages.EnvironmentVariablesPage;
import tinyos.dlrc.make.dialog.pages.ExcludePage;
import tinyos.dlrc.make.dialog.pages.ExtraPage;
import tinyos.dlrc.make.dialog.pages.IncludePage;
import tinyos.dlrc.make.dialog.pages.PlatformPage;
import tinyos.dlrc.make.dialog.pages.SensorPage;

public class MakeTargetLaunchTabGroup extends AbstractLaunchConfigurationTabGroup{
	private MakeTarget currentTarget;
	private IMakeTargetInformation information;
	
	private MakeTargetLaunchTab[] makeTabs;
	
	public MakeTargetLaunchTabGroup(){
		// nothing
	}
	
	public void createTabs( ILaunchConfigurationDialog dialog, String mode ){
		information = new IMakeTargetInformation(){
			public IEnvironment getEnvironment(){
				if( currentTarget == null )
					return null;
				
				ProjectTOS project = currentTarget.getProjectTOS();
				if( project == null )
					return null;
				
				return project.getEnvironment();
			}
			
			public IPlatform[] getPlatforms(){
				IEnvironment environment = getEnvironment();
				if( environment == null )
					return null;
				
				return environment.getPlatforms();
			}
			
			public IPlatform getSelectedPlatform(){
				if( currentTarget == null )
					return null;
				
				return currentTarget.getPlatform();
			}
		};
		
		makeTabs = new MakeTargetLaunchTab[]{
				new MakeTargetLaunchTab( new ComponentPage( true ), this ),
				new MakeTargetLaunchTab( new IncludePage( true, true, true ), this ),
				new MakeTargetLaunchTab( new ExcludePage( true ), this ),
				new MakeTargetLaunchTab( new PlatformPage( true ), this ),
				new MakeTargetLaunchTab( new ExtraPage( true ), this ),
				new MakeTargetLaunchTab( new SensorPage( true ), this ),
				new MakeTargetLaunchTab( new EnvironmentVariablesPage( true ), this ),
		};
		
		List<ILaunchConfigurationTab> tabs = new ArrayList<ILaunchConfigurationTab>();
		tabs.add( new ProjectTab( this ) );
		
		for( MakeTargetLaunchTab tab : makeTabs ){
			tabs.add( tab );
		}
		
		tabs.add( new CommonTab() );
		setTabs( tabs.toArray( new ILaunchConfigurationTab[ tabs.size() ] ) );
	}
	
	public MakeTarget getCurrentTarget(){
		return currentTarget;
	}
	
	public IMakeTargetInformation getInformation(){
		return information;
	}
	
	@Override
	public void initializeFrom( ILaunchConfiguration configuration ){
		currentTarget = LaunchConverter.read( configuration );
		super.initializeFrom( configuration );
	}
	
	public void check(){
		for( MakeTargetLaunchTab tab : makeTabs ){
			tab.check( currentTarget );
		}
	}
	
	public void store( ILaunchConfigurationWorkingCopy configuration ){
		LaunchConverter.write( currentTarget, configuration );
	}
}
