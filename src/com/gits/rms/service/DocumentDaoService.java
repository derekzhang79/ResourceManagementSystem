package com.gits.rms.service;

import com.gits.rms.persistence.DocumentDao;
import com.gits.rms.vo.DocumentVO;

public class DocumentDaoService implements DocumentService {

    private DocumentDao dao;

	@Override
	public void insertDocument(DocumentVO document) {
		// TODO Auto-generated method stub
		dao.insertDocument(document);
	}

	@Override
	public void updateDocument(DocumentVO document) {
		// TODO Auto-generated method stub
		dao.updateDocument(document);
	}

}
