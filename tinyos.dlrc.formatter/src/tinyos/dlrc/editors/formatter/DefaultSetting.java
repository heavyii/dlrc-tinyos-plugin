package tinyos.dlrc.editors.formatter;

public class DefaultSetting implements IFormattingSettings{
	@Override
	public int getLineWrappingLength(){
		return 80;
	}
}
