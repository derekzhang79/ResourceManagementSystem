
package com.gits.rms.service;

import com.gits.rms.vo.SupportVO;

public interface SupportService {
    SupportVO getSupport(Integer id);

    void insertSupport(SupportVO supportObj);
}
