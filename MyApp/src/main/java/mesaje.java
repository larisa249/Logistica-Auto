public class mesaje {
	int id;
	int expeditor_id;
	int destinatar_id;
	String continut;
	
	public mesaje (int id, int expeditor_id, int destinatar_id, String continut) {
		this.id=id;
		this.expeditor_id=expeditor_id;
		this.destinatar_id=destinatar_id;
		this.continut=continut;
	}
	
	public int getId() {
		return id;
	}
	
	public int getExpeditor() {
		return expeditor_id;
	}
	
	public int getDestinatar() {
		return destinatar_id;
	}
	
	public String getContinut() {
		return continut;
	}
}
