package repository.impl;

import conexion.ConexionBD;
import model.Producto;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class RepositoryImpl implements Repository {
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }

    @Override
    public List<Producto> list()  {
        List<Producto> productoList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from productos")) {
            while (resultSet.next()) {
                Producto producto = createProduct(resultSet);
                productoList.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productoList;
    }

    @Override
    public Producto byId(Long id) {
        Producto producto = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM productos WHERE id =?"))
        {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                producto = createProduct(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void save(Producto o) {
        try (PreparedStatement stmt = getConnection().prepareStatement("INSERT INTO productos VALUES (?,?,?)");) {
            stmt.setInt(1, NULL);
            stmt.setString(2, o.getNombre());
            stmt.setDouble(3, o.getPrecio());
            stmt.executeUpdate();
            System.out.println("Conexion realizada con exito");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM productos WHERE id ="+ id);) {
            stmt.executeUpdate();
            System.out.println("Eliminado con exito");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Producto createProduct(ResultSet resultSet) throws SQLException {
        Producto producto = new Producto(resultSet.getInt("id"),resultSet.getString("nombre"), resultSet.getDouble("precio"));
        return producto;
    }
}
