package com.revisaoWeb.service;

import com.revisaoWeb.dao.TagDao;
import com.revisaoWeb.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagDao tagDao;

    public TagService(TagDao tagDao){
        this.tagDao = tagDao;
    }

    public void criar(Tag tag) {
        tagDao.criar(tag);
    }

    public List<Tag> listar() {
        return tagDao.listar();
    }

    public Tag listarId(int id) {
        return tagDao.listarId(id);
    }

    public void atualizar(int id, Tag tag) {
        tagDao.atualizar(id, tag);
    }

    public void deletar(int id) {
        tagDao.deletar(id);
    }




}
