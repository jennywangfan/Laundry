/**
* <p>Title: LaundryItemDao.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 29, 2015
* @version 1.0
*/
package com.triplexilaundry.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.triplexilaundry.domain.LaundryItem;
import com.triplexilaundry.exception.ClientServerCategoryIdNotMatchException;

/**
 * <p>Title: LaundryItemDao</p>
 * <p>Description: </p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 29, 2015
 */
@Repository
public class LaundryItemDao {
	
	public static HashMap<Integer,LaundryItem> laundryItemMap = new HashMap<>();
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public LaundryItem findLaundryById(int id) throws ClientServerCategoryIdNotMatchException{
		LaundryItem li = laundryItemMap.get(id);
		if(li == null){
			String sql = "from LaundryItem";
			Query query = entityManager.createQuery(sql);
			@SuppressWarnings("unchecked")
			List<LaundryItem> result = query.getResultList();
			for(LaundryItem lItem : result){
				laundryItemMap.put(lItem.getItemId(), lItem);
			}
			li = laundryItemMap.get(id);
			if(li != null)
				return li;
			else
				throw new ClientServerCategoryIdNotMatchException("item id in client not match database");
		}
		else
			return li;
		
	}

}
