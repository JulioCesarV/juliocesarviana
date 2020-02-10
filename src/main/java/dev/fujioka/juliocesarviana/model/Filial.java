package dev.fujioka.juliocesarviana.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_filial")
public class Filial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int numero; //Trata-se da numeração que a filial ganha ao ser criada, para que possamos diferenciar umas das outras. Começando com 1.
	
	private String endereco;
	
	private float faturamentoMensal;
	
	private float despesaMensal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public float getFaturamentoMensal() {
		return faturamentoMensal;
	}

	public void setFaturamentoMensal(float faturamentoMensal) {
		this.faturamentoMensal = faturamentoMensal;
	}

	public float getDespesaMensal() {
		return despesaMensal;
	}

	public void setDespesaMensal(float despesaMensal) {
		this.despesaMensal = despesaMensal;
	}
	
	
	
	
}
