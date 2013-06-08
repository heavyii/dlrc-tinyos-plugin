package tinyos.dlrc.refactoring.entities.variable.rename.local;

import org.eclipse.core.commands.IHandler;

import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameActionHandler;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameInfo;
import tinyos.dlrc.refactoring.abstractrefactoring.rename.RenameProcessor;

public class RenameLocalVariableActionHandler extends RenameActionHandler implements IHandler {

	@Override
	protected RenameProcessor createProcessor(RenameInfo info) {
		return new RenameLocalVariableProcessor(info);
	}

}
