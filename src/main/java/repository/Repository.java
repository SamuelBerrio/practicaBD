package repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
public interface Repository <T>{
    List<T> list();

    T byId(Long id);
    void save(T t);
    void delete(Long id);
}