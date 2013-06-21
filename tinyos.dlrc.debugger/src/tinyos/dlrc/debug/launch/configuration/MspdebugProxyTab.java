/*
 * Dlrc 2, NesC development in Eclipse.
 * Copyright (C) 2013 DLRC
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
 * Web:  
 * Mail: ruishengleen@gmail.com
 */
package tinyos.dlrc.debug.launch.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tinyos.dlrc.debug.TinyOSDebugPlugin;

public class MspdebugProxyTab extends AbstractTinyOSDebuggerTab implements IGdbProxyConfigurationTab{
	public static final String PROXY_CONFIG_ID = ITinyOSDebugLaunchConstants.TINYOS_DBG_LAUNCH_ID + ".mspdebugProxyTab";
	public static final String ATTR_DRIVER = PROXY_CONFIG_ID + ".driver";
	public static final String ATTR_USB_BUTTON = PROXY_CONFIG_ID + ".usbButton";
	public static final String ATTR_USB_SERIAL = PROXY_CONFIG_ID + ".usbSerial";
	public static final String ATTR_TTY_BUTTON = PROXY_CONFIG_ID + ".ttyButton";
	public static final String ATTR_TTY_STRING = PROXY_CONFIG_ID + ".ttyString";
	public static final String ATTR_PROTOCOL = PROXY_CONFIG_ID + ".protocol";
	public static final String ATTR_GDB_PORT = PROXY_CONFIG_ID + ".port";
	
	private Combo driverCombo;
	private Button usbConnectionButton;
	private Combo usbDeviceCombo; 
	private Button ttyConnectionButton;
	private Text ttyDevice;
	private Combo protocolCombo;
	private Text gdbPort;
	
	public enum DriverFlag {
		USB_ACCESS,
		TTY_ACCESS
	}
	
	public enum Driver {
		RF2500("rf2500", DriverFlag.USB_ACCESS),
		OLIMEX("olimex", DriverFlag.TTY_ACCESS,DriverFlag.USB_ACCESS),
		OLIMEX_ISO("olimex-iso", DriverFlag.USB_ACCESS),
		SIM("sim"),
		UIF("uif", DriverFlag.TTY_ACCESS,DriverFlag.USB_ACCESS),
		FT232H("ft232h", DriverFlag.USB_ACCESS),
		UIF_BSL("uif-bsl", DriverFlag.TTY_ACCESS),
		FLASH_BSL("flash-bsl", DriverFlag.TTY_ACCESS),
		GDBC("gdbc"),
		TILIB("tilib");
		
		private final String id;
		private final DriverFlag[] flags;
		
		Driver(String id, DriverFlag... flags) {
			this.id = id;
			this.flags = flags;
		}
		
		public String getId() {
			return id;
		}
		
		public DriverFlag[] getFlags() {
			return flags;
		}
		
		public boolean hasFlag(DriverFlag flag) {
			for(DriverFlag f: flags) {
				if( flag.equals(f)) {
					return true;
				}
			}
			return false;
		}
	}
		
	@Override
	public String getCommand(){
		String command = "mspdebug";
		if(usbConnectionButton.getSelection()) {
			command = command + " -s " + usbDeviceCombo.getText();
		} else if (ttyConnectionButton.getSelection()) {
			command = command + " -d " + ttyDevice.getText();
		}
		
		if(protocolCombo.getSelectionIndex() == 1) {
			command = command + " -j";
		}
		
		command = command + " " + driverCombo.getText() + " \"gdb " + gdbPort.getText() + "\"";
		return command;
	}

	@Override
	public String getID(){
		return PROXY_CONFIG_ID;
	}

	
	@Override
	public void createControl( Composite parent ){
		Composite content = new Composite( parent, SWT.NONE );
		content.setLayout( new GridLayout( 2, false ) );
		content.setLayoutData( new GridData( SWT.LEFT, SWT.TOP, false, false ) );

		createMSPDebugSelection( content );
		createPortSetting( content );
	}
	
	@Override
	public String getName(){
		return "mspdebug";
	}

