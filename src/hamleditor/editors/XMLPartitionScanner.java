package hamleditor.editors;

import org.eclipse.jface.text.rules.*;

public class XMLPartitionScanner extends RuleBasedPartitionScanner {
	public final static String XML_COMMENT = "__xml_comment";
	public final static String XML_TAG = "__xml_tag";
	public final static String HAML_VAR = "__haml_var";
	public final static String HAML_ID = "__haml_id";
	public final static String HAML_ATTR = "__haml_attr";

	public XMLPartitionScanner() {

		IToken xmlComment = new Token(XML_COMMENT);
		IToken tag = new Token(XML_TAG);
		IToken variable = new Token(HAML_VAR);
		IToken id = new Token (HAML_ID);
		IToken attr = new Token (HAML_ATTR);

		IPredicateRule[] rules = new IPredicateRule[6];

		rules[0] = new MultiLineRule("\"", "\"", xmlComment);
		rules[1] = new SingleLineRule("//", "", xmlComment);
		rules[2] = new WordPatternRule(new TagWordDetector(), "%", "", tag);
		//rules[3] = new SingleLineRule("@"," ",variable);
		rules[3] = new WordPatternRule(new TagWordDetector(), ".", "", variable);
		rules[4] = new WordPatternRule(new TagWordDetector(), "#", "", id);
		rules[5] = new WordPatternRule(new TagWordDetector(), ":", "", attr);
		
		setPredicateRules(rules);
	}
}
