package hamleditor.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class XMLConfiguration extends SourceViewerConfiguration {
	private XMLDoubleClickStrategy doubleClickStrategy;
	private XMLTagScanner tagScanner;
	private HAMLIDScanner idScanner;
	private XMLScanner scanner;
	private XMLVarScanner varScanner;
	private ColorManager colorManager;

	public XMLConfiguration(ColorManager colorManager) {
		this.colorManager = colorManager;
	}
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			XMLPartitionScanner.XML_COMMENT,
			XMLPartitionScanner.XML_TAG,
			XMLPartitionScanner.HAML_VAR,
			XMLPartitionScanner.HAML_ID,
			XMLPartitionScanner.HAML_ATTR };
	}
	public ITextDoubleClickStrategy getDoubleClickStrategy(
		ISourceViewer sourceViewer,
		String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new XMLDoubleClickStrategy();
		return doubleClickStrategy;
	}

	protected XMLScanner getXMLScanner() {
		if (scanner == null) {
			scanner = new XMLScanner(colorManager);
			scanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.DEFAULT))));
		}
		return scanner;
	}
	protected XMLTagScanner getXMLTagScanner() {
		if (tagScanner == null) {
			tagScanner = new XMLTagScanner(colorManager);
			tagScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.TAG))));
		}
		return tagScanner;
	}
	
	protected HAMLIDScanner getHAMLIDScanner() {
		if (idScanner == null) {
			idScanner = new HAMLIDScanner(colorManager);
			idScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.ID))));
		}
		return idScanner;
	}
	
	protected XMLVarScanner getXMLVarScanner() {
		if (varScanner == null) {
			varScanner = new XMLVarScanner(colorManager);
			varScanner.setDefaultReturnToken(
				new Token(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.VAR))));
		}
		return varScanner;
	}

	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();

		DefaultDamagerRepairer dr =
				new DefaultDamagerRepairer(getXMLScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

		NonRuleBasedDamagerRepairer ndr =
			new NonRuleBasedDamagerRepairer(
				new TextAttribute(
					colorManager.getColor(IXMLColorConstants.XML_COMMENT)));
		reconciler.setDamager(ndr, XMLPartitionScanner.XML_COMMENT);
		reconciler.setRepairer(ndr, XMLPartitionScanner.XML_COMMENT);

		ndr =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.VAR)));
			reconciler.setDamager(ndr, XMLPartitionScanner.HAML_VAR);
			reconciler.setRepairer(ndr, XMLPartitionScanner.HAML_VAR);
			
		ndr =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.TAG)));
			reconciler.setDamager(ndr, XMLPartitionScanner.XML_TAG);
			reconciler.setRepairer(ndr, XMLPartitionScanner.XML_TAG);
			
		ndr =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.ID)));
			reconciler.setDamager(ndr, XMLPartitionScanner.HAML_ID);
			reconciler.setRepairer(ndr, XMLPartitionScanner.HAML_ID);
			
		ndr =
				new NonRuleBasedDamagerRepairer(
					new TextAttribute(
						colorManager.getColor(IXMLColorConstants.ATTR)));
			reconciler.setDamager(ndr, XMLPartitionScanner.HAML_ATTR);
			reconciler.setRepairer(ndr, XMLPartitionScanner.HAML_ATTR);
		return reconciler;
	}

}