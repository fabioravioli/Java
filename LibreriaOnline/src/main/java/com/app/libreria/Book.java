package com.app.libreria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libri")

public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	@Column(name = "titolo", length = 100, nullable = false)
	private String titolo;
	@Column(name = "autore", length = 100, nullable = false)
	private String autore;
	@Column(name = "anno_pubblicazione", nullable = false)
	private int anno_pubblicazione;
	@Column(name = "quantita", nullable = true)
	private int quantità;
	
	
	public Book () {
		this("", "", 0 , 0);
		
	}
	
	public Book (String titolo, String autore, int anno_pubblicazione, int quantità) {
		this.titolo = titolo;
		this.autore = autore;
		this.anno_pubblicazione = anno_pubblicazione;
		this.quantità = quantità;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public int getAnno_pubblicazione() {
		return anno_pubblicazione;
	}

	public void setAnno_pubblicazione(int anno_pubblicazione) {
		this.anno_pubblicazione = anno_pubblicazione;
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	
	
	
}
