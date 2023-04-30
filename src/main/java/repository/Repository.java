package repository;
import model.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public interface Repository <T>{
    List<T> list();

    T byId(Long id);
    void save(Producto o);

    void delete(Long id);
}