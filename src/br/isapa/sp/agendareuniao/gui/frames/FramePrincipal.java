package br.isapa.sp.agendareuniao.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.isapa.sp.agendareuniao.dao.DAOReuniao;
import br.isapa.sp.agendareuniao.gui.modtabelas.ModeloTabelaReuniao;
import br.isapa.sp.agendareuniao.modelos.Reuniao;
import br.isapa.sp.agendareuniao.resources.Recursos;
import br.isapa.sp.agendareuniao.gui.frames.FrameAgendamento;

public class FramePrincipal extends JFrame {
	/*
	 * GUI
	 */

	private JScrollPane agTabela;
	private JTable tabela;
	private JLabel lbAgendado;
	private JButton btSalaSEA, btSalaIAI, btSalaCN, btSalaLEO;
	private JButton btAgendamento, btCancelamento;
	private JLabel lbIcone;
	private JLabel lbMenu;
	private JLabel lbCriador;
	String statusAD;

	/*
	 * PROPRIEDADES
	 */
	private List<Reuniao> listaReuniao = new ArrayList<>();

	// DECLARAR OBJETO DE REFERÊNCIA DO CONTATO SELECIONADO
	private Reuniao reuniaoSelecionada = null;

	public FramePrincipal() {

		inicializarComponentes();
		inicializarEventos();

	}

	private void inicializarComponentes() {
		final Font FONTE_PADRAO = new Font("Arial", Font.BOLD, 13);
		final Font FONTE_MENU = new Font("Arial", Font.BOLD, 17);
		final Font FONTE_CRIADOR = new Font("Arial", Font.BOLD, 9);
		final int LARGURA = 1256, ALTURA = 550;

		// this
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setMinimumSize(new Dimension(LARGURA, ALTURA));
		setTitle("Agenda de Reuniões");
		setLocationRelativeTo(null);
		setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);

		// lbAgendado
		lbAgendado = new JLabel();
		lbAgendado.setForeground(Color.BLACK);
		lbAgendado.setBackground(Color.BLACK);
		lbAgendado.setFont(FONTE_MENU);
		lbAgendado.setText("REUNIÕES AGENDADAS");
		lbAgendado.setSize(200, 20);
		lbAgendado.setLocation(2, 0);
		add(lbAgendado);

		// btSalaSEA
		btSalaSEA = new JButton();
		btSalaSEA.setFont(FONTE_PADRAO);
		btSalaSEA.setText("SALA SERRA");
		btSalaSEA.setSize(200, 20);
		btSalaSEA.setLocation(0, lbAgendado.getY() + lbAgendado.getHeight());
		add(btSalaSEA);

		// btSalaIAI
		btSalaIAI = new JButton();
		btSalaIAI.setFont(FONTE_PADRAO);
		btSalaIAI.setText("SALA ITAJAÍ");
		btSalaIAI.setSize(200, 20);
		btSalaIAI.setLocation(0, btSalaSEA.getY() + btSalaSEA.getHeight());
		add(btSalaIAI);

		// btSalaCN
		btSalaCN = new JButton();
		btSalaCN.setFont(FONTE_PADRAO);
		btSalaCN.setText("SALA CONSELHEIRO");
		btSalaCN.setSize(200, 20);
		btSalaCN.setLocation(0, btSalaIAI.getY() + btSalaIAI.getHeight());
		add(btSalaCN);

		// btSalaLEO
		btSalaLEO = new JButton();
		btSalaLEO.setFont(FONTE_PADRAO);
		btSalaLEO.setText("SALA LEOPOLDINA");
		btSalaLEO.setSize(200, 20);
		btSalaLEO.setLocation(0, btSalaCN.getY() + btSalaCN.getHeight());
		add(btSalaLEO);

		// lbMenu
		lbMenu = new JLabel();
		lbMenu.setForeground(Color.BLACK);
		lbMenu.setBackground(Color.BLACK);
		lbMenu.setFont(FONTE_MENU);
		lbMenu.setText("               MENU");
		lbMenu.setSize(200, 20);
		lbMenu.setLocation(0, btSalaLEO.getY() + btSalaLEO.getHeight());
		add(lbMenu);

		// btAgendamento
		btAgendamento = new JButton();
		btAgendamento.setFont(FONTE_PADRAO);
		btAgendamento.setText("NOVO AGENDAMENTO");
		btAgendamento.setSize(200, 20);
		btAgendamento.setLocation(0, lbMenu.getY() + lbMenu.getHeight());
		add(btAgendamento);

		// btCancelamento
		btCancelamento = new JButton();
		btCancelamento.setFont(FONTE_PADRAO);
		btCancelamento.setText("CANCELAR REUNIÃO");
		btCancelamento.setSize(200, 20);
		btCancelamento.setLocation(0, btAgendamento.getY() + btAgendamento.getHeight());
		add(btCancelamento);

		// lbIcone
		lbIcone = new JLabel(Recursos.imgIsapa);
		lbIcone.setSize(200, 300);
		lbIcone.setIcon(Recursos.imgIsapa);
		lbIcone.setLocation(0, btCancelamento.getY() + btCancelamento.getHeight() + 128);
		add(lbIcone);
		
