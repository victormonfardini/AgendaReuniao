package br.isapa.sp.agendareuniao.modelos;

public class Reuniao {
	/*
	 * PROPRIEDADES
	 */

	private String usuario;
	private String assunto;
	private String dataMesAno;
	private String horaInicio;
	private String horaFinal;
	private String sala;
	private int id;

	/*
	 * GETTERS & SETTERS
	 */

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDataMesAno() {
		return dataMesAno;
	}

	public void setDataMesAno(String dataMesAno) {
		this.dataMesAno = dataMesAno;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
