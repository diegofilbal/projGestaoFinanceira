/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.*;
import java.util.ArrayList;

import conexao.Conexao;
import dominio.Vendedor;

/**
 *
 * @author diego
 */
public class VendedorDAO {
    
    private Conexao con = new Conexao("localhost", "5432", "Gestao Financeira", "postgres", "postgres");
    
    private final String BUSCARCPF = "SELECT * FROM \"Vendedor\" WHERE \"CPF\"=?";
    private final String INSERIR = "INSERT INTO \"Vendedor\"(\"CPF\",\"nome\",\"telefone\",\"email\") VALUES (?,?,?,?)";
    private final String RELATORIO = "SELECT * FROM \"Vendedor\"";
    private final String ALTERARCPF = "UPDATE \"Vendedor\" SET \"CPF\"=? WHERE \"CPF\"=?";
    private final String ALTERARNOME = "UPDATE \"Vendedor\" SET \"nome\"=? WHERE \"CPF\"=?";
    private final String ALTERARTEL = "UPDATE \"Vendedor\" SET \"telefone\"=? WHERE \"CPF\"=?";
    private final String ALTERAREMAIL = "UPDATE \"Vendedor\" SET \"email\"=? WHERE \"CPF\"=?";
    private final String EXCLUIR = "DELETE FROM \"Vendedor\" WHERE \"CPF\"=?";
    
    public Vendedor buscarCPF(String CPF){
        Vendedor v = null;
        try {
            con.conectar();
            PreparedStatement buscarCPF = con.getConexao().prepareStatement(BUSCARCPF);
            buscarCPF.setString(1, CPF);
            ResultSet rsBuscarCPF = buscarCPF.executeQuery();
            while (rsBuscarCPF.next()) {
                v = new Vendedor(rsBuscarCPF.getString("CPF"),rsBuscarCPF.getString("nome"),
                        rsBuscarCPF.getString("telefone"), rsBuscarCPF.getString("email"));
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao buscar Vendedor no banco de dados" + e.getMessage());
            e.printStackTrace();
        }
        return v;
    }

    public void inserir(Vendedor v){
        try {
            con.conectar();
            PreparedStatement inserir = con.getConexao().prepareStatement(INSERIR);
            inserir.setString(1, v.getCPF());
            inserir.setString(2, v.getNome());
            inserir.setString(3, v.getTelefone());
            inserir.setString(4, v.getEmail());
            inserir.execute();
            System.out.println("Vendedor cadastrado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Vendedor no banco de dados: " +e.getMessage());
            e.printStackTrace();
        }
    }
    
    public ArrayList<Vendedor> relatorio(){
        ArrayList relVendedor = new ArrayList();
        try {
            con.conectar();
            PreparedStatement relatorio = con.getConexao().prepareStatement(RELATORIO);
            ResultSet rsRelatorio = relatorio.executeQuery();
            while (rsRelatorio.next()) {
                Vendedor v = new Vendedor(rsRelatorio.getString("CPF"),rsRelatorio.getString("nome"),
                        rsRelatorio.getString("telefone"), rsRelatorio.getString("email"));
		relVendedor.add(v); 
            }
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao emitir relatório de Vendedor: "+e.getMessage());
            e.printStackTrace();
        }
        return relVendedor;
    }
    
    public void alterarCPF(String CPF, String novo){
        try {
            con.conectar();
            PreparedStatement alterarCPF = con.getConexao().prepareStatement(ALTERARCPF);
            alterarCPF.setString(1, novo);
            alterarCPF.setString(2, CPF);
            alterarCPF.execute();
            System.out.println("CPF do Vendedor alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar o CPF do Vendedor no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarNome(String CPF, String novo){
        try {
            con.conectar();
            PreparedStatement alterarNome = con.getConexao().prepareStatement(ALTERARNOME);
            alterarNome.setString(1, novo);
            alterarNome.setString(2, CPF);
            alterarNome.execute();
            System.out.println("Nome alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar nome do Vendedor no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarTelefone(String CPF, String novo){
        try {
            con.conectar();
            PreparedStatement alterarTelefone = con.getConexao().prepareStatement(ALTERARTEL);
            alterarTelefone.setString(1, novo);
            alterarTelefone.setString(2, CPF);
            alterarTelefone.execute();
            System.out.println("Telefone alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar telefone do Vendedor no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void alterarEmail(String CPF, String novo){
        try {
            con.conectar();
            PreparedStatement alterarEmail = con.getConexao().prepareStatement(ALTERAREMAIL);
            alterarEmail.setString(1, novo);
            alterarEmail.setString(2, CPF);
            alterarEmail.execute();
            System.out.println("Email alterado com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao alterar email do Vendedor no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }  
    
    public void excluir(String CPF){
        try {
            con.conectar();
            PreparedStatement excluir = con.getConexao().prepareStatement(EXCLUIR);
            excluir.setString(1, CPF);
            excluir.execute();
            System.out.println("Vendedor excluido com sucesso!");
            con.desconectar();
        } catch (Exception e) {
            System.out.println("Erro ao excluir Vendedor no banco de dados: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
}