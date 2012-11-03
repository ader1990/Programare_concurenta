package Document;

public class Document {
	private DocumentType type = DocumentType.FirstType;
	private boolean available = false;

	public Document() {
		this._setType();
	}

	private void _setType() {
		int seed = (int)(Math.random() * 1000)%DocumentType.values().length;
		this.type=DocumentType.values()[seed];
	}

	public Document(boolean available) {
		this.available = available;
	}

	public Document(DocumentType type) {
		this.type = type;
	}

	public Document(DocumentType type, boolean available) {
		this.type = type;
		this.available = available;
	}

	public void SetAvailable() {
		this.available = true;
	}

	public boolean CheckIfAvailable() {
		return this.available;
	}

	public boolean CheckIfType(DocumentType type) {
		return this.type == type;
	}
	
	public String toString(){
		return "Document with type:"+this.type.toString()+" and availability "+this.available;
	}

}
