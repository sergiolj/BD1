package postgres;

//import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String host = "jdbc:postgresql://localhost:5432/motoapp";
		String user = "postgres";
		String password = "admin";
		//String sql="SELECT * FROM information_schema.tables";
		String schema = "public";

		/*Essa metodologia de criação do link com a base de dados usa a interface AutoCloseable
		 * garantindo que a conexão será encerrada após o uso isso se chama bloco try-with-resources
		 */
		try{
			JdbcConn database = new JdbcConn(host, user, password);
//			ResultSet rs = database.executeQuery(sql);
//			int i=1;
//			while(rs.next()) {
//				System.out.println("Tupla"+i+rs.getString(i));
//				i++;
//				}
			database.listAllTables(schema);
			
			database.listFieldsFromTable(schema, "moto");
			
		}catch (SQLException e ) {
			e.printStackTrace();
		}
	}

}
