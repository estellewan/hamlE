package hamleditor.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class Editor extends TextEditor {

	private ColorManager colorManager;

	public Editor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
