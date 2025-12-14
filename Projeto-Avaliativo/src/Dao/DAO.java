package Dao;

import java.util.Optional;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T,ID> {
	Boolean save(T entity) throws SQLException;
    Boolean update(T entity) throws SQLException; 
    Optional<T> findById(ID id)throws SQLException;
    List<T> findAll() throws SQLException;
    Boolean delete(T entity)throws SQLException;
}