	@Override
	public void initializeFrom( ILaunchConfiguration configuration ){
		setInitializing(true);

		int driverIndex = 0;
		boolean usbConnection = true;
		String usbSerial = "";
		boolean ttyConnection = false;
		String ttystring = "";
		int protocolIndex = 0;
		String gdbPortText = "";
		try {
			driverIndex = configuration.getAttribute( ATTR_DRIVER,  5);//ft232h
			usbConnection = configuration.getAttribute( ATTR_USB_BUTTON, true);
			usbSerial = configuration.getAttribute( ATTR_USB_SERIAL,  "");
			ttyConnection = configuration.getAttribute( ATTR_TTY_BUTTON,  false);
			ttystring = configuration.getAttribute( ATTR_TTY_STRING,  "/dev/ttyUSB0");
			protocolIndex = configuration.getAttribute( ATTR_PROTOCOL,  1); //JTAG
			gdbPortText = configuration.getAttribute( ATTR_GDB_PORT,  "7000");
		} catch (CoreException ce) {
			TinyOSDebugPlugin.getDefault().log("Exception while initializing mspdebug proxy tab", ce);
		}
		driverCombo.select(driverIndex);
		usbConnectionButton.setSelection(usbConnection);
		usbDeviceCombo.add(usbSerial);
		usbDeviceCombo.select(0);
		ttyConnectionButton.setSelection(ttyConnection);
		ttyDevice.setText(ttystring);
		protocolCombo.select(protocolIndex);
		gdbPort.setText(gdbPortText);
		
		setInitializing(false);
	}

	@Override
	public void performApply( ILaunchConfigurationWorkingCopy configuration ){
		if( isDirty() ){
			configuration.setAttribute( ATTR_DRIVER,  driverCombo.getSelectionIndex());
			configuration.setAttribute( ATTR_USB_BUTTON, usbConnectionButton.getSelection());
			configuration.setAttribute( ATTR_USB_SERIAL,  usbDeviceCombo.getText());
			configuration.setAttribute( ATTR_TTY_BUTTON,  ttyConnectionButton.getSelection());
			configuration.setAttribute( ATTR_TTY_STRING,  ttyDevice.getText());
			configuration.setAttribute( ATTR_PROTOCOL,  protocolCombo.getSelectionIndex());
			configuration.setAttribute( ATTR_GDB_PORT,  gdbPort.getText());
			//setDirty(false);
		}
	}

	@Override
	public void setDefaults( ILaunchConfigurationWorkingCopy configuration ){
		configuration.setAttribute( ATTR_DRIVER,  5);//ft232h
		configuration.setAttribute( ATTR_USB_BUTTON, true);
		configuration.setAttribute( ATTR_USB_SERIAL,  "");
		configuration.setAttribute( ATTR_TTY_BUTTON,  false);
		configuration.setAttribute( ATTR_TTY_STRING,  "/dev/ttyUSB0");
		configuration.setAttribute( ATTR_PROTOCOL,  1); //JTAG
		configuration.setAttribute( ATTR_GDB_PORT,  "7000");
	}
	
	private boolean gdbServerPortIsValid() {
		try {
			int port = Integer.parseInt( gdbPort.getText() );
			return ( port > 0 && port <= 0xFFFF );
		}
		catch( NumberFormatException e ) {
			return false;
		}
	}
	
