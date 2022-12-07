package DAO;

import DTO.ProductosDTO;

public interface ProductosDAO {
	
	
		public void registrar(ProductosDTO producto);
		public void mostrar(ProductosDTO producto);
		public void buscarPrecio(ProductosDTO producto); 
		public void buscarNombre(ProductosDTO nombre);
			
		
		
}	