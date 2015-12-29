package br.com.knowrad.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import br.com.knowrad.dto.LaudoDTO;
import br.com.knowrad.util.SolrConnection;
import br.com.knowrad.util.Util;

public class SolrSearchEngine {

	private SolrClient solr = new SolrConnection().getSolrConnection();
	
	@SuppressWarnings("rawtypes")
	public List<LaudoDTO> searchLaudos(String search) {

		List<LaudoDTO> listLaudo = new ArrayList<LaudoDTO>();
		
		if(solr == null)
			return listLaudo;
		
		try {
			
			SolrQuery query = new SolrQuery();

			if(search == null || search.equals(""))
                query.setQuery("*:*");
            else
                query.setQuery("_text_:" + search);
			
			QueryResponse response = solr.query(query);
			SolrDocumentList list = response.getResults();
			
			for(Map solrMap : list) {
				LaudoDTO dto = new LaudoDTO();
				dto.setIdPaciente(Util.verifyLong(solrMap.get("idPaciente")));
				dto.setNomePaciente(Util.verifyString(solrMap.get("nomePaciente")));
				dto.setTitulo(Util.verifyString(solrMap.get("titulo")));
				dto.setTexto(Util.verifyString(solrMap.get("texto")));
				dto.setModalidade(Util.verifyString(solrMap.get("modalidade")));
				listLaudo.add(dto);
			}
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listLaudo;
		
	}
	
}