package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class App {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		Connection conectar = null;
		PreparedStatement preparedStatement = null;
		
		try { //conectar-se ao banco
			
			conectar = DB.fazerConexao();
			preparedStatement = conectar.prepareStatement( //Iniciar o prepared statement
					"INSERT INTO seller"
					+"(Name, Email, BirthDate, BaseSalary, DepartmentId)" //descrever tabelas, exceto a do ID que é automática pelo MySQL
					+"VALUES"
					+"(?, ?, ?, ?, ?)" // '?' = placeholder == lugar onde depois colocaremos o valor
					);
			
		//agora vamos fazer um comando para trocar o '?', pelo valor desejado:

		preparedStatement.setString(1, "Carlos"); // número 1 para substituir a primeira interrogação '?'
		preparedStatement.setString(2, "carlos@gmail.com"); // número 1 para substituir a primeira interrogação '?'
		preparedStatement.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
		preparedStatement.setDouble(4, 3000.0);
		preparedStatement.setInt(5, 4);
		
		int linhasAlteradas = preparedStatement.executeUpdate(); //executar
		
		System.out.println("Inserido com Sucesso! Rows Affected: "+linhasAlteradas);
	
		} catch (SQLException erro) {
			erro.printStackTrace();
		} catch (ParseException erro) {
			erro.printStackTrace();

		}
		finally {
			DB.fecharStatement(preparedStatement);
			DB.fecharConexao();
		}
	}

}
