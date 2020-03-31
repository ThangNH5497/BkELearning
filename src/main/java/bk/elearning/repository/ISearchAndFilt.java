package bk.elearning.repository;

import java.util.List;

public interface ISearchAndFilt<T> {

	//tra ve tong so luong tim kiem ràng buộc bới 1 trường giá trị
		public Long getCount(String fieldContraint,Object fieldContraintValue,String fieldSearch,String key);
		
		/**
		 * 
		 * @param fieldContraint
		 * @param fieldContraintValue
		 * @param fieldSearch
		 * @param key
		 * @param start
		 * @param size
		 * @return kết quả tìm kiếm ràng buộc bở fieldContraint và tìm kiếm theo fieldSearch
		 */
		public List<T> search(String fieldContraint,Object fieldContraintValue,String fieldSearch,String key,int start,int size);
		
}
