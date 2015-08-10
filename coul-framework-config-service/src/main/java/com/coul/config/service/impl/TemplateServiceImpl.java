package com.coul.config.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coul.common.exception.DaoAccessException;
import com.coul.config.entity.Template;
import com.coul.config.repository.TemplateRepository;
import com.coul.config.service.ITemplateService;
import com.coul.core.control.action.PagingGridData;
import com.coul.core.control.action.PagingRequestData;
import com.coul.core.domain.base.entity.EntityDao;
import com.coul.core.domain.base.service.impl.IBaseServieImpl;

@Service("templateServiceImpl")
public class TemplateServiceImpl
  extends IBaseServieImpl<Template> implements ITemplateService {

    /**
    * 日志信息	
    */
    @SuppressWarnings("unused")
	private final static Logger log= Logger.getLogger(TemplateServiceImpl.class);
    /**
     * 持久化业务对象
     */
    @Autowired
    private TemplateRepository  templateRepository; 
	
	@Override
	public PagingGridData<Template> queryTemplateDate(
			PagingRequestData page) throws DaoAccessException {
		PagingGridData<Template>  data = 
				 new  PagingGridData<Template>(page , templateRepository.queryTemplateDate(page.getSearchCondtion(), page.getPaging()));	
		return data;
	}


	
	@Override
	public PagingGridData<Template> queryTemplate(PagingRequestData page) throws DaoAccessException {
		PagingGridData<Template>  data = 
				 new  PagingGridData<Template>(page , templateRepository.queryTemplate(page.getRequestData(), page.getPaging()));	
		return data;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public EntityDao getEntityDao() {
		// TODO Auto-generated method stub
		return templateRepository;
	}




}
