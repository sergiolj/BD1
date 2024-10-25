package postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConn {
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
    public void listAllTables(String schema) throws SQLException {
    	String sql="SELECT table_name FROM information_schema.tables WHERE table_schema='"+schema+"' AND table_type='BASE TABLE'";
    	try (Statement stm = createStatement()) {
    		ResultSet rs = stm.executeQuery(sql);
    		System.out.println("TABELAS DO SCHEMA "+schema.toUpperCase());
    		list(rs);
		}catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void list(ResultSet rs) {
    	int i=1;
    	try{
    		while (rs.next()) {
    		System.out.println(i+" - "+rs.getString(1));
            i++;
    		}
    	}catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
    /*Lista os campos de um schema e tabela especificados
     */
    public void listFieldsFromTable(String schema, String table) throws SQLException{
    	String sql="SELECT column_name FROM information_schema.columns WHERE table_name='"+table+"'";
    	try (Statement stm = createStatement()){
    	ResultSet rs = stm.executeQuery(sql);
    	System.out.println("\nTABELA: "+table.toUpperCase());
    	list(rs);
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
