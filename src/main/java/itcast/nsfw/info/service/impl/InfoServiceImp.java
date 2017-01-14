package itcast.nsfw.info.service.impl;

import javax.annotation.Resource;

import itcast.core.service.impl.BaseServiceImp;
import itcast.nsfw.info.service.InfoService;
import org.springframework.stereotype.Service;
import itcast.nsfw.info.dao.InfoDao;
import itcast.nsfw.info.entity.Info;

@Service("infoService")
public class InfoServiceImp extends BaseServiceImp<Info> implements InfoService {
	
		
	private InfoDao infoDao;
	@Resource
	public void setInfoDao(InfoDao infoDao) {
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
	public InfoDao getInfoDao() {
		return infoDao;
	}
	
	/*public List<Info> findObjects(String sql, List<Object> parameters){
		return infoDao.findObjects(sql, parameters);		
	}*/
	
	  
}
