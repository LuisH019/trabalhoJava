/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produto.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import produto.Produto;
/**
 *
 * @author Pichau
 */
public class ProdutoDao {
    private int ultimoId;
    private HashMap <Integer, Produto> produtosBd;
    
    public ProdutoDao (){
        produtosBd = new HashMap<Integer, Produto>();
    }
    public void cadastrar (Produto produto){
        ultimoId++;
	produto.setId(ultimoId);
	produtosBd.put(ultimoId, produto);
    }
    public List <Produto> mostrar(){
        return new ArrayList<Produto>(produtosBd.values());
    }
    public void alterar (Produto produto, int alterado){
        produtosBd.replace(alterado, produto);
        produto.setId(ultimoId);
	produtosBd.put(ultimoId, produto);
    }
    public void apagar (int removido){
        produtosBd.remove (removido);
    }

    public HashMap<Integer, Produto> getProdutosBd() {
        return produtosBd;
    }

    public void setProdutosBd(HashMap<Integer, Produto> getProdutosBd) {
        this.produtosBd = getProdutosBd;
    }
}
