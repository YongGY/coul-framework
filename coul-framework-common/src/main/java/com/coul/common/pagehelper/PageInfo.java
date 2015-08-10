package com.coul.common.pagehelper;


/**
 * =============================================================================<br>
 * 
 * 文件包： com.ffcs.crm.common.vo 所含类： PageInfo 编写人员：陈军 创建日期：2007-03-27 功能说明:
 * 保存分页信息返回值 更新记录： 日期 作者 内容<br>
 * =============================================================================<br>
 * 2007-03-29  创建新文件，并实现基本功能
 * =============================================================================<br>
 */
@SuppressWarnings("unchecked")
public class PageInfo {
    
    private int            _perPageCount;  // 每页记录数
                                           
    private int            _totalPageCount; // 总页数
                                           
    private int            _currentPage;   // 当前页
                                           
    private int            _totalCount;    // 总记录数
                                           
    private java.util.List _dataList;      // 查询出的记录集合
    
    private String _resultStr;//错误结果描述(json)
                                           
    /**
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public PageInfo() {
    }
    
    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public int get_currentPage() {
        return this._currentPage;
    }
    
    /**
     * @param currentPage
     *            int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void set_currentPage(final int _currentPage) {
        this._currentPage = _currentPage;
    }
    
    /**
     * @return List
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public java.util.List get_dataList() {
        return this._dataList;
    }
    
    /**
     * @param dataList
     *            List
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void set_dataList(final java.util.List _dataList) {
        this._dataList = _dataList;
    }
    
    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public int get_perPageCount() {
        return this._perPageCount;
    }
    
    /**
     * @param perPageCount
     *            int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void set_perPageCount(final int _perPageCount) {
        this._perPageCount = _perPageCount;
    }
    
    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public int get_totalCount() {
        return this._totalCount;
    }
    
    /**
     * @param totalCount
     *            int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void set_totalCount(final int totalCount) {
        this._totalCount = totalCount;
    }
    
    /**
     * @return int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public int get_totalPageCount() {
        return this._totalPageCount;
    }
    
    /**
     * @param totalPageCount
     *            int
     * @author: wuq
     * @修改记录： ==============================================================<br>
     *        日期:2007-9-21 wuq 创建方法，并实现其功能
     *        ==============================================================<br>
     */
    public void set_totalPageCount(final int totalPageCount) {
        this._totalPageCount = totalPageCount;
    }

	public String get_resultStr() {
		return _resultStr;
	}

	public void set_resultStr(String _resultStr) {
		this._resultStr = _resultStr;
	}

	
    
}
