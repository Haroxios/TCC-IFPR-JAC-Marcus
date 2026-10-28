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
        Documento documento = documentoDao.listarId(id);
        return documento;
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
            verificarDocumentoAtrasado(n.getIdDocumento(), n.getDataProximaRevisao());
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

        if (!documento.getStatusRevisao().name().equals("ANDAMENTO")) {
            throw new RuntimeException();
        }

        int nivelAtual = documento.getNivelRevisao();
        int novoNivel = NivelRevisao.proximoNivel(nivelAtual);

        int dias = NivelRevisao.qntProximoDia(novoNivel);
        LocalDate novaData = documento.getDataProximaRevisao().plusDays(dias);

        documentoDao.atualizarRevisao(id, novoNivel, novaData);
    }

    public void adiar(int id) {
        verificarExistenciaDocumento(id);
        Documento documento = documentoDao.listarId(id);
        LocalDate novaData = documento.getDataProximaRevisao().plusDays(1);
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
        LocalDate data = LocalDate.now();
        documentoDao.atualizarRevisaoStatus(id, 1, data, Status.ANDAMENTO);
    }

    private void verificarExistenciaDocumento(int id) {
        if (documentoDao.listarId(id) == null) {
            throw new RuntimeException();
        }
    }

    private void verificarDocumentoAtrasado(int id, LocalDate dataRevisao) {
        int atraso = dataRevisao.compareTo(LocalDate.now());

        if (atraso < 0) {
            documentoDao.atualizarStatus(id, Status.PENDENTE);
        }
    }
}
