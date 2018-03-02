package br.isapa.sp.agendareuniao.resources;

import javax.swing.ImageIcon;

public class Recursos {
	private Recursos() {
	}

	public static ImageIcon imgIsapa = null;
	public static ImageIcon imgUser = null;
	public static ImageIcon imgSenha = null;

	public static void carregar() {

		imgIsapa = new ImageIcon(Recursos.class.getResource("imge/imgIsapa.jpg"));
		imgUser = new ImageIcon(Recursos.class.getResource("imge/imgUser.jpg"));
		imgSenha = new ImageIcon(Recursos.class.getResource("imge/imgChave.jpg"));

	}

}
