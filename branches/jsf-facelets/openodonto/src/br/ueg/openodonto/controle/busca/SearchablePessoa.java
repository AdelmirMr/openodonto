package br.ueg.openodonto.controle.busca;

import java.lang.reflect.Field;
import java.util.ArrayList;

import br.ueg.openodonto.controle.servico.ExampleRequest;
import br.ueg.openodonto.dominio.Pessoa;
import br.ueg.openodonto.persistencia.dao.sql.SqlWhereOperatorType;
import br.ueg.openodonto.persistencia.orm.OrmResolver;
import br.ueg.openodonto.persistencia.orm.OrmTranslator;
import br.ueg.openodonto.servico.busca.FieldFacade;
import br.ueg.openodonto.servico.busca.MessageDisplayer;
import br.ueg.openodonto.validator.EmptyValidator;
import br.ueg.openodonto.validator.NullValidator;
import br.ueg.openodonto.validator.Validator;
import br.ueg.openodonto.validator.ValidatorFactory;

public class SearchablePessoa extends AbstractSearchable<Pessoa>{

	private static final long serialVersionUID = -5786526767273778486L;

	public SearchablePessoa(MessageDisplayer displayer) {
		super(Pessoa.class,displayer);
	}
	
	@Override
	protected void buildFacade() {
		super.buildFacade();
		OrmTranslator translator = new OrmTranslator(OrmResolver.getAllFields(new ArrayList<Field>(), Pessoa.class, true));
		getFacade().add(new FieldFacade("C�digo",translator.getColumn("codigo")));
		getFacade().add(new FieldFacade("Nome",translator.getColumn("nome")));
	}
	
	@Override
	protected void buildFilter() {
		super.buildFilter();
		buildNameFilter();
		buildCodigoFilter();
	}
	
	private void buildNameFilter(){
		Validator validator = ValidatorFactory.newStrRangeLen(100,3, true);
		getFiltersMap().put("nomeFilter", buildBasicFilter("nomeFilter","Nome",validator));
	}
	
	private void buildCodigoFilter(){
		Validator validator = ValidatorFactory.newNumMax(Integer.MAX_VALUE);
		getFiltersMap().put("idFilter",buildBasicFilter("idFilter","C�digo",validator));
	}

	@Override
	public Pessoa buildExample() {
		ExampleRequest<Pessoa> request  = new ExampleRequest<Pessoa>(this);		
		request.getFilterRelation().add(request.new TypedFilter("nomeFilter", "nome",SqlWhereOperatorType.LIKE));
		request.getFilterRelation().add(request.new TypedFilter("idFilter","codigo",SqlWhereOperatorType.EQUAL));
		request.getInvalidPermiteds().add(NullValidator.class);
		request.getInvalidPermiteds().add(EmptyValidator.class);
		Pessoa target = getManageExample().processExampleRequest(request);
		return target;
	}

}
