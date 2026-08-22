package com.revisaoWeb.service;

import com.revisaoWeb.dao.CompartilhamentoDao;
import com.revisaoWeb.model.Compartilhamento;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CompartilhamentoService {

    private final CompartilhamentoDao compartilhamentoDao;

    public CompartilhamentoService(CompartilhamentoDao compartilhamentoDao) {
        this.compartilhamentoDao = compartilhamentoDao;
    }

    public void criar(Compartilhamento compartilhamento) {
        compartilhamentoDao.criar(compartilhamento);
    }

    public List<Compartilhamento> listar() {
        return compartilhamentoDao.listar();
    }

    public Compartilhamento listarId(int id) {
        return compartilhamentoDao.listarId(id);
    }

    public void atualizar(int id, Compartilhamento compartilhamento) {
        compartilhamentoDao.atualizar(id, compartilhamento);
    }

    public void deletar(int id) {
        compartilhamentoDao.deletar(id);
    }



}
