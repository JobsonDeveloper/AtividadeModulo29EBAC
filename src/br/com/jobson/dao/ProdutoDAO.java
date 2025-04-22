package br.com.jobson.dao;

import br.com.jobson.dao.jdbc.ConnectionFactory;
import br.com.jobson.domain.Cliente;
import br.com.jobson.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {

    public ProdutoDAO() {

    }

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        Integer response = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tb_produto (id, nome, codigo, preco) " +
                    "VALUES (nextval ('sq_produto'),?,?,?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getCodigo());
            stm.setDouble(3, produto.getPreco());

            response = stm.executeUpdate();
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

        return response;
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        Integer response = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM tb_produto WHERE codigo = ?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getCodigo());
            response = stm.executeUpdate();
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

        return response;
    }

    @Override
    public Produto consultar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        Produto produto = new Produto();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_produto WHERE codigo = ?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, codigo);
            ResultSet response = stm.executeQuery();

            while (response.next()) {
                produto.setId(response.getLong("id"));
                produto.setNome(response.getString("nome"));
                produto.setCodigo(response.getString("codigo"));
                produto.setPreco(response.getDouble("preco"));
            }
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

        return produto;
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "UPDATE tb_produto SET nome = ?, preco = ? WHERE codigo = ?";

            stm = connection.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getPreco());
            stm.setString(3, produto.getCodigo());

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
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement stm = null;
        List<Produto> lista = new ArrayList<>();
        Produto produto = new Produto();

        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM tb_produto";
            stm = connection.prepareStatement(sql);
            ResultSet response = stm.executeQuery();

            while (response.next()) {
                produto.setId(response.getLong("id"));
                produto.setNome(response.getString("nome"));
                produto.setCodigo(response.getString("codigo"));
                produto.setPreco(response.getDouble("preco"));
                lista.add(produto);
            }
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

        return lista;
    }
}
