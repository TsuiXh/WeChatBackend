package org.tsui.entity;

import java.util.List;

/**
 *	��ҳ��ѯ���ߣ�������Ϣ��
 * @author TsuiXh
 */
public class PageAttr {
	
	public static final String SORT_ASC = " asc ";		//����
	public static final String SORT_DESC = " desc ";		//����
	public static final String DEFAULT_COLUMNS = " * ";	//������
	/**
	 * ��ҳǰ��Ҫ���õ����ݣ����ֿ�ѡ��
	 */
	
	private String tableName;//�����ƣ����룩
	private String columns;//��ѯ��	�����룩
	private String condition;//��ѯ����
	private String sortColumn;//������
	private String sortType = SORT_ASC;//����ʽ������|����
	private int currentPage;//��ǰҳ	�����룩
	private int pageSize;//ҳ���С	�����룩
	
	private List data;//��ȡ������
	private int totalLine;//�ܼ�¼��
	private int totalPage;//��ҳ��
	
	
	public PageAttr(){
	}
	
	/**
	 * @param tableName	����
	 * @param columns			����
	 * @param condition		����
	 * @param sortColumn	������
	 * @param sortType		����ʽ
	 * @param currentPage	��ǰҳ
	 * @param pageSize		ҳ���С
	 */
	public PageAttr(String tableName, String columns, String condition, String sortColumn, String sortType,
			int currentPage, int pageSize) {
		super();
		this.tableName = tableName;
		this.columns = columns;
		this.condition = condition;
		this.sortColumn = sortColumn;
		this.sortType = sortType;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public int getTotalLine() {
		return totalLine;
	}
	public void setTotalLine(int totalLine) {
		this.totalLine = totalLine;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
