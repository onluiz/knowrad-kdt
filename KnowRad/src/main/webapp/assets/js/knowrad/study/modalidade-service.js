"use strict"

var ModalidadeService = {

    service: new AbstractService("modalidade/"),

    search: function(searchText, limit, callback) {

        this.service.doGET("search", ({searchText: searchText, limit: limit}), callback);

    }

};