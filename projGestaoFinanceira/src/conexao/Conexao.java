/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.*;

/**
 *
 * @author diego
 */
public class Conexao {
	private final String usuario;
	private final String senha;
	private final String caminho;
	private Connection con;
	
	public Conexao(String local, String porta, String banco, String u, String s){
		caminho = "jdbc:postgresql://" + local + ":" + porta + "/" + banco;
		usuario = u;
		senha = s;
	}
	
	public void conectar(){
		try{
			Class.forName("org.postgresql.Driver"); //Carrega o driver (inicializa um objeto da classe org.postgresql.Driver)
			con = DriverManager.getConnection(caminho, usuario, senha); //Cria a conexão
		}catch(Exception e){
			System.err.println("Erro na conexão com o postgreSQL: "+e.getMessage());
		}
	}

	public void desconectar(){
		try{
			con.close(); // fecha a conexão
		}catch(Exception e){
			System.out.println("Erro na desconexão com o PostGreSQL: "+e.getMessage());
		}
	}
	
	public Connection getConexao(){
		return con;
	}
    
    
}
