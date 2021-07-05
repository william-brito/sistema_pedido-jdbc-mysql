package application;

import java.sql.Connection;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		//testar se, realmente, há a conexão com o banco de dados com o método da classe DB
		Connection conectar = DB.fazerConexao();
		DB.fecharConexao();

	}

}
