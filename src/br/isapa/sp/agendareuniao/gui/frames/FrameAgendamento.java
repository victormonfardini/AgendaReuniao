package br.isapa.sp.agendareuniao.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import br.isapa.sp.agendareuniao.dao.ConexaoMySQL;
import br.isapa.sp.agendareuniao.dao.DAOReuniao;
import br.isapa.sp.agendareuniao.modelos.Reuniao;

public class FrameAgendamento extends JDialog {
	private JLabel lbSala;
	JComboBox cbSala = new JComboBox<>();
	private JLabel lbAssunto;
	private JTextField tfAssunto;
	private JLabel lbData;
	JDateChooser jDate;
	private JLabel lbHoraInicio;
	public JComboBox cbHoraInicio = new JComboBox<>();
	private JLabel lbHoraFinal;
	public JComboBox cbHoraFinal = new JComboBox<>();
	private JButton btAgendar;
	private JLabel lbObservacao;
	private JTextArea tfaObservacao;
	JCalendar cCalendario = new JCalendar();

	int horaI, horaF;
	int dia, mes, ano;
	int dia1, mes1, ano1;
	String horI2, Inicio,Final ;
	String dataEscolhido;
	FramePrincipal framePrincial = new FramePrincipal();
	FrameLogin frameLogin;

	/*
	 * PROPRIEDADES
	 */
	private List<Reuniao> listaReuniao = new ArrayList<>();

	// DECLARAR OBJETO DE REFER�NCIA DO CONTATO SELECIONADO
	private Reuniao reuniaoSelecionada = null;

	public FrameAgendamento(JFrame telaPai) {
		super(telaPai, true);
		inicializarComponentes();
		inicializarEventos();

	}

	private void inicializarComponentes() {

		final Dimension TAMANHO = new Dimension(320, 290);
		final Font FONTE_PADRAO = new Font("Arial", Font.PLAIN, 15);

		// this
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setMinimumSize(TAMANHO);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.WHITE);
		setTitle("                                    AGENDAMENTO");
		

		// lbAssunto
		lbAssunto = new JLabel();
		lbAssunto.setForeground(Color.BLACK);
		lbAssunto.setFont(FONTE_PADRAO);
		lbAssunto.setText("ASSUNTO: ");
		lbAssunto.setSize(80, 30);
		lbAssunto.setLocation(20, 10);
		add(lbAssunto);

		// tfAssunto
		tfAssunto = new JTextField();
		tfAssunto.setFont(FONTE_PADRAO);
		tfAssunto.setSize(200, 20);
		tfAssunto.setLocation(lbAssunto.getWidth() + lbAssunto.getX(), lbAssunto.getY() + 4);
		add(tfAssunto);

		// lbData
		lbData = new JLabel();
		lbData.setForeground(Color.BLACK);
		lbData.setFont(FONTE_PADRAO);
		lbData.setText("DATA: ");
		lbData.setSize(60, 30);
		lbData.setLocation(lbAssunto.getX(), lbAssunto.getHeight() + 9);
		add(lbData);

		jDate = new JDateChooser();
		jDate.setBounds(lbData.getX() + 50, lbData.getY() + 4, lbData.getWidth() + 65, tfAssunto.getHeight());
		add(jDate);

		// lbSala
		lbSala = new JLabel();
		lbSala.setForeground(Color.BLACK);
		lbSala.setFont(FONTE_PADRAO);
		lbSala.setText("SALA: ");
		lbSala.setSize(50, 30);
		lbSala.setLocation(lbData.getX(), lbData.getY() + lbData.getHeight());
		add(lbSala);

		// cbSala
		cbSala = new JComboBox<>();
		cbSala.setSize(125, 20);
		cbSala.setLocation(lbSala.getX() + lbSala.getWidth(), lbSala.getY() + 4);
		cbSala.addItem("");
		cbSala.addItem("Sala Serra");
		cbSala.addItem("Sala Conselheiro");
		cbSala.addItem("Sala Itajai");
		cbSala.addItem("Sala Leopoldina");
		cbSala.addItem("Sala CTI");
		cbSala.addItem("Sala Guarulhos");
		add(cbSala);

