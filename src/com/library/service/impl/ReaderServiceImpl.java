package com.library.service.impl;

import com.library.dao.ReaderDao;
import com.library.dao.impl.ReaderImpl;
import com.library.entity.Reader;
import com.library.service.ReaderService;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    ReaderDao readerDao = new ReaderImpl();

    @Override
    public int insert(Reader reader) {
        return readerDao.insert(reader);
    }

    @Override
    public int delete(String ISBN) {
        return readerDao.delete(ISBN);
    }

    @Override
    public int update(Reader reader) {
        return readerDao.update(reader);
    }

    @Override
    public List<Reader> queryByReaderISBN(String ISBN) {
        return readerDao.queryByReaderISBN(ISBN);
    }

    @Override
    public List<Reader> queryAll() {
        return readerDao.queryAll();
    }

    @Override
    public List<Reader> queryByReaderName(String name) {
        return readerDao.queryByReaderName(name);
    }
}
