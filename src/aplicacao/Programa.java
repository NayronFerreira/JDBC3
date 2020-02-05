package aplicacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Programa {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentID)"
				+ "VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS
				);
		st.setString(1, "Indrid Santanna");
		st.setString(2, "tataia_meuamor@gmail.com");
		st.setDate(3, new Date(sdf.parse("11/04/1995").getTime()));
		st.setDouble(4, 9000);
		st.setInt(5, 2);
		
		int resultado = st.executeUpdate();
		
		if(resultado>0) {
			ResultSet rs = st.getGeneratedKeys();
			while(rs.next()) {
				int id = rs.getInt(1);
				System.out.println("Pronto! Id = "+id);
			}
		}
		else {System.out.println("Nenhuma linha foi alterada");}
		
		System.out.println("Foram alterada(s) "+resultado+" linha(s)");
			}
		catch(SQLException x) {
			x.printStackTrace();
		}
		catch(ParseException x) {
			x.printStackTrace();
		}
		finally{
			DB.closeStatement(st);
			DB.closeConnection();}
	
	}

}
