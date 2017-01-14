package itcast.nsfw.complain.service;

import itcast.core.service.BaseService;
import itcast.nsfw.complain.entity.Complain;

import java.util.List;

public interface compService extends BaseService<Complain> {

	List<Object[]> getAnnualStatisticDataByYear(int year);

	

}
