
package com.gits.rms.persistence;

import com.gits.rms.vo.SupportVO;

public interface SupportDao {
    void insertSupport(SupportVO supportObj);

    SupportVO getSupport(Integer id);
}
