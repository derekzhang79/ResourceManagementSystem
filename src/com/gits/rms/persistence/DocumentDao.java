package com.gits.rms.persistence;

import com.gits.rms.vo.DocumentVO;

public interface DocumentDao {

	void insertDocument(DocumentVO document);

    void updateDocument(DocumentVO document);
    
}