		// lbHoraInicio
		lbHoraInicio = new JLabel();
		lbHoraInicio.setForeground(Color.BLACK);
		lbHoraInicio.setFont(FONTE_PADRAO);
		lbHoraInicio.setText("IN�CIO: ");
		lbHoraInicio.setSize(50, 30);
		lbHoraInicio.setLocation(lbSala.getX(), lbSala.getY() + lbSala.getHeight() + 4);
		add(lbHoraInicio);

		// cbHoraInicio
		cbHoraInicio = new JComboBox<>();
		cbHoraInicio.setSize(60, 20);
		cbHoraInicio.setLocation(lbHoraInicio.getX() + lbHoraInicio.getWidth(), lbHoraInicio.getY() + 4);
		cbHoraInicio.addItem("");
		add(cbHoraInicio);

		// lbHoraFinal
		lbHoraFinal = new JLabel();
		lbHoraFinal.setForeground(Color.BLACK);
		lbHoraFinal.setFont(FONTE_PADRAO);
		lbHoraFinal.setText("T�RMINO: ");
		lbHoraFinal.setSize(78, 30);
		lbHoraFinal.setLocation(cbHoraInicio.getX() + cbHoraInicio.getWidth() + 10, lbHoraInicio.getY());
		add(lbHoraFinal);

		// cbHoraFinal
		cbHoraFinal = new JComboBox<>();
		cbHoraFinal.setSize(60, 20);
		cbHoraFinal.setLocation(lbHoraFinal.getX() + lbHoraFinal.getWidth(), lbHoraInicio.getY() + 4);
		cbHoraFinal.addItem("");
		add(cbHoraFinal);
		
		// lbobservacao
		lbObservacao = new JLabel();
		lbObservacao.setForeground(Color.BLACK);
		lbObservacao.setFont(FONTE_PADRAO);
		lbObservacao.setText("OBS: ");
		lbObservacao.setSize(78, 60);
		lbObservacao.setLocation(lbHoraInicio.getX(), lbHoraFinal.getY() + lbHoraFinal.getHeight());
		add(lbObservacao);
		
		// tfaObservacao
		tfaObservacao = new JTextArea(3, 3);
		tfaObservacao.setFont(FONTE_PADRAO);
		tfaObservacao.setSize(235, 60);
		tfaObservacao.setBorder(new LineBorder(Color.BLACK));
		tfaObservacao.setLineWrap(true);
		tfaObservacao.setLocation(lbObservacao.getWidth()- 9, lbObservacao.getY() + 4);
		add(tfaObservacao);
		

