package net.sourceforge.vrapper.vim.commands;

import net.sourceforge.vrapper.platform.CursorService;
import net.sourceforge.vrapper.utils.ContentType;
import net.sourceforge.vrapper.utils.TextRange;
import net.sourceforge.vrapper.vim.EditorAdaptor;

public class TextOperationTextObjectCommand extends TextObjectCommand {

	private final TextOperation command;

	public TextOperationTextObjectCommand(TextOperation command, TextObject textObject) {
		super(textObject);
		this.command = command;
	}

	@Override
	protected void execute(EditorAdaptor editorAdaptor, TextRange range, ContentType contentType) throws CommandExecutionException {
		command.execute(editorAdaptor, range, contentType);
        CursorService cursorService = editorAdaptor.getCursorService();
        cursorService.setPosition(cursorService.getPosition(), true);
	}

	@Override
	public CountAwareCommand repetition() {
		TextOperation wrappedRepetition = command.repetition();
		if (wrappedRepetition != null)
			return new TextOperationTextObjectCommand(wrappedRepetition, textObject);
		return null;
	}

}