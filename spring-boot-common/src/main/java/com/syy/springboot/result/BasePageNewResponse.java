/**  
 * 
 * @Title:  BasePageNewResponse.java   
 * @Package: commercial.base.common.models.base     
 * @author: Lenovo     
 * @date:   2019年7月19日 下午1:39:06   
 * @version V1.0 
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved. 
 */
package com.syy.springboot.result;



/**
 * BasePageNewResponse
 * 
 * @author: Lenovo
 * @version: 2019年7月19日
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.  
 */
public class BasePageNewResponse extends BaseResponse<Object> {
     private Integer pageIndex;
     private Integer pageSize;
     private Integer pageNum;
	/**  
	 * getPageIndex
	 * @return: Integer 
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}
	/**  
	 * setPageIndex
	 * @return: Integer
	 */
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**  
	 * getPageSize
	 * @return: Integer 
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**  
	 * setPageSize
	 * @return: Integer
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**  
	 * getPageNum
	 * @return: Integer 
	 */
	public Integer getPageNum() {
		return pageNum;
	}
	/**  
	 * setPageNum
	 * @return: Integer
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
     
}
