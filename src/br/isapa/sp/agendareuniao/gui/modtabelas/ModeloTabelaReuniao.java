package br.isapa.sp.agendareuniao.gui.modtabelas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.isapa.sp.agendareuniao.modelos.Reuniao;

/*
 * CRIANDO O MODELO DE TABELA
 * 1� Herda a classe AbstractTableModel
 * 2� Crie uma lista da entidade trabalhada
 * 3� Implemente os m�todos abstratos
 */

public class ModeloTabelaReuniao extends AbstractTableModel
{
	/*
	 * PROPRIEDADES
	 */
	
	private List<Reuniao> listaReuniao;
	
	
	/**
	 * Recebe uma lista de contatos que ser� utilizada para criar o conte�do da tabela
	 * @param listaContatos - A lista que tser� utilizada para construir a tabela
	 */
	public ModeloTabelaReuniao(List<Reuniao> listaReuniao)
	{
		super();
		this.listaReuniao = listaReuniao;
	}

	
	
	/*
	 * RETORNA A QUANTIDADE DE COLUNAS QUE HAVER� EM SUA TABELA
	 */
	
	@Override
	public int getColumnCount() 
	{
		return 6;
	}

	
	/*
	 * RETORNA A QUANTIDADE DE LINHAS QUE HAVER� EM SUA TABELA
	 */
	
	@Override
	public int getRowCount() 
	{
		return listaReuniao.size();
	}

	@Override
	public Object getValueAt(int indLinha, int indColuna) 
	{
		switch (indColuna) 
		{
			case 0:
				return listaReuniao.get(indLinha).getUsuario();
			case 1:
				return listaReuniao.get(indLinha).getAssunto();
			case 2:
				return listaReuniao.get(indLinha).getDataMesAno();
			case 3:
				return listaReuniao.get(indLinha).getHoraInicio();
			case 4:
				return listaReuniao.get(indLinha).getHoraFinal();
			case 5:
				return listaReuniao.get(indLinha).getObservacao();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int indColuna) 
	{
		switch (indColuna) 
		{
		case 0:
			return "USU�RIO";
		case 1:
			return "ASSUNTO";
		case 2:
			return "DATA";
		case 3:
			return "INICIO";
		case 4:
			return "FINAL";
		case 5:
			return "OBSERVA��O";
		default:
			return null;
		}
	}
}
