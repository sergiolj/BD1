package postgres;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String host = "jdbc:postgresql://localhost:5432/nomeDatabase";
		String user = "postgres";
		String password = "admin";
		String sql="SELECT * FROM information_schema.tables WHERE table_schema = 'public'";
		String schema = "public";

		/*Essa metodologia de criação do link com a base de dados usa a interface AutoCloseable
		 * garantindo que a conexão será encerrada após o uso isso se chama bloco try-with-resources
		 */
		try(JdbcConn database = new JdbcConn(host, user, password)){
			ResultSet rs = database.executeQuery(sql);
			int i=1;
			while(rs.next()) {
				System.out.println("Tupla"+i+rs.getString(i));
				i++;
			}
			
			database.listAllTables();
			database.listFieldsFromTable(schema, "user_type");
			
		}catch (SQLException e ) {
			e.printStackTrace();
		}
	}

}
