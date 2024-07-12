package exercicio02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class carroDAO extends DAO {
    
    public carroDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    
    
    public boolean insert(carro carro) {
        conectar();
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO carros (codigo, nome, marca, nota) "
                       + "VALUES (" + carro.getCodigo() + ", '" + carro.getNome() + "', '"  
                       + carro.getMarca() + "', '" + carro.getNota() + "')";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    
    public carro get(int codigo) {
        carro carro = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM carros WHERE codigo=" + codigo;
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);    
            if (rs.next()){            
                carro = new carro(rs.getInt("codigo"), rs.getString("nome"), rs.getString("marca"), rs.getString("nota").charAt(0));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carro;
    }
    
    
    public List<carro> get() {
        return get("");
    }

    
    public List<carro> getOrderByCodigo() {
        return get("codigo");        
    }
    
    
    public List<carro> getOrderByNome() {
        return get("nome");        
    }
    
    
    public List<carro> getOrderByMarca() {
        return get("marca");        
    }
    
    
    private List<carro> get(String orderBy) {    
        List<carro> carros = new ArrayList<carro>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM carros" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while (rs.next()) {                    
                carro c = new carro(rs.getInt("codigo"), rs.getString("nome"), rs.getString("marca"), rs.getString("nota").charAt(0));
                carros.add(c);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carros;
    }


    public boolean update(carro carro) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE carros SET nome = '" + carro.getNome() + "', marca = '"  
                       + carro.getMarca() + "', nota = '" + carro.getNota() + "'"
                       + " WHERE codigo = " + carro.getCodigo();
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean delete(int codigo) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM carros WHERE codigo = " + codigo;
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }    
    
}

