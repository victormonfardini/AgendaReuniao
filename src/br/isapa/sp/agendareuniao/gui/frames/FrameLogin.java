package br.isapa.sp.agendareuniao.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.isapa.sp.agendareuniao.modelos.Reuniao;
import br.isapa.sp.agendareuniao.resources.Recursos;

public class FrameLogin extends JFrame{

	private JLabel lbTitulo;
	private JLabel lbTitulo2;
	private JLabel lbIcone;
	private JLabel lbIconeUser;
	private JLabel lbIconeSenha;
	private JTextField tfUser;
	private JPasswordField pfSenha;
	private JButton btLogin;
	private JButton btCancelar;
	private String senha;
	String login;
	int status = 0;

	public FrameLogin() {
		inicializarComponentes();
		inicializarEventos();
		getRootPane().setDefaultButton(btLogin);
	}

	private void inicializarComponentes() {

		final Font FONTE_PADRAO = new Font("Arial", Font.BOLD, 15);
		final Font FONTE_MENU = new Font("Calibri", Font.ITALIC, 53);
		final int LARGURA = 325, ALTURA = 400;

		// this
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setMinimumSize(new Dimension(LARGURA, ALTURA));
		setTitle("Login");
		setLocationRelativeTo(null);
		setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);
		getRootPane().setDefaultButton(btLogin);

		// lbTitulo
		lbTitulo = new JLabel();
		lbTitulo.setForeground(Color.BLACK);
		lbTitulo.setBackground(Color.BLACK);
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setFont(FONTE_MENU);
		lbTitulo.setText("AGENDA");
		lbTitulo.setSize(325, 60);
		lbTitulo.setLocation(0, 0);
		add(lbTitulo);

		// lbTitulo2
		lbTitulo2 = new JLabel();
		lbTitulo2.setForeground(Color.BLACK);
		lbTitulo2.setBackground(Color.BLACK);
		lbTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo2.setFont(FONTE_MENU);
		lbTitulo2.setText("REUNIÕES");
		lbTitulo2.setSize(325, 60);
		lbTitulo2.setLocation(0, lbTitulo.getHeight() - 7);
		add(lbTitulo2);

		// lbIconeUser
		lbIconeUser = new JLabel(Recursos.imgUser);
		lbIconeUser.setSize(37, 38);
		lbIconeUser.setIcon(Recursos.imgUser);
		lbIconeUser.setLocation(25, 120);
		add(lbIconeUser);

		// tfUser
		tfUser = new JTextField();
		tfUser.setFont(FONTE_PADRAO);
		tfUser.setSize(200, 30);
		tfUser.setLocation(lbIconeUser.getWidth() + lbIconeUser.getX() + 4, lbIconeUser.getY() + 4);
		add(tfUser);

		// lbIconeSenha
		lbIconeSenha = new JLabel(Recursos.imgSenha);
		lbIconeSenha.setSize(37, 38);
		lbIconeSenha.setIcon(Recursos.imgSenha);
		lbIconeSenha.setLocation(lbIconeUser.getWidth() - 13, lbIconeUser.getHeight() + lbIconeUser.getY() + 10);
		add(lbIconeSenha);

		// pfSenha
		pfSenha = new JPasswordField();
		pfSenha.setFont(FONTE_PADRAO);
		pfSenha.setSize(200, 30);
		pfSenha.setLocation(lbIconeSenha.getWidth() + lbIconeSenha.getX() + 5, lbIconeSenha.getY() + 4);
		add(pfSenha);

		// btLogin
		btLogin = new JButton();
		btLogin.setFont(FONTE_PADRAO);
		btLogin.setText("LOGIN");
		btLogin.setSize(90, 20);
		btLogin.setLocation(pfSenha.getX(), pfSenha.getY() + pfSenha.getHeight() + 10);
		add(btLogin);

		// btCancelar
		btCancelar = new JButton();
		btCancelar.setFont(FONTE_PADRAO);
		btCancelar.setText("SAIR");
		btCancelar.setSize(90, 20);
		btCancelar.setLocation(btLogin.getX() + btLogin.getWidth() + 20, btLogin.getY());
		add(btCancelar);

		// lbIcone
		lbIcone = new JLabel(Recursos.imgIsapa);
		lbIcone.setSize(200, 200);
		lbIcone.setIcon(Recursos.imgIsapa);
		lbIcone.setLocation(65, 200);
		add(lbIcone);

	}

	public void AutenticaAD() {

		Reuniao reuniao = null;
		reuniao = new Reuniao();

		Scanner scanner = new Scanner(System.in);

		//IP do servidor do AD 
		String url = "ldap://172.16.0.35:389";
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		login = tfUser.getText();
		
		System.setProperty("login", tfUser.getText());

		env.put(Context.SECURITY_PRINCIPAL, login + "@corp.isapa.net");

		senha = new String(pfSenha.getPassword());

		env.put(Context.SECURITY_CREDENTIALS, senha);

		try {

			if (senha.length() > 0) {
				DirContext ctx = new InitialDirContext(env);
				System.out.println(ctx.getEnvironment());

				// do something useful with the context...
				
				ctx.close();
				status = 1;
				
				if (status == 1) {

					FramePrincipal framePrincipal;
					framePrincipal = new FramePrincipal();

					framePrincipal.setVisible(true);

					dispose();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Senha e/ou nome de usuário incorretos", "Erro usuário e/ou Senha",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (AuthenticationNotSupportedException ex) {
			JOptionPane.showMessageDialog(this, "A autenticação não é suportada pelo servidor", "Erro na autenticação",
					JOptionPane.ERROR_MESSAGE);

		} catch (AuthenticationException ex) {
			JOptionPane.showMessageDialog(this, "Senha e/ou nome de usuário incorretos", "Erro usuário e/ou Senha",
					JOptionPane.ERROR_MESSAGE);

		} catch (NamingException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar criar o contexto", "Erro", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void inicializarEventos() {
		ActionListener alSair = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		};

		ActionListener alLogin = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				AutenticaAD();
				

			}
			
		};

		btCancelar.addActionListener(alSair);
		btLogin.addActionListener(alLogin);
	}
}
