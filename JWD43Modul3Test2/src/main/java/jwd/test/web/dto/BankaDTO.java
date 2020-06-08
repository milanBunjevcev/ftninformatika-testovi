package jwd.test.web.dto;

public class BankaDTO {

	private Long id;
	private String naziv;
	private double sredstva;

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

	public double getSredstva() {
		return sredstva;
	}

	public void setSredstva(double sredstva) {
		this.sredstva = sredstva;
	}
}