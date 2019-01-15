package br.isapa.sp.agendareuniao.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.isapa.sp.agendareuniao.gui.frames.FrameAgendamento;
import br.isapa.sp.agendareuniao.modelos.Reuniao;

public class DAOReuniao 
{
	public static FrameAgendamento frameAgendamento;
		public static void inserir(Reuniao reuniao) throws Exception 
	{
		
		final String SQL = "INSERT INTO agenda_reuniao SET usuario = ?, assunto = ?, dataMesAno = ?, horaInicio = ?, horaFinal = ?, sala = ?, observacao = ?";
	
		try 
		{
			//1ª - Abrir a conexao
			ConexaoMySQL.abrir();
		
			//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
			//por valor) e executar sqls
			//RETURN_GENERATED_KEYS: Avisa que , ao executar o script será gerado um id e este deve ser
			//retornado
			PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL, 
																	PreparedStatement.RETURN_GENERATED_KEYS);
			
			
			//Aplicando os valores nos '?'
			stmt.setString(1, reuniao.getUsuario().toLowerCase());
			stmt.setString(2, reuniao.getAssunto());
			stmt.setString(3, reuniao.getDataMesAno());
			stmt.setString(4, reuniao.getHoraInicio());
			stmt.setString(5, reuniao.getHoraFinal());
			stmt.setString(6, reuniao.getSala());
			stmt.setString(7, reuniao.getObservacao());
			
			//Executando o Script
			stmt.execute();
			
			//Retornando o ID gerado pelo banco
			ResultSet idGerado = stmt.getGeneratedKeys();
			
			//Lendo o ID gerado
			if(idGerado.next())
			{
				//Pegando o ID gerado e aplicando-o no contato
				reuniao.setId(idGerado.getInt(1));
			}
			
		} 
		catch (Exception e) 
		{
			//2ª - Jogar a exceção para cima com throw
			throw e;
		}
		finally
		{
			//3ª - Fechar a conexao
			//Este comando fica no finally porque a conexao deve ser encerrada
			//tanto se der erro quanto não der
			ConexaoMySQL.fechar();
		}												
			
	}
	
	public static List<Reuniao> buscarSerra() throws Exception
	{
		//Lista que armazenará os contatos que estão no banco
		List<Reuniao> listaReuniao = new ArrayList<>();
		
		final String SQL = "SELECT id, usuario, assunto, dataMesAno,TIME_FORMAT(horaInicio, '%H:%i'),TIME_FORMAT(horaFinal, '%H:%i'), observacao FROM agenda_reuniao "
				+ "WHERE sala = 'Sala Serra' AND dataMesAno >= curdate() ORDER BY dataMesAno, horaInicio;";
		
		try 
		{
			//1ª - Abrir a conexao
			ConexaoMySQL.abrir();
		
			//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
			//por valor) e executar sqls
			PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);
			
			
			//Executando o Script
			ResultSet resultadosDaBusca = stmt.executeQuery();
			
			while(resultadosDaBusca.next())
			{
				//Objeto da linha
				Reuniao r = new Reuniao();
				
				//Buscando as informações da linha 
				r.setId(resultadosDaBusca.getInt(1));
				r.setUsuario(resultadosDaBusca.getString("usuario")); //Nome da coluna 
				r.setAssunto(resultadosDaBusca.getString("assunto")); 
		
						//String data recebe o conteúdo da busca no banco de dados na coluna dataMesAno
						String data = resultadosDaBusca.getString("dataMesAno");
						
						//Criando um array de 3 posições
						String array[] = new String[3];
						//Separando a String data a cada hifen(-)
						array = data.split("-");
						//Variavel data recebe os array separados pela barra (/)
						data = array[2] + "/" + array[1] + "/" + array[0];
						
				r.setDataMesAno(data); //Nome da coluna
				r.setHoraInicio(resultadosDaBusca.getString("TIME_FORMAT(horaInicio, '%H:%i')"));
				r.setHoraFinal(resultadosDaBusca.getString("TIME_FORMAT(horaFinal, '%H:%i')"));
				r.setObservacao(resultadosDaBusca.getString("observacao"));
				
				
				//Adicionar o objeto c na lista de contatos
				listaReuniao.add(r);
				
			}
			
		} 
		catch (Exception e) 
		{
			//2ª - Jogar a exceção para cima com throw
			throw e;
		}
		finally
		{
			//3ª - Fechar a conexao
			//Este comando fica no finally porque a conexao deve ser encerrada
			//tanto se der erro quanto não der
			ConexaoMySQL.fechar();
		}
		
		//Retorna a lista de contatos
		return listaReuniao;	
		
	}
	
	public static List<Reuniao> buscarCN() throws Exception
	{
		//Lista que armazenará os contatos que estão no banco
				List<Reuniao> listaReuniao = new ArrayList<>();
				
				final String SQL = "SELECT id, usuario, assunto, dataMesAno, TIME_FORMAT(horaInicio, '%H:%i'),TIME_FORMAT(horaFinal, '%H:%i'), observacao FROM agenda_reuniao "+
						 "WHERE sala = 'Sala Conselheiro' AND dataMesAno >= curdate() ORDER BY dataMesAno, horaInicio;";
				
				try 
				{
					//1ª - Abrir a conexao
					ConexaoMySQL.abrir();
				
					//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
					//por valor) e executar sqls
					PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);
					
					
					//Executando o Script
					ResultSet resultadosDaBusca = stmt.executeQuery();
					
					while(resultadosDaBusca.next())
					{
						//Objeto da linha
						Reuniao r = new Reuniao();
						
						//Buscando as informações da linha 
						r.setId(resultadosDaBusca.getInt(1));
						r.setUsuario(resultadosDaBusca.getString("usuario")); //Nome da coluna
						r.setAssunto(resultadosDaBusca.getString("assunto"));
				
								//String data recebe o conteúdo da busca no banco de dados na coluna dataMesAno
								String data = resultadosDaBusca.getString("dataMesAno");
								
								//Criando um array de 3 posições
								String array[] = new String[3];
								//Separando a String data a cada hifen(-)
								array = data.split("-");
								//Variavel data recebe os array separados pela barra (/)
								data = array[2] + "/" + array[1] + "/" + array[0];
								
						r.setDataMesAno(data); //Nome da coluna
						r.setHoraInicio(resultadosDaBusca.getString("TIME_FORMAT(horaInicio, '%H:%i')"));
						r.setHoraFinal(resultadosDaBusca.getString("TIME_FORMAT(horaFinal, '%H:%i')"));
						r.setObservacao(resultadosDaBusca.getString("observacao"));
						
						
						//Adicionar o objeto c na lista de contatos
						listaReuniao.add(r);
						
					}
					
				} 
				catch (Exception e) 
				{
					//2ª - Jogar a exceção para cima com throw
					throw e;
				}
				finally
				{
					//3ª - Fechar a conexao
					//Este comando fica no finally porque a conexao deve ser encerrada
					//tanto se der erro quanto não der
					ConexaoMySQL.fechar();
				}
				
				//Retorna a lista de contatos
				return listaReuniao;	
		
	}
	
	public static List<Reuniao> buscarItajai() throws Exception
	{
		//Lista que armazenará os contatos que estão no banco
				List<Reuniao> listaReuniao = new ArrayList<>();
				
				final String SQL = "SELECT id, usuario, assunto, dataMesAno,TIME_FORMAT(horaInicio, '%H:%i'),TIME_FORMAT(horaFinal, '%H:%i'), observacao FROM agenda_reuniao "
						+ "WHERE sala = 'Sala Itajai' AND dataMesAno >= curdate() ORDER BY dataMesAno, horaInicio;";
				
				try 
				{
					//1ª - Abrir a conexao
					ConexaoMySQL.abrir();
				
					//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
					//por valor) e executar sqls
					PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);
					
					
					//Executando o Script
					ResultSet resultadosDaBusca = stmt.executeQuery();
					
					while(resultadosDaBusca.next())
					{
						//Objeto da linha
						Reuniao r = new Reuniao();
						
						//Buscando as informações da linha 
						r.setId(resultadosDaBusca.getInt(1));
						r.setUsuario(resultadosDaBusca.getString("usuario")); //Nome da coluna
						r.setAssunto(resultadosDaBusca.getString("assunto"));
				
								//String data recebe o conteúdo da busca no banco de dados na coluna dataMesAno
								String data = resultadosDaBusca.getString("dataMesAno");
								
								//Criando um array de 3 posições
								String array[] = new String[3];
								//Separando a String data a cada hifen(-)
								array = data.split("-");
								//Variavel data recebe os array separados pela barra (/)
								data = array[2] + "/" + array[1] + "/" + array[0];
								
						r.setDataMesAno(data); //Nome da coluna
						r.setHoraInicio(resultadosDaBusca.getString("TIME_FORMAT(horaInicio, '%H:%i')"));
						r.setHoraFinal(resultadosDaBusca.getString("TIME_FORMAT(horaFinal, '%H:%i')"));
						r.setObservacao(resultadosDaBusca.getString("observacao"));
						
						
						//Adicionar o objeto c na lista de contatos
						listaReuniao.add(r);
						
					}
					
				} 
				catch (Exception e) 
				{
					//2ª - Jogar a exceção para cima com throw
					throw e;
				}
				finally
				{
					//3ª - Fechar a conexao
					//Este comando fica no finally porque a conexao deve ser encerrada
					//tanto se der erro quanto não der
					ConexaoMySQL.fechar();
				}
				
				//Retorna a lista de contatos
				return listaReuniao;	
	}
	
	public static List<Reuniao> buscarLeo() throws Exception
	{
		//Lista que armazenará os contatos que estão no banco
				List<Reuniao> listaReuniao = new ArrayList<>();
				
				final String SQL = "SELECT id, usuario, assunto, dataMesAno, TIME_FORMAT(horaInicio, '%H:%i'),TIME_FORMAT(horaFinal, '%H:%i'), observacao FROM agenda_reuniao "+
						 "WHERE sala = 'Sala Leopoldina' AND dataMesAno >= curdate() ORDER BY dataMesAno, horaInicio;";
				
				try 
				{
					//1ª - Abrir a conexao
					ConexaoMySQL.abrir();
				
					//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
					//por valor) e executar sqls
					PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);
					
					
					//Executando o Script
					ResultSet resultadosDaBusca = stmt.executeQuery();
					
					while(resultadosDaBusca.next())
					{
						//Objeto da linha
						Reuniao r = new Reuniao();
						
						//Buscando as informações da linha 
						r.setId(resultadosDaBusca.getInt(1));
						r.setUsuario(resultadosDaBusca.getString("usuario")); //Nome da coluna 
						r.setAssunto(resultadosDaBusca.getString("assunto")); //Nome da coluna 
				
								//String data recebe o conteúdo da busca no banco de dados na coluna dataMesAno
								String data = resultadosDaBusca.getString("dataMesAno");
								
								//Criando um array de 3 posições
								String array[] = new String[3];
								//Separando a String data a cada hifen(-)
								array = data.split("-");
								//Variavel data recebe os array separados pela barra (/)
								data = array[2] + "/" + array[1] + "/" + array[0];
								
						r.setDataMesAno(data); //Nome da coluna
						r.setHoraInicio(resultadosDaBusca.getString("TIME_FORMAT(horaInicio, '%H:%i')"));
						r.setHoraFinal(resultadosDaBusca.getString("TIME_FORMAT(horaFinal, '%H:%i')"));
						r.setObservacao(resultadosDaBusca.getString("observacao"));
						
						
						//Adicionar o objeto c na lista de contatos
						listaReuniao.add(r);
						
					}
					
				} 
				catch (Exception e) 
				{
					//2ª - Jogar a exceção para cima com throw
					throw e;
				}
				finally
				{
					//3ª - Fechar a conexao
					//Este comando fica no finally porque a conexao deve ser encerrada
					//tanto se der erro quanto não der
					ConexaoMySQL.fechar();
				}
				
				//Retorna a lista de contatos
				return listaReuniao;	
	}
	
	public static List<Reuniao> buscarCTI() throws Exception
	{
		//Lista que armazenará os contatos que estão no banco
		List<Reuniao> listaReuniao = new ArrayList<>();
		
		final String SQL = "SELECT id, usuario, assunto, dataMesAno,TIME_FORMAT(horaInicio, '%H:%i'),TIME_FORMAT(horaFinal, '%H:%i'), observacao FROM agenda_reuniao "
				+ "WHERE sala = 'Sala CTI' AND dataMesAno >= curdate() ORDER BY dataMesAno, horaInicio;";
		
		try 
		{
			//1ª - Abrir a conexao
			ConexaoMySQL.abrir();
		
			//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
			//por valor) e executar sqls
			PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);
			
			
			//Executando o Script
			ResultSet resultadosDaBusca = stmt.executeQuery();
			
			while(resultadosDaBusca.next())
			{
				//Objeto da linha
				Reuniao r = new Reuniao();
				
				//Buscando as informações da linha 
				r.setId(resultadosDaBusca.getInt(1));
				r.setUsuario(resultadosDaBusca.getString("usuario")); //Nome da coluna 
				r.setAssunto(resultadosDaBusca.getString("assunto")); 
		
						//String data recebe o conteúdo da busca no banco de dados na coluna dataMesAno
						String data = resultadosDaBusca.getString("dataMesAno");
						
						//Criando um array de 3 posições
						String array[] = new String[3];
						//Separando a String data a cada hifen(-)
						array = data.split("-");
						//Variavel data recebe os array separados pela barra (/)
						data = array[2] + "/" + array[1] + "/" + array[0];
						
				r.setDataMesAno(data); //Nome da coluna
				r.setHoraInicio(resultadosDaBusca.getString("TIME_FORMAT(horaInicio, '%H:%i')"));
				r.setHoraFinal(resultadosDaBusca.getString("TIME_FORMAT(horaFinal, '%H:%i')"));
				r.setObservacao(resultadosDaBusca.getString("observacao"));
				
				
				//Adicionar o objeto c na lista de contatos
				listaReuniao.add(r);
				
			}
			
		} 
		catch (Exception e) 
		{
			//2ª - Jogar a exceção para cima com throw
			throw e;
		}
		finally
		{
			//3ª - Fechar a conexao
			//Este comando fica no finally porque a conexao deve ser encerrada
			//tanto se der erro quanto não der
			ConexaoMySQL.fechar();
		}
		
		//Retorna a lista de contatos
		return listaReuniao;	
		
	}
	
	public static List<Reuniao> buscarGUA() throws Exception
	{
		//Lista que armazenará os contatos que estão no banco
		List<Reuniao> listaReuniao = new ArrayList<>();
		
		final String SQL = "SELECT id, usuario, assunto, dataMesAno,TIME_FORMAT(horaInicio, '%H:%i'),TIME_FORMAT(horaFinal, '%H:%i'), observacao FROM agenda_reuniao "
				+ "WHERE sala = 'Sala Guarulhos' AND dataMesAno >= curdate() ORDER BY dataMesAno, horaInicio;";
		
		try 
		{
			//1ª - Abrir a conexao
			ConexaoMySQL.abrir();
		
			//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
			//por valor) e executar sqls
			PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);
			
			
			//Executando o Script
			ResultSet resultadosDaBusca = stmt.executeQuery();
			
			while(resultadosDaBusca.next())
			{
				//Objeto da linha
				Reuniao r = new Reuniao();
				
				//Buscando as informações da linha 
				r.setId(resultadosDaBusca.getInt(1));
				r.setUsuario(resultadosDaBusca.getString("usuario")); //Nome da coluna 
				r.setAssunto(resultadosDaBusca.getString("assunto")); 
		
						//String data recebe o conteúdo da busca no banco de dados na coluna dataMesAno
						String data = resultadosDaBusca.getString("dataMesAno");
						
						//Criando um array de 3 posições
						String array[] = new String[3];
						//Separando a String data a cada hifen(-)
						array = data.split("-");
						//Variavel data recebe os array separados pela barra (/)
						data = array[2] + "/" + array[1] + "/" + array[0];
						
				r.setDataMesAno(data); //Nome da coluna
				r.setHoraInicio(resultadosDaBusca.getString("TIME_FORMAT(horaInicio, '%H:%i')"));
				r.setHoraFinal(resultadosDaBusca.getString("TIME_FORMAT(horaFinal, '%H:%i')"));
				r.setObservacao(resultadosDaBusca.getString("observacao"));
				
				
				//Adicionar o objeto c na lista de contatos
				listaReuniao.add(r);
				
			}
			
		} 
		catch (Exception e) 
		{
			//2ª - Jogar a exceção para cima com throw
			throw e;
		}
		finally
		{
			//3ª - Fechar a conexao
			//Este comando fica no finally porque a conexao deve ser encerrada
			//tanto se der erro quanto não der
			ConexaoMySQL.fechar();
		}
		
		//Retorna a lista de contatos
		return listaReuniao;	
		
	}
	
	public static void deletar(Reuniao reuniao) throws Exception 
	{
		final String SQL = "DELETE FROM agenda_reuniao WHERE id = ? AND usuario = ?";
		
		try 
		{
			//1ª - Abrir a conexao
			ConexaoMySQL.abrir();
		
			//PreparedStatment - Classe responsável por aplicar valores na SQL (trocar ?
			//por valor) e executar sqls
			//RETURN_GENERATED_KEYS: Avisa que , ao executar o script será gerado um id e este deve ser
			//retornado
			PreparedStatement stmt = ConexaoMySQL.getConexao().prepareStatement(SQL);
			
			//Aplicando o id
			stmt.setInt(1, reuniao.getId());
			stmt.setString(2, reuniao.getUsuario());
			
			//Executando o Script
			stmt.execute();
			
		} 
		catch (Exception e) 
		{
			//2ª - Jogar a exceção para cima com throw
			throw e;
		}
		finally
		{
			//3ª - Fechar a conexao
			//Este comando fica no finally porque a conexao deve ser encerrada
			//tanto se der erro quanto não der
			ConexaoMySQL.fechar();
		}
	}
	
}