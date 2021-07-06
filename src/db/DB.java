package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection; //import do java.sql
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conectar = null; //método para iniciar conexão com o banco de dados (Connection = Objeto de conexão com o banco de dados JDBC)
	
	public static Connection fazerConexao() { //método para conectar-se ao banco de dados
		if (conectar == null) { //testar se o objeto ainda estiver nulo
			try {
			
				Properties propriedades = carregarPropriedades(); // método criado abaixo, que "pega" as propriedades do Banco no arquivo [db.properties]
				String urlBanco = propriedades.getProperty("dburl"); //dburl pois é o nome definido no arquivo[db.properties]->[jdbc:mysql://localhost:3306/SistemaPedido]
				conectar = DriverManager.getConnection(urlBanco, propriedades); //Obter conexão com o Banco de Dados
			//Conectar no JDBC = Instanciar um objeto do tipo Connection
			
			}
			catch (SQLException erro) {
				throw new DbException(erro.getMessage()); //Baseada na Classe de exceção criada
			}
		}
		return conectar;
	}
	
	public static void fecharConexao() { //fechar a conexão
		if (conectar != null) { //se a conexão estiver instanciada, mandaremos fechar
			try {
				conectar.close();
			}
			catch (SQLException erro) {
				throw new DbException(erro.getMessage()); //Baseada na Classe de exceção criada
			}
			
		}
	}
	
	private static Properties carregarPropriedades() { //método para carregar as propriedades definidas no arquivo[db.properties]
		try (FileInputStream fis = new FileInputStream("db.properties")) {
			Properties propriedades = new Properties();
			propriedades.load(fis);
			return propriedades;
		} catch (IOException erro) {
			throw new DbException(erro.getMessage()); //Baseada na Classe de exceção criada
		}
	}
	
	//tratar exceções do Statement, que está na classe Program
	public static void fecharStatement(Statement statementSQL) {
		if(statementSQL != null) {
			try {
				statementSQL.close();
			} catch (SQLException erro) {
				throw new DbException(erro.getMessage()); //Baseada na Classe de exceção criada
			}
		}
	}
	
	//tratar exceções do ResultSet, que está na classe Program
	public static void fecharResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException erro) {
				throw new DbException(erro.getMessage()); //Baseada na Classe de exceção criada
			}
		}
	}


}
