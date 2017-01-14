package itcast.nsfw.complain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import itcast.core.service.impl.BaseServiceImp;
import itcast.nsfw.complain.dao.compDao;
import itcast.nsfw.complain.entity.Complain;
import itcast.nsfw.complain.service.compService;


@Service("compService")
public class compServiceImpl extends BaseServiceImp<Complain> implements
		compService {

	private compDao compDao;
	@Resource
	public void setCompDao(compDao compDao) {
		super.setBaseDao(compDao);
		this.compDao = compDao;
	}
	
		
	public compDao getCompDao() {
		return compDao;
	}


	@Override
	public List<Object[]> getAnnualStatisticDataByYear(int year) {
		
		return compDao.getAnnualStatisticDataByYear(year);
	} 
	
	
	

}
