package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		//testar se, realmente, h� a conex�o com o banco de dados com o m�todo da classe DB
		//Connection conectar = DB.fazerConexao();
		//DB.fecharConexao();
		
		Connection conectar = null;
		Statement statementSQL = null; //Colocar o comando SQL
		ResultSet resultSet = null; //Guardar o resultado da consulta
		
		//ResultSet => Dados armazenados em forma de tabela
		////first() => [move para a posi��o 1, se houver]
		////beforeFirst() => [move para a posi��o 0]
		////next() => [move para o pr�ximo, e retorna false se j� estiver no �ltimo]
		////absolute(int) => [move para a posi��o dada, lembrando que dados reais come�am em 1]
		
		try {
			
			conectar = DB.fazerConexao();
			
			statementSQL = conectar.createStatement();
			resultSet = statementSQL.executeQuery("SELECT * FROM DEPARTMENT");
			
			while (resultSet.next()) { //enquanto tiver dados = true //Percorrer o resultSet // Tabela
				
				System.out.println("Id: "+resultSet.getInt("Id")+", Nome: "+resultSet.getString("Name"));
				
			}
			
		} 
		catch (SQLException erro) {
			erro.printStackTrace();
		}
		finally {
			DB.fecharResultSet(resultSet);
			DB.fecharStatement(statementSQL);
			DB.fecharConexao();
		}


	}

}
