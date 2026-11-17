package com.revisaoWeb.service;

import com.revisaoWeb.dao.DocumentoDao;
import com.revisaoWeb.model.Documento;
import com.revisaoWeb.model.NivelRevisao;
import com.revisaoWeb.model.Status;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    private final DocumentoDao documentoDao;

    public DocumentoService(DocumentoDao documentoDao) {
        this.documentoDao = documentoDao;
    }

    public void criar(Documento documento) {
        documento.setNivelRevisao(0);
        documento.setStatusRevisao(Status.ANDAMENTO);
        documento.setDataProximaRevisao(LocalDate.now().plusDays(1));

        documento.setPrevia(gerarPrevia(documento.getConteudo()));

        documentoDao.criar(documento);
    }

    public List<Documento> listar() {
        return documentoDao.listar();
    }

    public Documento listarId(int id) {
        return documentoDao.listarId(id);
    }

    public void atualizar(int id, Documento documento) {
        documento.setPrevia(gerarPrevia(documento.getConteudo()));
        documentoDao.atualizar(id, documento);
    }

    public void deletar(int id) {
        documentoDao.deletar(id);
    }

    public List<Documento> listarDocumentosUsuario(List<Integer> idCadernos) {
        List<Documento> documentosList = documentoDao.listarDocumentosUsuario(idCadernos);

        for (Documento n : documentosList) {
            verificarDocumentoAtrasado(n);
        }

        return documentosList;
    }

    private String gerarPrevia(String conteudo) {
        if (conteudo == null) {
            return "";
        }

        if (conteudo.length() <= 150) {
            return conteudo;
        }

        return conteudo.substring(0, 150).trim() + "...";
    }

    public void revisar(int id) {
        verificarExistenciaDocumento(id);
        Documento documento = documentoDao.listarId(id);

        if (!documento.getStatusRevisao().name().equals("ANDAMENTO") || !documento.getStatusRevisao().name().equals("PENDENTE")) {
            throw new RuntimeException();
        }

        int nivelAtual = documento.getNivelRevisao();
        int novoNivel = NivelRevisao.proximoNivel(nivelAtual);

        int dias = NivelRevisao.qntProximoDia(novoNivel);
        LocalDate novaData = LocalDate.now().plusDays(dias);

        documentoDao.atualizarRevisaoStatus(id, novoNivel, novaData, Status.ANDAMENTO);
    }

    public void adiar(int id) {
        verificarExistenciaDocumento(id);
        LocalDate novaData = LocalDate.now().plusDays(1);
        documentoDao.atualizarData(id, novaData);
    }

    public void desativar(int id) {
        verificarExistenciaDocumento(id);
        documentoDao.atualizarStatus(id, Status.DESATIVADO);
    }

    public void concluir(int id) {
        verificarExistenciaDocumento(id);
        documentoDao.atualizarStatus(id, Status.CONCLUIDO);
    }

    public void reativar(int id) {
        Documento doc = verificarExistenciaDocumento(id);

        LocalDate data = LocalDate.now();

        documentoDao.atualizarRevisaoStatus(id, 1, data, Status.ANDAMENTO);
    }

    private Documento verificarExistenciaDocumento(int id) {
        Documento doc = documentoDao.listarId(id);

        if (doc == null) {
            throw new RuntimeException();
        }

        return doc;
    }

    // um métoodo para trazer o docmento

    private Documento verificarDocumentoAtrasado(Documento documento) {
        Documento doc = new Documento();
        int atraso = documento.getDataProximaRevisao().compareTo(LocalDate.now());

        if (!documento.getStatusRevisao().equals(Status.CONCLUIDO)) {
            return doc;
        }

        if (atraso <= 0) {
            doc.setStatusRevisao(Status.PENDENTE);
        }

        return doc;
    }
}
