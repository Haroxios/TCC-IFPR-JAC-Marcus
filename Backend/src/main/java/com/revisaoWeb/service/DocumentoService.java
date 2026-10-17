package com.revisaoWeb.service;

import com.revisaoWeb.dao.DocumentoDao;
import com.revisaoWeb.model.Documento;
import com.revisaoWeb.model.Caderno;

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

    public void atualizar(int id, Documento documento) {
        documentoDao.atualizar(id, documento);
    }

    public void deletar(int id) {
        documentoDao.deletar(id);
    }

    public List<Documento> listarDocumentosUsuario(List<Integer> idCadernos) {
        return documentoDao.listarDocumentosUsuario(idCadernos);
    }

    // fazer para que uma nova data seja marcada
    public void revisaoDocumento() {

    }

    // fazer com que o status do documento seja atualizado isso não significa atualizar o documento todo
    public void statusDocumento() {

    }

}
