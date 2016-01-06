package br.com.fatec.socialnet.api.entity;

//CLASSE PARA IDS IDENTIFICADORES DAS ENTIDADES

public class SocialNetIdentifier {
	
	private Long id;
	public static final String COL_ID = "id";
	

	public SocialNetIdentifier() {
		
	}

	public SocialNetIdentifier(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

}
