package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection; //import do java.sql
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection conectar = null; //m�todo para iniciar conex�o com o banco de dados (Connection = Objeto de conex�o com o banco de dados JDBC)
	
	public static Connection fazerConexao() { //m�todo para conectar-se ao banco de dados
		if (conectar == null) { //testar se o objeto ainda estiver nulo
			try {
			
				Properties propriedades = carregarPropriedades(); // m�todo criado abaixo, que "pega" as propriedades do Banco no arquivo [db.properties]
				String urlBanco = propriedades.getProperty("dburl"); //dburl pois � o nome definido no arquivo[db.properties]->[jdbc:mysql://localhost:3306/SistemaPedido]
				conectar = DriverManager.getConnection(urlBanco, propriedades); //Obter conex�o com o Banco de Dados
			//Conectar no JDBC = Instanciar um objeto do tipo Connection
			
			}
			catch (SQLException erro) {
				throw new DbException(erro.getMessage()); //Baseada na Classe de exce��o criada
			}
		}
		return conectar;
	}
	
	public static void fecharConexao() { //fechar a conex�o
		if (conectar != null) { //se a conex�o estiver instanciada, mandaremos fechar
			try {
				conectar.close();
			}
			catch (SQLException erro) {
				throw new DbException(erro.getMessage()); //Baseada na Classe de exce��o criada
			}
			
		}
	}
	
	private static Properties carregarPropriedades() { //m�todo para carregar as propriedades definidas no arquivo[db.properties]
		try (FileInputStream fis = new FileInputStream("db.properties")) {
			Properties propriedades = new Properties();
			propriedades.load(fis);
			return propriedades;
		} catch (IOException erro) {
			throw new DbException(erro.getMessage()); //Baseada na Classe de exce��o criada
		}
	}

}
