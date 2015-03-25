package dataModel;

public class Tape {
	//TODO: Create A String that's capable of handling negative indices and values like length or length+1
	StringBuilder content=new StringBuilder();
	
	public StringBuilder getContent() {
		return content;
	}

	public void setContent(StringBuilder content) {
		this.content = content;
	}

	public static final char EMPTY = '¶';
	
	public char getSymbolAt(int position) {
		if(position >= 0 && position < content.length()) {
			return content.charAt(position);
		} else {
			return EMPTY;
		}
	}
	
	public void setSymbolAtPosition(char symbol,int position) {
		if(position >= 0 || position < content.length()) {
			content.replace(position, position, symbol+"");
			try {
				content.charAt(position+1);
				content.deleteCharAt(position+1);
			} catch(IndexOutOfBoundsException e) {
				
			}
		} else if(position < 0){
			content.insert(0, symbol);
		} else {
			content.append(symbol);
		}
	}
	
}
