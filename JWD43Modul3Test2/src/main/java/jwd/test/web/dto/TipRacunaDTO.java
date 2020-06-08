package jwd.test.web.dto;

public class TipRacunaDTO {

	private Long id;
	private String naziv;
	private double provizija;

	private Long bankaId;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getProvizija() {
		return provizija;
	}

	public void setProvizija(double provizija) {
		this.provizija = provizija;
	}

	public Long getBankaId() {
		return bankaId;
	}

	public void setBankaId(Long bankaId) {
		this.bankaId = bankaId;
	}
}