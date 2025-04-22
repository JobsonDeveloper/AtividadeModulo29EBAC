package br.com.jobson.dao;

import br.com.jobson.dao.jdbc.ConnectionFactory;
import br.com.jobson.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {
    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tb_cliente (id, codigo, nome) " +
                    "VALUES (nextval ('sq_cliente'),?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());
            stm.setString(2, cliente.getNome());

            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Cliente consultar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet response = null;
        Cliente cliente = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_cliente WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            response = stm.executeQuery();

            if (response.next()) {
                cliente = new Cliente();
                cliente.setId(response.getLong("id"));
                cliente.setNome(response.getString("nome"));
                cliente.setCodigo(response.getString("codigo"));
            }

            return cliente;
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_cliente WHERE codigo = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getCodigo());

            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE tb_cliente SET nome = ? WHERE codigo = ?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCodigo());

            return stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public List<Cliente> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente = new Cliente();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_cliente";
            stm = connection.prepareStatement(sql);
            ResultSet response = stm.executeQuery();

            while (response.next()) {
                cliente.setId(response.getLong("id"));
                cliente.setNome(response.getString("nome"));
                cliente.setCodigo(response.getString("codigo"));
                lista.add(cliente);
            }
        }
        catch (Exception e) {
            throw e;
        } finally {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return lista;
    }
}
