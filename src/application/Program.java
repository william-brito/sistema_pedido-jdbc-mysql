package application;

import java.sql.Connection;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		//testar se, realmente, h� a conex�o com o banco de dados com o m�todo da classe DB
		Connection conectar = DB.fazerConexao();
		DB.fecharConexao();

	}

}
