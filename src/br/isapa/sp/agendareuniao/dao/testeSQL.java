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
		//2� - Jogar a exce��o para cima com throw
		throw e;
	}
	finally
	{
		//3� - Fechar a conexao
		//Este comando fica no finally porque a conexao deve ser encerrada
		//tanto se der erro quanto n�o der
		ConexaoMySQL.fechar();
	} */
	 
	 ConexaoMySQL.abrir();
	 System.out.println("Conex�o aberta!");
	 ConexaoMySQL.fechar();
	
 }	
}
