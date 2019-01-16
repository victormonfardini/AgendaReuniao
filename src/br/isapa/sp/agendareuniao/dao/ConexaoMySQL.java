package br.isapa.sp.agendareuniao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL 
{
	
	/**
	 * Representa a conexao que será aberto
	 */
	private static Connection conexao;
	
	public static Connection getConexao()
	{
		return conexao;
	}

	/**
	 * Abre a conexão com o banco de dados MySQL
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	
	public static void abrir() throws ClassNotFoundException, SQLException
	{
		//CArrega o Driver (Classe que gerencia conexao de banco de daods) do MySQL
		//na memória
		Class.forName("com.mysql.jdbc.Driver");
	
		
		//Informações para conexão
		final String 	SERVIDOR = "ENERGIA",//Antigo "1.0.0.1"
						BANCO_DE_DADOS = "agenda_reuniao",
						USUARIO = "conexao",//conexao - Usuário criado para poder conseguir conectar
						SENHA = "conexao";//no banco de dados em um outro IP e não em localhost
		final int PORTA = 3306;
		
							/*
							 * String de conexão com o banco logal (no meu computador)
							 * //Informações para conexão
							 *final String 	SERVIDOR = "localhost",
							 *				BANCO_DE_DADOS = "agenda_reuniao",
							 *				USUARIO = "root",
							 *				SENHA = "root";
							 *final int PORTA = 3306; 
							 */

		//URL de conexão
		final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO_DE_DADOS;
				
		//Criando e abrindo a conexao e aplicando-a no no objeto conexao
		conexao = DriverManager.getConnection(URL, USUARIO, SENHA);		
	}
	
	
	public static void fechar()
	{
		if(conexao != null)
		{
			try 
			{
				conexao.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	
	}
}
