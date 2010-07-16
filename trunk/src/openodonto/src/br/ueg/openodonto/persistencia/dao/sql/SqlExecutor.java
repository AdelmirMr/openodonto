package br.ueg.openodonto.persistencia.dao.sql;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface SqlExecutor<T> extends Serializable{
    public List<T> executarQuery(IQuery query) throws SQLException;

    public List<T> executarNamedQuery(String query, Collection<Object> params,
	    String... fields) throws SQLException;

    public List<T> executarNamedQuery(String query, Collection<Object> params,
	    Integer quant, String... fields) throws SQLException;

    public List<T> executarQuery(String query, Object valorParametro)
	    throws SQLException;

    public List<T> executarQuery(String query, Object valorParametro,
	    Integer quant) throws SQLException;

    public List<T> executarQuery(String query, Collection<Object> params)
	    throws SQLException;

    public List<T> executarQuery(String query, Collection<Object> params,
	    Integer quant) throws SQLException;
}
