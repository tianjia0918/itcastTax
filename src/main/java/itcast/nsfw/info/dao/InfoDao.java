package itcast.nsfw.info.dao;


import java.util.List;

import itcast.core.dao.BaseDao;
import itcast.core.util.QueryHelper;
import itcast.nsfw.info.entity.Info;

public interface InfoDao extends BaseDao<Info> {

	List<Info> findObjects(QueryHelper queryHelper);

	

}
