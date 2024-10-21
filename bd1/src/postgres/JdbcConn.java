package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConn implements AutoCloseable {
	private Connection connection;
	private String host;
	private String user;
	private String password;
	private String table;
	private String schema;
	
	public JdbcConn(String host, String user, String password) {
		super();
		this.host = host;
		this.user = user;
		this.password = password;
		
		try {
			this.connection = DriverManager.getConnection(host, user, password);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public Statement createStatement() throws SQLException{
		return connection.createStatement();
	}
	
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    
    /*Criação de queries personalizadas pelo usuário
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        try (Statement stm = createStatement()) {
            return stm.executeQuery(sql);
        }
    }
    
    /*Lista todas as tabelas existentes na base de dados
     */
    public ResultSet listAllTables() throws SQLException {
    	String sql="SELECT * FROM informatio_schema.tables";
    	try (Statement stm = createStatement()) {
        	return stm.executeQuery(sql);
    	}
    }
    
    /*Lista os campos de um schema e tabela especificados
     */
    public ResultSet listFieldsFromTable(String schema, String table) throws SQLException{
    	String sql="SELECT * FROM informatio_schema.columns WHERE table_schema ='"+schema+"' AND table_name = '"+table+"'";
    	try (Statement stm = createStatement()){
    		return stm.executeQuery(sql);
    	}
    }
    
    /*Lista os registros de um schema e tabela especificados
     */
    public ResultSet selectAllFromTable(String table) throws SQLException{
     	String sql="SELECT * FROM table_name = '"+table+"'";
    	try (Statement stm = createStatement()){
    		return stm.executeQuery(sql);
    	}
    }
}
