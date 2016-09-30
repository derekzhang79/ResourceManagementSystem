
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.TargetTypeVO;

public interface TargetTypeDao {

	List getAllTargetType();	
	
	TargetTypeVO getTargetType(Integer id);
	
    void insertTargetType(TargetTypeVO targetType);
    
    void updateTargetType(TargetTypeVO targetType);
    
    void deleteTargetType(TargetTypeVO targetType);
    
    List targetTypeSearchResult(TargetTypeVO targetType);
	
	List checkTargetTypeInTarget(TargetTypeVO targetType);

}
