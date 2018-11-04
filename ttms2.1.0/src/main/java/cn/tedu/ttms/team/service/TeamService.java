package cn.tedu.ttms.team.service;
import java.util.List;
import java.util.Map;

import cn.tedu.ttms.team.entity.Teams;
public interface TeamService {
	
	public Map<String,Object> findPageObjects(
			String projectName,
			Integer valid,
			Integer pageCurrent
			);
	public void saveObject(Teams team);
	
	/***/
	public List<Map<String,Object>> findProjectIdAndName();

}