		// btAgendar
		btAgendar = new JButton();
		btAgendar.setFont(FONTE_PADRAO);
		btAgendar.setText("AGENDAR");
		btAgendar.setSize(295, 20);
		btAgendar.setLocation(10, lbObservacao.getY() + lbObservacao.getHeight() + 18);
		add(btAgendar);

	}

	public void popularCampos() {
		// Colocando as informa��es do contato selecionado de volta
		// nos campos
		tfAssunto.setText(reuniaoSelecionada.getAssunto());
		jDate.setDateFormatString(reuniaoSelecionada.getDataMesAno());
		cbHoraInicio.setSelectedItem(reuniaoSelecionada.getHoraInicio());
		cbHoraFinal.setSelectedItem(reuniaoSelecionada.getHoraFinal());
		cbSala.setSelectedItem(reuniaoSelecionada.getSala());
		tfaObservacao.setText(reuniaoSelecionada.getObservacao());
	}

	/**
	 * Salva a tarefa. Se a tarefa n�o estiver selecionado cadastra uma nova.
	 * 
	 * @throws ParseException
	 */
	private void Salvar() {

		if (validarCampos()) {
			// FAZER ALGORITMO QUE VERIFICA SE EXISTE UM CONTATO SELECIONADO
			Reuniao reuniao = null;
			reuniao = new Reuniao();

			// Obtendo os dados dos campos
			reuniao.setUsuario(System.getProperty("login"));
			reuniao.setAssunto(tfAssunto.getText());
			reuniao.setSala((String) cbSala.getSelectedItem());

			Date date;
			date = jDate.getDate();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			reuniao.setDataMesAno(formato.format(date));

			// String data recebe o conte�do da variavel dataMesAno
			String data = reuniao.getDataMesAno();

			// Criando um array de 3 posi��es
			String array[] = new String[3];
			// Separando a String data a cada barra(/)
			array = data.split("/");
			// Variavel data recebe os array separados pelo hifen (-)
			data = array[2] + "-" + array[1] + "-" + array[0];
			dataEscolhido = array[2] + "/" + array[1] + "/" + array[0];

			int array1[] = new int[3];
			array1[0] = Integer.parseInt(array[0]);
			array1[1] = Integer.parseInt(array[1]);
			array1[2] = Integer.parseInt(array[2]);
			dia = array1[0];
			mes = array1[1];
			ano = array1[2];

			reuniao.setHoraInicio((String) cbHoraInicio.getSelectedItem());
			// Jogando o conteudo da String horaInicio sem os dois pontos
			// (:)
			// dentro da variavel horaI
			String horI = reuniao.getHoraInicio();
			horI2 = formato.format(date) + " " + horI;
			String horasI[] = new String[2];
			horasI = horI.split(":");
			horaI = Integer.parseInt(horasI[0] + horasI[1]);
			Inicio = horasI[0] + ":" + horasI[1];

			reuniao.setHoraFinal((String) cbHoraFinal.getSelectedItem());
			// Jogando o conteudo da String horaFinal sem os dois pontos (:)
			// dentro da variavel horaF
			String horF = reuniao.getHoraFinal();
			String horasF[] = new String[2];
			horasF = horF.split(":");
			horaF = Integer.parseInt(horasF[0] + horasF[1]);
			Final = horasF[0] + ":" + horasF[1];

			if (validarHoraAtual() && validarEscolhaHora()) {

				// a variavel horaInicio recebe o conteudo da variavel horI
				reuniao.setHoraInicio(horI);
				// a variavel horaFinal recebe o conteudo da variavel horF
				reuniao.setHoraFinal(horF);
				// a variavel dataMesAno recebe o conteudo da variavel data
				reuniao.setDataMesAno(data);
				
				//Setando a observa��o que foi colocada no JTextArea
				reuniao.setObservacao(tfaObservacao.getText());

				// FAZER ALGORITMO QUE VERIFICA SE EXISTE UM CONTATO
				// SELECIONADO
				try {
					if (verificarAgendaDisponivel()) {

						// Adicionando o contato na lista de contatos
						try {
							// Inseri o contato no banco de dados
							DAOReuniao.inserir(reuniao);
							listaReuniao.add(reuniao);
							JOptionPane.showMessageDialog(this, "Reuni�o AGENDADA", "Salvar Reuni�o",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(this,
									"Ocorreu um erro ao inserir a reuni�o no banco de dados", "Salvar Reuni�o",
									JOptionPane.ERROR_MESSAGE);
						}
						// Refaz a tabela com as novas informa��es
						framePrincial.popularTabelaReuniao();
						limparCampos();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao inserir a reuni�o no banco de dados",
							"Salvar Reuni�o", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(this, "Ocorreu um erro ao inserir a reuni�o no banco de dados",
							"Salvar Reuni�o", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public void limparCampos() {

		// Remove a refer�ncia para a tarefa selecionada
		reuniaoSelecionada = null;

		// Limpado os campos
		tfAssunto.setText("");
		jDate.setDateFormatString("");
		cbHoraInicio.setSelectedItem("");
		cbHoraFinal.setSelectedItem("");
		cbSala.setSelectedItem("");
		tfaObservacao.setText("");
	}

	/**
	 * Valida o campo de data inseridos pelo usu�rio
	 * 
	 * @return <b>true</b> quando o campo estiver ok, <b>false</b> caso contr�rio
	 */


	private boolean validarEscolhaHora() {

		if (cbHoraInicio.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(this, "Escolha um op��o do inicio do horario ", "Salvar Reuni�o",
					JOptionPane.WARNING_MESSAGE);

			cbHoraInicio.requestFocus();
			return false;
		}

		if (cbHoraFinal.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(this, "Escolha um op��o do termino do horario ", "Salvar Reuni�o",
					JOptionPane.WARNING_MESSAGE);

			cbHoraFinal.requestFocus();
			return false;
		}
		if (horaF <= horaI) {
			JOptionPane.showMessageDialog(this, "Hora de T�rmino est� menor ou igual a Hora de Inicio ",
					"Salvar Reuni�o", JOptionPane.WARNING_MESSAGE);

			cbHoraInicio.requestFocus();
			cbHoraFinal.requestFocus();
			return false;
		}

		return true;
	}

	private boolean validarHoraAtual() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date hora = new Date();
		String horaformat = dateFormat.format(hora);
		Date horaAtual = null;
		Date horaEscolhida = null;
		try {
			horaAtual = dateFormat.parse(horaformat);
			horaEscolhida = dateFormat.parse(horI2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (horaEscolhida.getTime() < horaAtual.getTime()) {
			JOptionPane.showMessageDialog(this, "A hora e/ou a data de inicio escolhida j� foi ultrapassada",
					"Salvar Reuni�o", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Valida todos os campos inseridos pelo usu�rio
	 * 
	 * @return <b>true</b> quando os campos est�o ok, <b>false</b> caso contr�rio
	 */
	private boolean validarCampos() {

		if (tfAssunto.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "Insira uma quantidade de caracteres entre " + "3 e 64 no campo nome",
					"Salvar Reuni�o", JOptionPane.WARNING_MESSAGE);

			tfAssunto.requestFocus();
			return false;
		}

		if (cbSala.getSelectedItem() == "") {
			JOptionPane.showMessageDialog(this, "Escolha um op��o de sala ", "Salvar Reuni�o",
					JOptionPane.WARNING_MESSAGE);

			cbSala.requestFocus();
			return false;
		}

		return true;
	}

	private boolean verificarAgendaDisponivel() throws ClassNotFoundException, SQLException {
		String SQL = "SELECT TIME_FORMAT(horarioInicio, '%H:%i'), TIME_FORMAT(horarioFinal, '%H:%i'), "
				+ "agenda_reuniao.usuario, agenda_reuniao.assunto FROM horario LEFT JOIN agenda_reuniao "
				+ "ON  agenda_reuniao.sala = ? WHERE horarioInicio >= ? AND horarioFinal <= ? "
				+ "AND agenda_reuniao.dataMesAno = ?"
				+ "AND horarioInicio BETWEEN agenda_reuniao.horaInicio AND agenda_reuniao.horaFinal "
				+ "AND horarioFinal BETWEEN agenda_reuniao.horaInicio AND agenda_reuniao.horaFinal";
		try {
			Reuniao reuniao = null;
			reuniao = new Reuniao();

			reuniao.setSala((String) cbSala.getSelectedItem());

			Date date;
			date = jDate.getDate();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			reuniao.setDataMesAno(formato.format(date));
			// String data recebe o conte�do da variavel dataMesAno
			String data = reuniao.getDataMesAno();
			// Criando um array de 3 posi��es
			String array[] = new String[3];
			// Separando a String data a cada barra(/)
			array = data.split("/");
			// Variavel data recebe os array separados pelo hifen (-)
			// data = array[2] + "-" + array[1] + "-" + array[0];
			data = (new StringBuilder(String.valueOf(array[2]))).append("-").append(array[1]).append("-")
					.append(array[0]).toString();
			
			//recebe a data j� convertida para o padr�o SQL
			reuniao.setDataMesAno(data);
			
			//Est� recebendo a hora de inicio que o usu�rio escolheu
			reuniao.setHoraInicio(Inicio);
			
			//Est� recebendo a hor� final que o usu�rio escolheu
			reuniao.setHoraFinal(Final);

			ConexaoMySQL.abrir();

			PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);

			stmt.setString(1, reuniao.getSala());
			stmt.setString(2, reuniao.getHoraInicio());
			stmt.setString(3, reuniao.getHoraFinal());
			stmt.setString(4, reuniao.getDataMesAno());

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next() != false) {
				for (int i = 0; i < 29; i++) {
					JOptionPane.showMessageDialog(this,
							"O intervalo de hor�rio que foi escolhido j� possui uma reuni�o agendada", "Salvar Reuni�o",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}

			return true;
		} catch (

		ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
			throw e1;
		} finally {
			// 3� - Fechar a conexao
			// Este comando fica no finally porque a conexao deve ser encerrada
			// tanto se der erro quanto n�o der
			ConexaoMySQL.fechar();

		}
	}

	private void popularComboBox() throws Exception {

		String SQL = "SELECT TIME_FORMAT(horarioInicio, '%H:%i'),TIME_FORMAT(horarioFinal, '%H:%i') "
				+ "FROM horario WHERE NOT EXISTS(SELECT horaInicio, horaFinal FROM agenda_reuniao "
				+ "WHERE agenda_reuniao.sala = ? AND agenda_reuniao.dataMesAno = ? "
				+ "AND horario.horarioInicio BETWEEN horaInicio AND horaFinal "
				+ "AND horario.horarioFinal BETWEEN horaInicio AND horaFinal )";

		try {

			Reuniao reuniao = null;
			reuniao = new Reuniao();

			reuniao.setSala((String) cbSala.getSelectedItem());

			Date date;
			date = jDate.getDate();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			reuniao.setDataMesAno(formato.format(date));
			// String data recebe o conte�do da variavel dataMesAno
			String data = reuniao.getDataMesAno();
			// Criando um array de 3 posi��es
			String array[] = new String[3];
			// Separando a String data a cada barra(/)
			array = data.split("/");
			// Variavel data recebe os array separados pelo hifen (-)
			// data = array[2] + "-" + array[1] + "-" + array[0];
			data = (new StringBuilder(String.valueOf(array[2]))).append("-").append(array[1]).append("-")
					.append(array[0]).toString();

			reuniao.setDataMesAno(data);

			ConexaoMySQL.abrir();

			PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);

			stmt.setString(1, reuniao.getSala());
			stmt.setString(2, reuniao.getDataMesAno());

			ResultSet resultSet = stmt.executeQuery();

			cbHoraInicio.removeAllItems();
			cbHoraFinal.removeAllItems();

			while (resultSet.next()) {
				cbHoraInicio.addItem(resultSet.getString("TIME_FORMAT(horarioInicio, '%H:%i')"));
				cbHoraFinal.addItem(resultSet.getString("TIME_FORMAT(horarioFinal, '%H:%i')"));
			}

		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
			throw e1;
		} finally {
			// 3� - Fechar a conexao
			// Este comando fica no finally porque a conexao deve ser encerrada
			// tanto se der erro quanto n�o der
			ConexaoMySQL.fechar();

		}
	}

	private void inicializarEventos() {
		ActionListener alSalvar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				Salvar();
			}
		};

		ActionListener alHora = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					popularComboBox();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};

		cbSala.addActionListener(alHora);

		btAgendar.addActionListener(alSalvar);
	}

}
