package hamleditor.editors;

import org.eclipse.jface.text.rules.*;

public class TagRule extends SingleLineRule {

	public TagRule(IToken token) {
		super("%", " ", token);
	}
	
	/*
	protected boolean sequenceDetected(
		ICharacterScanner scanner,
		char[] sequence,
		boolean eofAllowed) {
		int c = scanner.read();
		if (sequence[0] == '<') {
			if (c == '?') {   
				// processing instruction - abort
				scanner.unread();
				return false;
			}
			if (c == '!') {
				scanner.unread();
				// comment - abort
				return false;
			}
		} else if (sequence[0] == '>') {
			scanner.unread();
		}
		return super.sequenceDetected(scanner, sequence, eofAllowed);
	}
	
	*/
	
	protected boolean endSequenceDetected(
			ICharacterScanner scanner) {
		int c = scanner.read();
		
		//System.out.print(c);
		//System.out.print((char)c);
		
		if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
			//scanner.unread();
			//System.out.print("space");
			return true;
		} else if (c == '=') {
			return true;
		}
		
		scanner.unread();
		return super.endSequenceDetected(scanner);
	}
}
