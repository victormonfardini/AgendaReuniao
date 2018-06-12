package br.isapa.sp.agendareuniao;

import br.isapa.sp.agendareuniao.gui.frames.FrameAgendamento;
import br.isapa.sp.agendareuniao.gui.frames.FrameLogin;
import br.isapa.sp.agendareuniao.resources.Recursos;

public class Principal {

	public static void main(String[] args) throws Exception {

		Recursos.carregar();
		new FrameLogin().setVisible(true);
		

	}

}