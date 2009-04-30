package kg.totality.core.commands.motions;

import kg.totality.core.commands.BorderPolicy;
import newpackage.glue.TextContent;

public class LineEndMotion extends AbstractModelSideMotion {

	private final BorderPolicy borderPolicy;

	public LineEndMotion(BorderPolicy borderPolicy) {
		this.borderPolicy = borderPolicy;
	}

	@Override
	public BorderPolicy borderPolicy() {
		return borderPolicy;
	}

	@Override
	protected int destination(int modelOffset, TextContent modelContent, int count) {
		return getDestination(modelOffset, modelContent, count);
	}

	public static int getDestination(int modelOffset, TextContent content, int count) {
		int currentLine = content.getLineInformationOfOffset(modelOffset).getNumber();
		int lineCount = content.getLineInformationOfOffset(content.getTextLength()).getNumber();
		int lineNo = Math.min(lineCount, currentLine + count - 1);
		return content.getLineInformation(lineNo).getEndOffset();
	}


}