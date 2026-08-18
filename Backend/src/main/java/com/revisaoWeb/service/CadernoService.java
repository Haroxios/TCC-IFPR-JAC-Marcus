package com.revisaoWeb.service;

import com.revisaoWeb.model.Caderno;
import com.revisaoWeb.dao.CadernoDao;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CadernoService {

    private final CadernoDao cadernoDao;

    public CadernoService(CadernoDao cadernoDao) {
        this.cadernoDao = cadernoDao;
    }

    public void criar(Caderno caderno) {
        cadernoDao.criar(caderno);
    }

    public List<Caderno> listar() {
        return cadernoDao.listar();
    }

    public Caderno listarId(int id) {
        return cadernoDao.listarId(id);
    }

    public void atualizar(Caderno caderno) {
        cadernoDao.atualizar(caderno);
    }

    public void deletar(int id) {
        cadernoDao.deletar(id);
    }



}
