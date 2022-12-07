package DAO;

import DTO.ProductosDTO;
import java.sql.*;

public class implementacionProductosDAO implements ProductosDAO {

	Conexion conexion = Conexion.getInstance();
	
	@Override
	public void registrar(ProductosDTO producto) {
		try {
		
			Connection conectar = conexion.conectar();
			PreparedStatement registrar = conectar.prepareStatement("insert into productos values (?,?,?,?)"); 
		
			registrar.setInt(1,producto.getCodigo());
			registrar.setString(2,producto.getNombre());
			registrar.setInt(3,producto.getCantidad());
			registrar.setDouble(4,producto.getPrecio());
			registrar.executeUpdate();
			conexion.cerrarConexion();
			}
		catch(SQLException e){
		System.out.println(e);
			}		
	}

	@Override
	public void mostrar(ProductosDTO producto) {
	


		try {
			
			Connection conectar = conexion.conectar();
			PreparedStatement mostrar = conectar.prepareStatement("select * from productos"); 
			mostrar.executeUpdate();
			conexion.cerrarConexion();
			
		}
			
			
		catch(SQLException e){
		System.out.println(e);
			}	
		
	
	}

	@Override
	public void buscarPrecio(ProductosDTO producto) {

		try {
			
			Connection conectar = conexion.conectar();
			PreparedStatement buscar = conectar.prepareStatement("select * from productos where precio = ?"); 
		
			buscar.setDouble(1,producto.getPrecio());
			ResultSet consulta = buscar.executeQuery();
			
			if(consulta.next()) {
				producto.setNombre(consulta.getString("nombre"));
				producto.setCodigo(Integer.parseInt(consulta.getString("codigo")));
				producto.setCantidad(Integer.parseInt(consulta.getString("cantidad")));
				producto.setPrecio(Double.parseDouble(consulta.getString("precio")));
			}
			conexion.cerrarConexion();
		}
		catch(SQLException e){
		System.out.println(e);
			}	
		
	}

	@Override
	public void buscarNombre(ProductosDTO producto) {
		try {
			
			Connection conectar = conexion.conectar();
			PreparedStatement buscar = conectar.prepareStatement("select * from productos where nombre = ?"); 
		
			buscar.setString(1,producto.getNombre());
			ResultSet consulta = buscar.executeQuery();
			
			if(consulta.next()) {
				producto.setNombre(consulta.getString("nombre"));
				producto.setCodigo(Integer.parseInt(consulta.getString("codigo")));
				producto.setCantidad(Integer.parseInt(consulta.getString("cantidad")));
				producto.setPrecio(Double.parseDouble(consulta.getString("precio")));
			}
			conexion.cerrarConexion();
			}
		catch(SQLException e){
		System.out.println(e);
			}	
	}

}
