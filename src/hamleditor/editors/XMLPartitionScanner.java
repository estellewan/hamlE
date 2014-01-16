package hamleditor.editors;

import org.eclipse.jface.text.rules.*;

public class XMLPartitionScanner extends RuleBasedPartitionScanner {
	public final static String XML_COMMENT = "__xml_comment";
	public final static String XML_TAG = "__xml_tag";

	public XMLPartitionScanner() {

		IToken xmlComment = new Token(XML_COMMENT);
		IToken tag = new Token(XML_TAG);

		IPredicateRule[] rules = new IPredicateRule[3];

		rules[0] = new MultiLineRule("<!--", "-->", xmlComment);
		rules[1] = new MultiLineRule("#", "\n", xmlComment);
		rules[2] = new TagRule(tag);
		
		setPredicateRules(rules);
	}
}
