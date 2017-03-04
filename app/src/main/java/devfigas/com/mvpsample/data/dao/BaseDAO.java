package devfigas.com.mvpsample.data.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import devfigas.com.mvpsample.data.DatabaseHelper;


public abstract class BaseDAO<E> extends DatabaseHelper<E> {

 protected Dao<E, Integer> dao;
 private Class<E> type;
 public static final int SUCCESS = 1;
 public static final int FAILURE = 0;

 public BaseDAO(Context context) {
  super(context);
  this.type = getBaseClass();
  setDao();
 }

protected  abstract Class getBaseClass();

 protected void setDao() {
  try{
      dao = DaoManager.createDao(getConnectionSource(), type);
  }catch(Exception e){
      e.printStackTrace();
  }
 }

 public List<E> getAll() {
  try{
      List<E> result = dao.queryForAll();
      return result;
  }catch(Exception e){
      e.printStackTrace();
   return null;
  }
 }

 public E getById(int id) {
  try{
   E obj = dao.queryForId(id);
   return obj;
  }catch(Exception e){
   e.printStackTrace();
   return null;
  }
 }
 public E getByWhereClauses(HashMap<String,Object> whereClauses) {
  try{
    QueryBuilder<E,Integer> queryBuilder = dao.queryBuilder();
    Where<E, Integer> where = queryBuilder.where();
    boolean firstClause = true;
    for(String field : whereClauses.keySet()){
         if(firstClause){
            where.eq(field, whereClauses.get(field));
         }else {
            where.and().eq(field, whereClauses.get(field));
         }
         firstClause = false;
   }
   queryBuilder.setWhere(where);
   List<E> list = queryBuilder.query();

   if(list.size()>0) return list.get(0);
   else return null;

  }catch(Exception e){
   e.printStackTrace();
   return null;
  }
 }
 public int insert(E obj) {
    try{
        dao.create(obj);
        return SUCCESS;
    } catch (SQLException e) {
        e.printStackTrace();
        return FAILURE;
    }


 }

 public void delete(E obj) {
  try{
   dao.delete(obj);
  }catch(Exception e){
   e.printStackTrace();
  }
 }

 public void update(E obj) {
  try{
   dao.update(obj); 
  }catch(Exception e){
   e.printStackTrace();
  }
 }
 public void clear(){
     try {
         TableUtils.clearTable(getConnectionSource(), getBaseClass());
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }



}
