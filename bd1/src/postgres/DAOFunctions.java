package postgres;

import java.sql.SQLException;
/*
 * Data Access Object Funções
 */

public interface DAOFunctions {
	
	/*
	 * Métodos abstratos para a criação de entidades no banco com base
	 * nas necessidades do modelo ER
	 * Como temos atualmente no modelo dois tipos de usuários, o usuário final e as oficinas
	 * o tratamento de ambos será diferenciado. Ao mesmo tempo poderemos ter mais
	 * tipos como o moto club ou eventos.
	 */
	void insert(User user) throws SQLException;
	
	void insert(Moto moto) throws SQLException;
	
	void insert(User type) throws SQLException;
	

}
