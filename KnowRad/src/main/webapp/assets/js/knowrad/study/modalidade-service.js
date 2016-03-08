"use strict"

var ModalidadeService = {

    service: new AbstractService("modalidade/"),

    /**
     * Traz todas as modalidades do banco
     * @param callback
     */
    findAllDTO: function(callback) {

        this.service.doGET("findAllDTO", ({}), callback);

    },

};