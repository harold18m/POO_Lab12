package Presentacion;

import DAO.*;
import DTO.ProductosDTO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textPrecio;
	private JTextField textCantidad;
	
	private void limpiar() {
		textCodigo.setText("");
		textNombre.setText("");
		textCantidad.setText("");
		textPrecio.setText("");
	}
	
	Conexion conexion = Conexion.getInstance();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Menu de productos");
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(0, 0, 308, 411);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Código:");
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 122, 94, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre:");
		lblNewLabel_1_1.setFont(new Font("Roboto", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 164, 94, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Cantidad:");
		lblNewLabel_1_2.setFont(new Font("Roboto", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 207, 94, 14);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Precio:");
		lblNewLabel_1_3.setFont(new Font("Roboto", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(10, 254, 94, 14);
		panel.add(lblNewLabel_1_3);
		
		JButton btnAgregar = new JButton("Agregar\r\n");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				implementacionProductosDAO producto_dao = new implementacionProductosDAO();
				ProductosDTO producto = new ProductosDTO();
				
				int codigo = Integer.parseInt(textCodigo.getText());
				String nombre =  textNombre.getText();
				int cantidad = Integer.parseInt(textCantidad.getText());
				double precio = Double.parseDouble(textPrecio.getText());

				producto.setCodigo(codigo);
				producto.setNombre(nombre);
				producto.setCantidad(cantidad);
				producto.setPrecio(precio);
				
				producto_dao.registrar(producto);
				JOptionPane.showMessageDialog(null,"Registro exitoso");
				limpiar();
 			}
		});
		btnAgregar.setBounds(33, 340, 113, 23);
		panel.add(btnAgregar);
		
		JButton btnBuscarNombre = new JButton("Buscar por nombre");
		btnBuscarNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				implementacionProductosDAO producto_dao = new implementacionProductosDAO();
				ProductosDTO producto = new ProductosDTO();
				
				if(textNombre.getText().equals("")) {
					JOptionPane.showInternalMessageDialog(null,"Por favor digite el nombre a buscar");
				}
				else {
					String nombre =  textNombre.getText();
					producto.setNombre(nombre);
					producto_dao.buscarNombre(producto);	
					
					textCodigo.setText(String.valueOf(producto.getCodigo()));
					textNombre.setText(producto.getNombre());
					textCantidad.setText(String.valueOf(producto.getCantidad()));
					textPrecio.setText(String.valueOf(producto.getPrecio()));
					
					JOptionPane.showInternalMessageDialog(null,"Registro encontrado");
				}
			}
		});
		btnBuscarNombre.setBounds(203, 161, 94, 23);
		panel.add(btnBuscarNombre);
		
		JButton btnBuscarPrecio = new JButton("Buscar por precio");
		btnBuscarPrecio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				implementacionProductosDAO producto_dao = new implementacionProductosDAO();
				ProductosDTO producto = new ProductosDTO();
				
				if(textPrecio.getText().equals("")) {
					JOptionPane.showInternalMessageDialog(null,"Por favor digite el precio a buscar");
				}
				else {
					double precio = Double.parseDouble(textPrecio.getText());
					producto.setPrecio(precio);
					producto_dao.buscarPrecio(producto);	
					
					textCodigo.setText(String.valueOf(producto.getCodigo()));
					textNombre.setText(producto.getNombre());
					textCantidad.setText(String.valueOf(producto.getCantidad()));
					textPrecio.setText(String.valueOf(producto.getPrecio()));
					
					JOptionPane.showInternalMessageDialog(null,"Registro encontrado");
				}
			
			}
		});
		btnBuscarPrecio.setBounds(203, 251, 94, 23);
		panel.add(btnBuscarPrecio);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(98, 120, 86, 20);
		panel.add(textCodigo);
		textCodigo.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(98, 162, 86, 20);
		panel.add(textNombre);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(98, 252, 86, 20);
		panel.add(textPrecio);
		
		JLabel lblMen = new JLabel("Menú");
		lblMen.setHorizontalAlignment(SwingConstants.CENTER);
		lblMen.setFont(new Font("Roboto", Font.BOLD, 17));
		lblMen.setBounds(10, 29, 282, 36);
		panel.add(lblMen);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel modelo = new DefaultTableModel();
					table.setModel(modelo);
				
					Connection conexionMysql = conexion.conectar();
					PreparedStatement seleccion = conexionMysql.prepareStatement("Select * from productos");
					ResultSet consulta = seleccion.executeQuery();
					
					ResultSetMetaData datos =consulta.getMetaData();
					int cantidadColumnas = datos.getColumnCount();
					
					modelo.addColumn("C\u00F3digo");
					modelo.addColumn("Nombre");
					modelo.addColumn("Cantidad");
					modelo.addColumn("Precio");
					
					int anchos [] = {90,90,90,90};
					for (int i = 0; i < cantidadColumnas; i++) {
						table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
					}
					
					while(consulta.next()) {
						 Object arreglo[]= new Object[cantidadColumnas];
						 for(int i = 0;i <cantidadColumnas; i++) {
							 arreglo[i] = consulta.getObject(i+1);
					}
						modelo.addRow(arreglo); 
					}
					conexion.cerrarConexion();
					}catch(SQLException error){
						System.out.println(error);
					}
				}
		});
		
		
		btnMostrar.setBounds(167, 340, 113, 23);
		panel.add(btnMostrar);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(98, 205, 86, 20);
		panel.add(textCantidad);
		textCantidad.setColumns(10);
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(306, 0, 278, 411);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 105, 262, 218);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Cantidad", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Resultado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 29, 262, 36);
		panel_1.add(lblNewLabel);
	}
}
