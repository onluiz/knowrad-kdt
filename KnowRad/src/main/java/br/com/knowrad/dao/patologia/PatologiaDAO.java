package br.com.knowrad.dao.patologia;

import br.com.knowrad.dto.PatologiaDTO;
import br.com.knowrad.entity.patologia.Patologia;

import java.util.List;

public interface PatologiaDAO {
    void persist(Patologia c);
    void merge(Patologia c);
    void remove(final Long id);
    Patologia findById(final Long id);
    List<PatologiaDTO> search(String searchText, Integer limit);
}
