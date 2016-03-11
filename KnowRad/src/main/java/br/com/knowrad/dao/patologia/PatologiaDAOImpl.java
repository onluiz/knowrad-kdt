package br.com.knowrad.dao.patologia;

import br.com.knowrad.dao.AbstractDAO;
import br.com.knowrad.dto.PatologiaDTO;
import br.com.knowrad.entity.patologia.Patologia;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PatologiaDAOImpl extends AbstractDAO<Patologia> implements PatologiaDAO {

    public List<PatologiaDTO> search(String searchText, Integer limit) {
        if(searchText == null || searchText.equals(""))
            return new ArrayList<PatologiaDTO>();

        if(limit == null || limit == 0)
            return new ArrayList<PatologiaDTO>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT p.id_patologia, p.descricao FROM patologia p ");
        sql.append(" WHERE upper(p.descricao) LIKE upper(concat('%', :searchText, '%')) ");
        sql.append(" ORDER BY p.descricao ASC ");
        sql.append(" LIMIT :limit ");

        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("searchText", searchText);
        query.setParameter("limit", limit);

        Iterator iterator = query.getResultList().iterator();
        List<PatologiaDTO> listDTO = new ArrayList<PatologiaDTO>();

        while (iterator.hasNext()) {
            Object[] object = (Object[]) iterator.next();
            PatologiaDTO dto = new PatologiaDTO();
            dto.setIdPatologia(Long.valueOf(String.valueOf(object[0])));
            dto.setDescricao(String.valueOf(object[1]));
            listDTO.add(dto);
        }

        return listDTO;
    }

}
