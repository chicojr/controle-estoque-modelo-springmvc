package br.com.ifpe.estoque.controller.produto;

import org.springframework.core.convert.converter.Converter;

import br.com.ifpe.estoque.model.produto.CategoriaProduto;
import br.com.ifpe.estoque.model.produto.CategoriaProdutoDao;

/**
 * @author Roberto Alencar
 */
public class CategoriaProdutoConverter implements Converter<String, CategoriaProduto> {

    public CategoriaProduto convert(String id) {

	if (!id.equals("")) {

	    CategoriaProdutoDao dao = new CategoriaProdutoDao();
	    return dao.buscarPorId(Integer.valueOf(id));
	} else {
	    return null;
	}
    }
}
