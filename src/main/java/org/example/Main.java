package org.example;

import conexion.ConexionBD;
import model.Producto;
import repository.Repository;
import repository.impl.RepositoryImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try(Connection conn = ConexionBD.getInstance()){
            Repository<Producto> repository = new RepositoryImpl();
            listProducts(repository);
            getProductById(repository);
            //addProduct(repository);
            //updateProduct(repository);
            deleteProduct(repository);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void deleteProduct(Repository<Producto> repository) {
        System.out.println("DELETE PRODUCT:-------------------");
        repository.delete(Long.valueOf(2));
    }

    private static void getProductById(Repository<Producto> repository) {
        System.out.println("GET PRODUCT BY ID: -------------------");
        System.out.println(repository.byId(Long.valueOf(1)));
    }

    private static void listProducts(Repository<Producto> repository) {
        System.out.println("LISTA DE PRODUCTOS");
        for(Producto producto: repository.list()){
            System.out.println("Producto: "+producto.getNombre()+" / Precio: "+producto.getPrecio());
        }
    }
}