package jwd.test.web.dto;

public class ZgradaDTO {

	private Long id;
	private String adresa;
	private String predsednikKS;
	private int brojStanova;
	private int brojStanara;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPredsednikKS() {
		return predsednikKS;
	}

	public void setPredsednikKS(String predsednikKS) {
		this.predsednikKS = predsednikKS;
	}

	public int getBrojStanova() {
		return brojStanova;
	}

	public void setBrojStanova(int brojStanova) {
		this.brojStanova = brojStanova;
	}

	public int getBrojStanara() {
		return brojStanara;
	}

	public void setBrojStanara(int brojStanara) {
		this.brojStanara = brojStanara;
	}

}