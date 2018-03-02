package br.isapa.sp.agendareuniao.dao;


public class testeSQL 
{
 public static void main(String[] args) throws Exception {
	

	/*try 
	{
		ConexaoMySQL.abrir();
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
	} */
	 
	 ConexaoMySQL.abrir();
	 System.out.println("Conexão aberta!");
	 ConexaoMySQL.fechar();
	
 }	
}
