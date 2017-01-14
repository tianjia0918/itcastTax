package itcast.nsfw.complain.dao;

import itcast.core.dao.BaseDao;
import itcast.nsfw.complain.entity.Complain;

import java.util.List;

public interface compDao extends BaseDao<Complain> {

	List<Object[]> getAnnualStatisticDataByYear(int year);

}
