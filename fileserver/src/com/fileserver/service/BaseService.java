package com.fileserver.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fileserver.common.contants.DefaultCode;
import com.fileserver.common.enums.ResponseCodes;
import com.fileserver.common.exception.SystemException;
import com.fileserver.dao.impl.BaseDao;
import com.fileserver.entity.IEntity;

@Service
@SuppressWarnings("rawtypes")
public class BaseService {

	@Autowired
	private BaseDao baseDao;

	public List<?> findByExample(Object obj) {
		List list = null;
		try {
			list = baseDao.findByExample(obj);
			if (list == null) {
				list = new ArrayList();
			}
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
		return list;
	}

	public Object findObjectByCondition(String condition) {

		Object object = null;
		try {
			object = baseDao.findObjectByCondition(condition);
			if (object == null) {
				object = new Object();
			}
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
		return object;
	}

	public List<?> findByProperty(String tableModel, String propertyName,
			Object value, String sort) {
		List list = null;
		try {
			list = baseDao.findByProperty(tableModel, propertyName, value, sort);
			if (list == null) {
				list = new ArrayList();
			}
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
		
		return list;
	}

	public List find(String hql) {
		List list = null;
		try {
			list = baseDao.find(hql);
			if (list == null) {
				list = new ArrayList();
			}
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
		return list;
	}
	
	// 通过id查找
	public Object findById(Class<?> className, Integer id) {
		Object object = null;
		try {
			List list =  baseDao.find("from " + className.getName() + " where id = " + id + " and isDelete = " + DefaultCode.Code_Zero);
			if (list != null && list.size() > 0) {
				object = list.get(0);
			}
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
		return object;
	}
	
	//分页查询
	public List findListByPage(String hql, int page, int size) {
		List list = null;
		try {
			list = baseDao.findListByPage(hql, page, size);
			if (list == null) {
				list = new ArrayList();
			}
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
		return list;
	}	

	/**
	 * 执行sql语句
	 */
	public List executeSQL(final Class cls , final String sql, Integer page, final Integer size) {
		
		List list = null;
		try {
			list = baseDao.executeSQL(cls, sql, page, size);
			if (list == null) {
				list = new ArrayList();
			}
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
		return list;
	}

	/**
	 * 执行sql语句 更新数据
	 */
	@Transactional
	public void executeUpdateSQL(final String sql) {
		
		try {
			 baseDao.executeUpdateSQL(sql);
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailQueryDB, e);
		}
	}
	
	@Transactional
	public void save(IEntity object) {
		try {
			object.setCreateTime(new Date());
			object.setIsDelete(Boolean.FALSE);
			baseDao.save(object);
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailSaveDB, e);
		}
	}

	@Transactional
	public void delete(IEntity object) {
		try {
			object.setDeleteTime(new Date());
			object.setIsDelete(true);
			baseDao.delete(object);
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailDelDB, e);
		}
	}

	@Transactional
	public void update(IEntity object) {
		try {
			object.setModifyTime(new Date());
			baseDao.saveOrUpdate(object);
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailUpdDB, e);
		}
	}
	
	@Transactional
	public void deleteAll(String[] ids, String str) {
		try {
			baseDao.delAll(ids, str);
		} catch (Exception e) {
			throw new SystemException(ResponseCodes.FailDelAllDB, e);
		}
	}
}
