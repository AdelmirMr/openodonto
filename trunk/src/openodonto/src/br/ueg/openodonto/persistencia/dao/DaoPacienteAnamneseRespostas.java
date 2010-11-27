package br.ueg.openodonto.persistencia.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ueg.openodonto.dominio.PacienteAnamneseResposta;
import br.ueg.openodonto.dominio.PacienteQuestionarioAnamnese;
import br.ueg.openodonto.dominio.QuestaoAnamnese;
import br.ueg.openodonto.persistencia.dao.sql.CrudQuery;
import br.ueg.openodonto.persistencia.dao.sql.IQuery;
import br.ueg.openodonto.persistencia.orm.Dao;
import br.ueg.openodonto.persistencia.orm.OrmFormat;


@Dao(classe=PacienteAnamneseResposta.class)
public class DaoPacienteAnamneseRespostas extends DaoCrud<PacienteAnamneseResposta>{

	private static final long serialVersionUID = 3114100911322875992L;
	
	public DaoPacienteAnamneseRespostas() {
		super(PacienteAnamneseResposta.class);
	}	

	@Override
	public PacienteAnamneseResposta getNewEntity() {
		return new PacienteAnamneseResposta();
	}
	
	public void updateRelationshipPQA(PacienteQuestionarioAnamnese pqa) throws SQLException{
		Long pacienteId = pqa.getPacienteId();
		Long questionarioAnamneseId = pqa.getQuestionarioAnamneseId();
		if(pqa.getRespotas() != null){
			List<PacienteAnamneseResposta> respostas = new ArrayList<PacienteAnamneseResposta>(pqa.getRespotas().values());
			DaoPacienteQuestionarioAnamnese daoPQA = (DaoPacienteQuestionarioAnamnese) DaoFactory.getInstance().getDao(PacienteQuestionarioAnamnese.class);			
			List<PacienteQuestionarioAnamnese> pqas = daoPQA.getPQARelationshipPAR(pacienteId, questionarioAnamneseId); 
			//TODO Acabar m�todo
		}


	}

	public Map<QuestaoAnamnese,PacienteAnamneseResposta> getRespostasRelationshipPQA(Long pacienteId,Long questionarioAnamneseId) throws SQLException{
		DaoQuestaoAnamnese daoQA = (DaoQuestaoAnamnese) DaoFactory.getInstance().getDao(QuestaoAnamnese.class);
		OrmFormat orm = new OrmFormat(new PacienteAnamneseResposta(null, questionarioAnamneseId, pacienteId));
		IQuery query = CrudQuery.getSelectQuery(PacienteAnamneseResposta.class, orm.formatNotNull(), "*");
		List<PacienteAnamneseResposta> respostas = getSqlExecutor().executarQuery(query);
		Map<QuestaoAnamnese,PacienteAnamneseResposta> questaoRespostasMap = new HashMap<QuestaoAnamnese, PacienteAnamneseResposta>();
		for(PacienteAnamneseResposta resposta : respostas){
			QuestaoAnamnese questao = daoQA.findByKey(resposta.getQuestaoAnamneseId());
			if(questao != null){
				questaoRespostasMap.put(questao, resposta);
			}
		}	
	    return questaoRespostasMap;
	}
	
}
