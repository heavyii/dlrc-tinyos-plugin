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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
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
import tinyos.dlrc.debug.CDTAbstractionLayer.CDTLaunchConfigConst;

public class SnapGateProxyTab extends AbstractTinyOSDebuggerTab implements
		IGdbProxyConfigurationTab {
	public static final String PROXY_CONFIG_ID = ITinyOSDebugLaunchConstants.TINYOS_DBG_LAUNCH_ID
			+ ".snapgateProxyTab";
	public static final String ATTR_IP_LIST = PROXY_CONFIG_ID + ".ipList";
	public static final String ATTR_IP = PROXY_CONFIG_ID + ".ip";
	public static final String ATTR_DRIVER = PROXY_CONFIG_ID + ".driver";
	public static final String ATTR_USB_BUTTON = PROXY_CONFIG_ID + ".usbButton";
	public static final String ATTR_USB_SERIAL = PROXY_CONFIG_ID + ".usbSerial";
	public static final String ATTR_TTY_BUTTON = PROXY_CONFIG_ID + ".ttyButton";
	public static final String ATTR_TTY_STRING = PROXY_CONFIG_ID + ".ttyString";
	public static final String ATTR_PROTOCOL = PROXY_CONFIG_ID + ".protocol";
	public static final String ATTR_GDB_PORT = PROXY_CONFIG_ID + ".gdbPort";
	public static final String ATTR_SERVER_PORT = PROXY_CONFIG_ID + ".serverPort";
	public static final String ATTR_UPLOAD_CHECK = PROXY_CONFIG_ID
			+ ".uploadCheck";
	public static final String ATTR_PROGRAM_PATH = PROXY_CONFIG_ID
			+ ".programPath";
	
	private List<String> snapgateIPList;
	private Combo snapgateCombo;
	private Button snapgateAddButton;
	private Button snapgateDelButton;
	private Combo driverCombo;
	private Button usbListButton;
	private Button usbConnectionButton;
	private Combo usbDeviceCombo;
	private Button ttyConnectionButton;
	private Text ttyDevice;
	private Combo protocolCombo;
	private Text gdbPort;
	private Text serverPort;
	private String programPathName;
	private Button uploadCheck;
	private Text programPath;
	private Button programDefault;
	
	public enum DriverFlag {
		USB_ACCESS, TTY_ACCESS
	}

	public enum Driver {
		RF2500("rf2500", DriverFlag.USB_ACCESS), OLIMEX("olimex",
				DriverFlag.TTY_ACCESS, DriverFlag.USB_ACCESS), OLIMEX_ISO(
				"olimex-iso", DriverFlag.USB_ACCESS), SIM("sim"), UIF("uif",
				DriverFlag.TTY_ACCESS, DriverFlag.USB_ACCESS), FT232H("ft232h",
				DriverFlag.USB_ACCESS), UIF_BSL("uif-bsl",
				DriverFlag.TTY_ACCESS), FLASH_BSL("flash-bsl",
				DriverFlag.TTY_ACCESS), GDBC("gdbc"), TILIB("tilib");

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
			for (DriverFlag f : flags) {
				if (flag.equals(f)) {
					return true;
				}
			}
			return false;
		}
	}

	private String getProgramName() {
		return "mspdebug-snapgate";
	}
	
	@Override
	public String getCommand() {
		String command = getProgramName();
		command = command + " -s " + usbDeviceCombo.getText()
				+ " -p " + gdbPort.getText()
				+ " -i " + snapgateCombo.getText()
				+ " -P " + serverPort.getText();
		
		if(uploadCheck.getSelection())
			command = command + " -f " + programPath.getText();
		
		return command;
	}

	@Override
	public String getID() {
		return PROXY_CONFIG_ID;
	}

	private void createProgramPathSetting(Composite parent) {
		uploadCheck = createCheckButton(parent, "upload program");
		
		GridData data;
		programPath = new Text(parent, SWT.SINGLE | SWT.BORDER);
		programPath.setLayoutData(data = new GridData(SWT.LEFT, SWT.CENTER,
				true, false));
		data.widthHint = 600;
		programPath.setText("");
		programPath.setEnabled(false);
		programPath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				if (!isInitializing()) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});
		uploadCheck.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				programPath.setEnabled(uploadCheck.getSelection());
				if(!programPathName.isEmpty()) {
					programPath.setText(programPathName);
				}
				if (!isInitializing()) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});
	}
	
	@Override
	public void createControl(Composite parent) {

		Composite content = new Composite(parent, SWT.NONE);
		content.setLayout(new GridLayout(2, false));
		content.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

		createSnapGateSelection(content);
		createMSPDebugSelection(content);
		createPortSetting(content);
		createServerPortSetting(content);
		createProgramPathSetting(content);
	}

	@Override
	public String getName() {
		return "SnapGate";
	}

	private String getProgramPath(ILaunchConfiguration configuration) {
		String projectString = "";
		String programString = "";
		try {
			projectString = configuration.getAttribute(
					CDTLaunchConfigConst.ATTR_PROJECT_NAME, "");
			programString = configuration.getAttribute(
					CDTLaunchConfigConst.ATTR_PROGRAM_NAME, "");
		} catch (CoreException ce) {
			TinyOSDebugPlugin.getDefault().log(
					"Exception while initializing mspdebug proxy tab", ce);
		}

		if (projectString.length() < 1) {
			return null;
		}
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectString);
		programString = project.getLocation().toString() + "/" + programString;
		return programString;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		setInitializing(true);
		
		try {
			snapgateIPList = new ArrayList<String>();
			List<String> defaultList = new ArrayList<String>();
			defaultList.add("192.168.14.100");
			snapgateIPList = configuration.getAttribute(ATTR_IP_LIST, defaultList);
			for(String text:snapgateIPList)
			{
				snapgateCombo.add(text);
			}
			snapgateCombo.setText(configuration.getAttribute(ATTR_IP, "192.168.14.100"));
			driverCombo.select(configuration.getAttribute(ATTR_DRIVER, 5));// ft232h
			usbConnectionButton.setSelection(configuration.getAttribute(ATTR_USB_BUTTON, true));
			usbDeviceCombo.add(configuration.getAttribute(ATTR_USB_SERIAL, "SNP0001"));
			usbDeviceCombo.select(0);
			ttyConnectionButton.setSelection(configuration.getAttribute(ATTR_TTY_BUTTON, false));
			ttyDevice.setText(configuration.getAttribute(ATTR_TTY_STRING,
					"/dev/ttyUSB0"));
			protocolCombo.select(configuration.getAttribute(ATTR_PROTOCOL, 1)); // JTAG
			gdbPort.setText(configuration.getAttribute(ATTR_GDB_PORT, "7000"));
			serverPort.setText(configuration.getAttribute(ATTR_SERVER_PORT, "32000"));
			uploadCheck.setSelection(configuration.getAttribute(
					ATTR_UPLOAD_CHECK, false));
			programPath.setText(configuration.getAttribute(ATTR_PROGRAM_PATH,
					""));
		} catch (CoreException ce) {
			TinyOSDebugPlugin.getDefault().log(
					"Exception while initializing mspdebug proxy tab", ce);
		}
		
		programPath.setEnabled(uploadCheck.getSelection());
		programPathName = getProgramPath(configuration);

		setInitializing(false);
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		if (isDirty()) {
			configuration.setAttribute(ATTR_IP_LIST, snapgateIPList);
			configuration.setAttribute(ATTR_IP, snapgateCombo.getText());
			configuration.setAttribute(ATTR_DRIVER,
					driverCombo.getSelectionIndex());
			configuration.setAttribute(ATTR_USB_BUTTON,
					usbConnectionButton.getSelection());
			configuration.setAttribute(ATTR_USB_SERIAL,
					usbDeviceCombo.getText());
			configuration.setAttribute(ATTR_TTY_BUTTON,
					ttyConnectionButton.getSelection());
			configuration.setAttribute(ATTR_TTY_STRING, ttyDevice.getText());
			configuration.setAttribute(ATTR_PROTOCOL,
					protocolCombo.getSelectionIndex());
			configuration.setAttribute(ATTR_GDB_PORT, gdbPort.getText());
			configuration.setAttribute(ATTR_SERVER_PORT, serverPort.getText());
			configuration.setAttribute(ATTR_UPLOAD_CHECK, uploadCheck.getSelection());
			configuration.setAttribute(ATTR_PROGRAM_PATH, programPath.getText());
		}
	}

	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		List<String> defaultList = new ArrayList<String>();
		defaultList.add("192.168.14.100");
		configuration.setAttribute(ATTR_IP_LIST, defaultList);
		configuration.setAttribute(ATTR_IP, "192.168.14.100");
		configuration.setAttribute(ATTR_DRIVER, 5);// ft232h
		configuration.setAttribute(ATTR_USB_BUTTON, true);
		configuration.setAttribute(ATTR_USB_SERIAL, "SNP0001");
		configuration.setAttribute(ATTR_TTY_BUTTON, false);
		configuration.setAttribute(ATTR_TTY_STRING, "/dev/ttyUSB0");
		configuration.setAttribute(ATTR_PROTOCOL, 1); // JTAG
		configuration.setAttribute(ATTR_GDB_PORT, "7000");
		configuration.setAttribute(ATTR_SERVER_PORT, "32000");
		configuration.setAttribute(ATTR_UPLOAD_CHECK, false);
		configuration.setAttribute(ATTR_PROGRAM_PATH, "");
	}

	private boolean portIsValid(String text) {
		try {
			int port = Integer.parseInt(text);
			return (port > 0 && port <= 0xFFFF);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private void createPortSetting(Composite parent) {
		Label gdbServerPortLabel = new Label(parent, SWT.NONE);
		gdbServerPortLabel.setText("Listen for GDB on port"); //$NON-NLS-1$
		gdbServerPortLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false));

		GridData data;
		gdbPort = new Text(parent, SWT.SINGLE | SWT.BORDER);
		gdbPort.setLayoutData(data = new GridData(SWT.LEFT, SWT.CENTER, true,
				false));
		data.widthHint = 45;
		gdbPort.setText("7000");
		gdbPort.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				String error = "Invalid server port";
				if (!portIsValid(gdbPort.getText())) {
					setErrorCondition(error);
				} else {
					removeErrorCondition(error);
				}
				if (!isInitializing()) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});
	}

	private void createServerPortSetting(Composite parent) {
		Label serverPortLabel = new Label(parent, SWT.NONE);
		serverPortLabel.setText("Listen for SnapGate on port"); //$NON-NLS-1$
		serverPortLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false));

		GridData data;
		serverPort = new Text(parent, SWT.SINGLE | SWT.BORDER);
		serverPort.setLayoutData(data = new GridData(SWT.LEFT, SWT.CENTER, true,
				false));
		data.widthHint = 45;
		serverPort.setText("32000");
		serverPort.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				String error = "Invalid server port";
				if (!portIsValid(serverPort.getText())) {
					setErrorCondition(error);
				} else {
					removeErrorCondition(error);
				}
				if (!isInitializing()) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});
	}
	
	private void getUSBDevices() {
		if(snapgateCombo.getText().isEmpty() || serverPort.getText().isEmpty())
			return;
		ProcessBuilder pb = new ProcessBuilder(getProgramName(), "-l", "-i", snapgateCombo.getText(), "-P", serverPort.getText());
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

		String usbDeviceText = usbDeviceCombo.getText();
		usbDeviceCombo.removeAll();
		while (true) {
			String line;
			try {
				line = lineReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			if (line == null) {
				break;
			}
			if (line.startsWith("Devices on")) {
				continue;
			}

			line = line.trim();
			String[] fields = line.split(" ", 3);
			if (fields.length < 3)
				continue;

			String description = "";
			description = fields[2];

			Matcher m = descPattern.matcher(description);
			if (m.matches()) {
				String serial = m.group(2);
				if (serial.length() < 3)
					continue;

				usbDeviceCombo.add(serial);
			}
		}
		usbDeviceCombo.setText(usbDeviceText);
	}

	private void updateDriverConnection() {
		// device --> button & protocol
		String value = driverCombo.getText();
		for (Driver d : Driver.values()) {
			if (value.equals(d.getId())) {
				boolean usbaccess = d.hasFlag(DriverFlag.USB_ACCESS);
				boolean ttyaccess = d.hasFlag(DriverFlag.TTY_ACCESS);
				usbConnectionButton.setEnabled(usbaccess);
				usbDeviceCombo.setEnabled(usbaccess);

				ttyConnectionButton.setEnabled(ttyaccess);
				ttyDevice.setEnabled(ttyaccess);

				if (usbaccess != ttyaccess) {
					usbConnectionButton.setSelection(usbaccess);
					ttyConnectionButton.setSelection(ttyaccess);
				}

				break;
			}
		}
	}

	private void createSnapGateSelection(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("SnapGate:");
		label.setLayoutData(new GridData());
		
		Composite content = new Composite( parent, SWT.NONE );
		content.setLayout( new GridLayout( 3, false ) );
		content.setLayoutData( new GridData( SWT.LEFT, SWT.TOP, false, false ) );

		snapgateCombo = new Combo(content, SWT.READ_ONLY);
		snapgateCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				if (!isInitializing()) {
					//getUSBDevices();
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});

		snapgateAddButton = new Button(content, SWT.PUSH);
		snapgateAddButton.setText("Add");
		snapgateAddButton.setLayoutData(new GridData());
		snapgateAddButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog m = new InputDialog(null, "SnapGate", "SnapGate IP", "192.168.14.100", 
						new IInputValidator(){ 
					public String isValid(String newText) 
					{
						String errmsg = "invalid IP";
						Matcher match = Pattern.compile("((\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3}))").matcher(newText);
						if (match.matches())
						{
							for(int i=2; i <= 5; i++)
							{
								short s=Short.parseShort(match.group(i));
								if(s<=0 || s>=255){
									return errmsg;
								}		
							}

							return null;
						}
						
						return errmsg;
					}
				});
				 if(m.open()==Window.OK) 
			       { 
					 	if(!m.getValue().isEmpty())
					 	{				 		
				            if(snapgateIPList.indexOf(m.getValue()) == -1)
				            {
					            snapgateIPList.add(m.getValue());
					            snapgateCombo.add(m.getValue());
					            snapgateCombo.setText(m.getValue());
								if (!isInitializing()) {
									setDirty(true);
									updateLaunchConfigurationDialog();
								}
				            }
					 	}
			       }
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing
			}
		});
		
		snapgateDelButton = new Button(content, SWT.PUSH);
		snapgateDelButton.setText("Delete");
		snapgateDelButton.setLayoutData(new GridData());
		snapgateDelButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!snapgateCombo.getText().isEmpty())
				{
					snapgateIPList.remove(snapgateCombo.getText());
					int index = snapgateCombo.indexOf(snapgateCombo.getText());
					snapgateCombo.remove(index);
					if (!isInitializing()) {
						setDirty(true);
						updateLaunchConfigurationDialog();
					}
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing
			}
		});	
	}

	private void createMSPDebugSelection(Composite parent) {
		ModifyListener setDirtyModifyListener = new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				if (!isInitializing()) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		};

		SelectionListener buttonSelectionListener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isInitializing()) {
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing
			}
		};

		// driver selection
		Label label = new Label(parent, SWT.NONE);
		label.setText("Driver:");
		label.setLayoutData(new GridData());

		driverCombo = new Combo(parent, SWT.READ_ONLY);
		for (Driver d : Driver.values()) {
			driverCombo.add(d.getId());
		}
		driverCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent evt) {
				if (!isInitializing()) {
					updateDriverConnection();
					setDirty(true);
					updateLaunchConfigurationDialog();
				}
			}
		});
		driverCombo.setEnabled(false);

		// connection selection
		label = new Label(parent, SWT.NONE);
		label.setText("Connection:");
		label.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

		Composite group = new Composite(parent, SWT.NONE);
		group.setLayout(new GridLayout(3, false));

		// usb serial
		usbConnectionButton = new Button(group, SWT.RADIO);
		usbConnectionButton.setText("USB serial");
		usbConnectionButton.setLayoutData(new GridData());
		usbConnectionButton.addSelectionListener(buttonSelectionListener);

		usbDeviceCombo = new Combo(group, SWT.READ_ONLY);
		usbDeviceCombo.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false,
				false, 1, 1));
		//getUSBDevices();
		usbDeviceCombo.addModifyListener(setDirtyModifyListener);

		usbListButton = new Button(group, SWT.PUSH);
		usbListButton.setText("check");
		usbListButton.setLayoutData(new GridData());
		usbListButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getUSBDevices();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Nothing
			}
		});
		
		// tty
		ttyConnectionButton = new Button(group, SWT.RADIO);
		ttyConnectionButton.setText("TTY:");
		ttyConnectionButton.addSelectionListener(buttonSelectionListener);
		ttyConnectionButton.setEnabled(false);

		ttyDevice = new Text(group, SWT.BORDER);
		GridData gridData = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gridData.grabExcessHorizontalSpace = true;
		gridData.widthHint = 150;
		gridData.verticalSpan = 2;
		ttyDevice.setLayoutData(gridData);
		ttyDevice.addModifyListener(setDirtyModifyListener);
		ttyDevice.setEnabled(false);

		// protocal seletion
		label = new Label(parent, SWT.NONE);
		label.setText("Protocol:");
		label.setLayoutData(new GridData());

		protocolCombo = new Combo(parent, SWT.READ_ONLY);
		protocolCombo.add("SBW");
		protocolCombo.add("JTAG");
		protocolCombo.addModifyListener(setDirtyModifyListener);
		protocolCombo.setEnabled(false);
	}
}