		// lbCriador
		lbCriador = new JLabel();
		lbCriador.setForeground(Color.BLACK);
		lbCriador.setBackground(Color.BLACK);
		lbCriador.setFont(FONTE_CRIADOR);
		lbCriador.setText("Criado por Victor Monfardini");
		lbCriador.setSize(140, 20);
		lbCriador.setLocation(30,lbIcone.getHeight() + 207);
		add(lbCriador);
		

		// agTabela
		agTabela = new JScrollPane();
		agTabela.setSize(1050, 520);
		agTabela.setLocation(200, 0);
		add(agTabela);

		// tabela
		tabela = new JTable();

		// Associando o modelo com a tabela
		ModeloTabelaReuniao reuniao = new ModeloTabelaReuniao(this.listaReuniao);
		tabela.setModel(reuniao);

		tabela.getTableHeader().setBackground(Color.WHITE);
		tabela.getTableHeader().setForeground(Color.BLACK);
		tabela.setFont(FONTE_PADRAO);
		agTabela.setViewportView(tabela);
		
	

	}

	private void BuscarSEA() {
		try {
			listaReuniao = DAOReuniao.buscarSerra();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar as reuniões do banco de dados",
					"Buscar Reunião", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void BuscarCN() {
		try {
			listaReuniao = DAOReuniao.buscarCN();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar as reuniões do banco de dados",
					"Buscar Reunião", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void BuscarLEO() {
		try {
			listaReuniao = DAOReuniao.buscarLeo();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar as reuniões do banco de dados",
					"Buscar Reunião", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void BuscarIAI() {
		try {
			listaReuniao = DAOReuniao.buscarItajai();
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao buscar as reuniões do banco de dados",
					"Buscar Reunião", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Popula (Preenche com informações) a tabela contato
	 */
	public void popularTabelaReuniao() {
		ModeloTabelaReuniao reuniao = new ModeloTabelaReuniao(this.listaReuniao);
		tabela.setModel(reuniao);
		tabela.getColumnModel().getColumn(0).setMaxWidth(150);
		tabela.getColumnModel().getColumn(0).setMinWidth(95);
		tabela.getColumnModel().getColumn(2).setMaxWidth(150);
		tabela.getColumnModel().getColumn(2).setMinWidth(80);
		tabela.getColumnModel().getColumn(3).setMaxWidth(50);
		tabela.getColumnModel().getColumn(3).setMinWidth(40);
		tabela.getColumnModel().getColumn(4).setMaxWidth(50);
		tabela.getColumnModel().getColumn(4).setMinWidth(40);

	}

	/**
	 * Excluir o contato selecionado na tabela
	 */
	private void excluir() {
		
		String login = System.getProperty("login");
		// Só exclui se houver contato selecionado
		if (reuniaoSelecionada != null) {
			if (reuniaoSelecionada.getUsuario().equalsIgnoreCase(login)) {

				int comando = JOptionPane.showConfirmDialog(this, "Deseja realmente cancelar a reunião?",
						"Cancelar Reunião", JOptionPane.YES_NO_OPTION);

				if (comando == JOptionPane.YES_OPTION) {
					try {
						DAOReuniao.deletar(reuniaoSelecionada);
						// Remove o contato selecionado da lista
						listaReuniao.remove(reuniaoSelecionada);
						popularTabelaReuniao(); // Refaz a tabela

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Usuário logado não é o mesmo que agendou a reunião",
						"Excluir Reunião", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Nenhuma reunião selecionada",
					"Excluir Reunião", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Fecha o programa inteiro
	 */
	private void fechar() {
		System.exit(0);
	}

	private void inicializarEventos() {

		ActionListener alBuscarSEA = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				BuscarSEA();
				popularTabelaReuniao();
			}
		};

		ActionListener alBuscarCN = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				BuscarCN();
				popularTabelaReuniao();
			}
		};

		ActionListener alBuscarLEO = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				BuscarLEO();
				popularTabelaReuniao();
			}
		};

		ActionListener alBuscarIAI = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				BuscarIAI();
				popularTabelaReuniao();
			}
		};
		btSalaIAI.addActionListener(alBuscarIAI);

		btSalaLEO.addActionListener(alBuscarLEO);

		btSalaCN.addActionListener(alBuscarCN);

		btSalaSEA.addActionListener(alBuscarSEA);

		ActionListener alExcluir = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				excluir();
			}
		};

		btCancelamento.addActionListener(alExcluir);

		/* CRIAR EVENTO DE TROCA DE SELEÇÃO DE ITENS DA TABELA */
		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Obter qual o indice da linha foi selecionado...
				int indiceSelecionado = tabela.getSelectedRow();

				// Verificando se o indice é valido
				if (indiceSelecionado >= 0 && indiceSelecionado < listaReuniao.size()) {
					// Armazenando o contato selecionado
					reuniaoSelecionada = listaReuniao.get(indiceSelecionado);
				}

			}
		});

		btAgendamento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameAgendamento frameAgendamento = null;

				frameAgendamento = new FrameAgendamento(FramePrincipal.this);

				frameAgendamento.setVisible(true);
			}
		});
	}

}
