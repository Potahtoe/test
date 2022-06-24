package com.spring.test.page;

public class Paging {
	private int pageSize=10; //화면에 보여질 게시글의 갯수 지정
	private int count=0; //전체글의 갯수를 저장하는 변수
	private int number=0; //페이지번호
	private String pageNum;//페이지번호??
	
	private int startRow; //페이지 당 첫번째 게시물
	private int endRow;//페이지 당 마지막 게시물
	
	private int currentPage; //현재 페이지 번호
	private int pageCount; //페이지 갯수
	private int startPage; //시작페이지
	private int pageBlock;//??
	private int endPage; //마지막 페이지
	
	private int prev; //이전
	private int next; //다음
	
	public Paging() {}
	
	public Paging(String pageNum) {
		//게시글 없을 때 null처리
		if(pageNum==null) {
			pageNum="1";
		}
		this.pageNum=pageNum;
		currentPage = Integer.parseInt(pageNum);
	}
	
	public void setTotalCount(int count) {
		this.count=count;
		
		startRow = (currentPage -1) * pageSize +1;
		endRow = currentPage * pageSize;
		this.number=count-(currentPage -1)* pageSize;
		
		//페이지 계산
		pageCalculator();
		
	}
	
	private void pageCalculator() {
		if(count>0) {
			pageCount = count/pageSize + (count % pageSize == 0?0:1);
			startPage=1;
			if(currentPage %10 !=0) {
				startPage = (int)(currentPage/10)*10+1; //(int)(currentPage)+1 ??
			}else {
				startPage =((int)(currentPage/10)-1)*10 +1;
			}
			
			pageBlock=10;
			endPage = startPage + pageBlock -1;
			
			if(endPage > pageCount) endPage = pageCount;
			if(startPage > pageSize) prev = startPage-10; //이전
			if(endPage < pageCount) next = startPage+10; //다음
		}
	}
	
	//getter, setter
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Paging [pageSize=" + pageSize + ", count=" + count + ", number=" + number + ", pageNum=" + pageNum
				+ ", startRow=" + startRow + ", endRow=" + endRow + ", currentPage=" + currentPage + ", pageCount="
				+ pageCount + ", startPage=" + startPage + ", pageBlock=" + pageBlock + ", endPage=" + endPage
				+ ", prev=" + prev + ", next=" + next + "]";
	}
}
