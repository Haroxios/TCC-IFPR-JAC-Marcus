package com.revisaoWeb.service;

import com.revisaoWeb.dao.TagDocDao;
import com.revisaoWeb.model.TagDoc;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDocService {

    private final TagDocDao tagDocDao;

    public TagDocService(TagDocDao tagDocDao) {
        this.tagDocDao = tagDocDao;
    }

    public void criar(TagDoc tagDoc) {
        tagDocDao.criar(tagDoc);
    }

    public List<TagDoc> listar() {
        return tagDocDao.listar();
    }

    public TagDoc listarId(int id) {
        return tagDocDao.listarId(id);
    }

    public void atualizar(int id, TagDoc tagDoc) {
        tagDocDao.atualizar(id, tagDoc);
    }

    public void deletar(int id) {
        tagDocDao.deletar(id);
    }





}
