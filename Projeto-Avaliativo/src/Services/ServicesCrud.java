package Services;

import Dao.DaoI;

public class ServicesCrud <T, ID> {
    private Dao.DaoI<T, ID> dao;

    public ServicesCrud(DaoI<T, ID> dao) {
        this.dao = dao;
    }

    public T save(T entity) {
        return dao.save(entity);
    }

    public java.util.Optional<T> findById(ID id) {
        return dao.findById(id);
    }

    public java.util.List<T> findAll() {
        return dao.findAll();
    }

    public void delete(T entity) {
        dao.delete(entity);
    }
}