	private void createPortSetting( Composite parent ){
		Label gdbServerPortLabel = new Label( parent, SWT.NONE );
		gdbServerPortLabel.setText("Listen for GDB on port"); //$NON-NLS-1$
		gdbServerPortLabel.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, false, false ) );

		GridData data;
		gdbPort = new Text( parent, SWT.SINGLE | SWT.BORDER );
		gdbPort.setLayoutData( data = new GridData( SWT.LEFT, SWT.CENTER, true, false ));
		data.widthHint = 45;
		gdbPort.setText("7000");
		gdbPort.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				String error = "Invalid server port";
				if(!gdbServerPortIsValid()) {
					setErrorCondition(error);
				}
				else {
					removeErrorCondition(error);
				}
				if ( !isInitializing() ) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});
	}
	
	private void getUSBDevices() {
		ProcessBuilder pb = new ProcessBuilder("mspdebug",
				"--usb-list");
		Process p;
		try {
			p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		BufferedReader lineReader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
	
		Pattern descPattern = Pattern.compile("(.*)\\[serial: (.*)\\]");
		
		usbDeviceCombo.removeAll();
		while(true) {
			String line;
			try {
				line = lineReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			if( line == null ) { 
				break;
			}
			if( line.startsWith("Devices on")) {
				continue;
			}
			
			line = line.trim();
			String[] fields = line.split(" ", 3);
			if( fields.length < 3 )
				continue;

			String description = "";
			description = fields[2];

			Matcher m = descPattern.matcher(description);
			if( m.matches() ) {
				String serial = m.group(2);
				if( serial.length() < 3 )
					continue;
				
				usbDeviceCombo.add(serial);		
			}
		}
	}
	
	private void updateDriverConnection() {
		//device --> button & protocol
		String value = driverCombo.getText();
		for (Driver d : Driver.values()) {
			if (value.equals(d.getId())) {
				boolean usbaccess = d.hasFlag(DriverFlag.USB_ACCESS);
				boolean ttyaccess = d.hasFlag(DriverFlag.TTY_ACCESS);
				usbConnectionButton.setEnabled(usbaccess);
				usbDeviceCombo.setEnabled(usbaccess);
				
				ttyConnectionButton.setEnabled(ttyaccess);
				ttyDevice.setEnabled(ttyaccess);
				
				if(usbaccess != ttyaccess) {
					usbConnectionButton.setSelection(usbaccess);
					ttyConnectionButton.setSelection(ttyaccess);
				}
				
				break;
			}
		}
	}
	
	private void createMSPDebugSelection(Composite parent) {
		ModifyListener setDirtyModifyListener = new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				if ( !isInitializing() ) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		};
		
		SelectionListener buttonSelectionListener = new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ( !isInitializing() ) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing
			}
		};
		
		//driver selection
		Label label = new Label(parent, SWT.NONE);
		label.setText("Driver:");
		label.setLayoutData(new GridData());
		
		driverCombo = new Combo(parent, SWT.READ_ONLY);
		for (Driver d : Driver.values()) {
			driverCombo.add(d.getId());
		}
		driverCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				if ( !isInitializing() ) {
					updateDriverConnection();
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});
		
		//connection selection
		label = new Label(parent, SWT.NONE);
		label.setText("Connection:");
		label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
	
		Composite group = new Composite(parent, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
		//usb serial
		usbConnectionButton = new Button(group, SWT.RADIO);
		usbConnectionButton.setText("USB serial");
		usbConnectionButton.setLayoutData(new GridData());
		usbConnectionButton.addSelectionListener(buttonSelectionListener);
		
		usbDeviceCombo = new Combo(group, SWT.READ_ONLY);
		usbDeviceCombo.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		getUSBDevices();
		usbDeviceCombo.addModifyListener(setDirtyModifyListener);

		//tty
		ttyConnectionButton = new Button(group, SWT.RADIO);
		ttyConnectionButton.setText("TTY:");
		ttyConnectionButton.addSelectionListener(buttonSelectionListener);
		
		ttyDevice = new Text(group, SWT.BORDER);
		GridData gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gridData.grabExcessHorizontalSpace = true;
		gridData.widthHint = 150;
		gridData.verticalSpan = 2;
		ttyDevice.setLayoutData(gridData);
		ttyDevice.addModifyListener(setDirtyModifyListener);
		
		//protocal seletion
		label = new Label(parent, SWT.NONE);
		label.setText("Protocol:");
		label.setLayoutData(new GridData());
		
		protocolCombo = new Combo(parent, SWT.READ_ONLY);
		protocolCombo.add("SBW");
		protocolCombo.add("JTAG");
		protocolCombo.addModifyListener(setDirtyModifyListener);
	}	
}
