package com.revisaoWeb.service;

import com.revisaoWeb.dao.DocumentoDao;
import com.revisaoWeb.model.Documento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoDao documentoDao;

    public DocumentoService(DocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
    }

    public void criar(Documento documento) {
        documentoDao.criar(documento);
    }

    public List<Documento> listar() {
        return documentoDao.listar();
    }

    public Documento listarId(int id) {
        return documentoDao.listarId(id);
    }

    public void atualizar(Documento documento) {
        documentoDao.atualizar(documento);
    }

    public void deletar(int id) {
        documentoDao.deletar(id);
    }



}
