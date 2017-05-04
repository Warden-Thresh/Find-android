package com.warden.find.gson;

import java.util.List;

/**
 * Created by Warden on 2017/5/3.
 */

public class KmustJob {
    /**
     * page : {"offset":null,"count":2390,"result":[],"start":0,"order":"ASC","orderBy":null,"pageNo":1,"pageSize":20,"asc":true,"inverseOrder":"DESC","pageSizeEnabled":true,"startEnabled":true,"orderEnabled":false,"previousEnabled":false,"nextEnabled":false,"pageCountEnabled":false,"autoCount":false,"pageCount":-1,"res":[]}
     * count : 2390
     * currentPage : 1
     * pagelast : 120
     * pageSize : 20
     * resultlist : [{"ID":"10706","ZPHMC":"融创中国2017遇见招募宣讲会","ZPHSJ":"2017-05-12 19:00","SHZT":"2","ZPHDD":"呈贡校区信自100报告厅","ZPHTYPE":"1"},{"ID":"10710","ZPHMC":"vivo（云南高沃科技有限公司）","ZPHSJ":"2017-05-12 10:00","SHZT":"2","ZPHDD":"新迎校区润泽楼101","ZPHTYPE":"1"},{"ID":"10676","ZPHMC":"山东南山国际飞行有限公司专场招聘会","ZPHSJ":"2017-05-11 10:00","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋101","ZPHTYPE":"1"},{"ID":"10708","ZPHMC":"青岛东软载波科技股份有限公司校园招聘会","ZPHSJ":"2017-05-05 14:00","SHZT":"2","ZPHDD":"呈贡校区西北区B栋101","ZPHTYPE":"1"},{"ID":"10592","ZPHMC":"云南号外商标代理有限公司专场招聘会","ZPHSJ":"2017-05-05 13:30","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋102","ZPHTYPE":"1"},{"ID":"10711","ZPHMC":"宁波市交通规划设计研究院有限公司云南分公司招聘启事","ZPHSJ":"2017-05-05 09:30","SHZT":"2","ZPHDD":"呈贡校区西北区B栋105","ZPHTYPE":"1"},{"ID":"10714","ZPHMC":"环境科学与工程学院第三届环境行业专场招聘会","ZPHSJ":"2017-05-05 09:30","SHZT":"2","ZPHDD":"呈贡校区恬园环境科学与工程学院A栋一楼大厅","ZPHTYPE":"1"},{"ID":"10709","ZPHMC":"昆明鼎耀佳装饰设计工程有限公司招聘会","ZPHSJ":"2017-05-04 14:00","SHZT":"2","ZPHDD":"呈贡校区西北区B栋101","ZPHTYPE":"1"},{"ID":"10700","ZPHMC":"正大集团（中国区）农牧食品企业2017招聘会","ZPHSJ":"2017-05-03 14:00","SHZT":"2","ZPHDD":"呈贡校区恬园生科楼A200","ZPHTYPE":"1"},{"ID":"10670","ZPHMC":"昆明语言桥翻译有限公司专场招聘会","ZPHSJ":"2017-04-28 14:00","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋101","ZPHTYPE":"1"},{"ID":"10657","ZPHMC":"昆明市西山参差英语培训学校专场招聘会","ZPHSJ":"2017-04-28 13:30","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋104","ZPHTYPE":"1"},{"ID":"10589","ZPHMC":"链家集团北京总部专场招聘会","ZPHSJ":"2017-04-27 19:00","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋102","ZPHTYPE":"1"},{"ID":"10643","ZPHMC":"云南思辉电气设备有限公司专场招聘会","ZPHSJ":"2017-04-27 13:30","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋104","ZPHTYPE":"1"},{"ID":"10642","ZPHMC":"云南省小龙潭矿务局专场招聘会","ZPHSJ":"2017-04-27 09:30","SHZT":"2","ZPHDD":"呈贡校区计算机重点实验室207","ZPHTYPE":"1"},{"ID":"10658","ZPHMC":"管理与经济学院2017届毕业生双向选择洽谈会（春季）","ZPHSJ":"2017-04-27 09:30","SHZT":"2","ZPHDD":"莲华校区管理与经济学院1楼大厅","ZPHTYPE":"1"},{"ID":"10645","ZPHMC":"内蒙古西贝餐饮连锁有限责任公司专场招聘会","ZPHSJ":"2017-04-26 14:30","SHZT":"2","ZPHDD":"新迎校区明虹楼311","ZPHTYPE":"1"},{"ID":"10638","ZPHMC":"爱唯尔（上海）企业发展有限公司专场招聘会","ZPHSJ":"2017-04-25 14:00","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋102","ZPHTYPE":"1"},{"ID":"10562","ZPHMC":"云南红岭云科技股份有限公司专场招聘会","ZPHSJ":"2017-04-25 13:30","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋105","ZPHTYPE":"1"},{"ID":"10656","ZPHMC":"云南月盛建设工程项目管理有限公司专场招聘会","ZPHSJ":"2017-04-25 13:30","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋104","ZPHTYPE":"1"},{"ID":"10591","ZPHMC":"强韵数据科技有限公司专场招聘会","ZPHSJ":"2017-04-25 13:30","SHZT":"2","ZPHDD":"呈贡校区西北区教学楼B栋101","ZPHTYPE":"1"}]
     */

    private PageBean page;
    private String count;
    private String currentPage;
    private String pagelast;
    private String pageSize;
    private List<ResultlistBean> resultlist;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPagelast() {
        return pagelast;
    }

    public void setPagelast(String pagelast) {
        this.pagelast = pagelast;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public List<ResultlistBean> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<ResultlistBean> resultlist) {
        this.resultlist = resultlist;
    }

    public static class PageBean {
        /**
         * offset : null
         * count : 2390
         * result : []
         * start : 0
         * order : ASC
         * orderBy : null
         * pageNo : 1
         * pageSize : 20
         * asc : true
         * inverseOrder : DESC
         * pageSizeEnabled : true
         * startEnabled : true
         * orderEnabled : false
         * previousEnabled : false
         * nextEnabled : false
         * pageCountEnabled : false
         * autoCount : false
         * pageCount : -1
         * res : []
         */

        private Object offset;
        private int count;
        private int start;
        private String order;
        private Object orderBy;
        private int pageNo;
        private int pageSize;
        private boolean asc;
        private String inverseOrder;
        private boolean pageSizeEnabled;
        private boolean startEnabled;
        private boolean orderEnabled;
        private boolean previousEnabled;
        private boolean nextEnabled;
        private boolean pageCountEnabled;
        private boolean autoCount;
        private int pageCount;
        private List<?> result;
        private List<?> res;

        public Object getOffset() {
            return offset;
        }

        public void setOffset(Object offset) {
            this.offset = offset;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public boolean isAsc() {
            return asc;
        }

        public void setAsc(boolean asc) {
            this.asc = asc;
        }

        public String getInverseOrder() {
            return inverseOrder;
        }

        public void setInverseOrder(String inverseOrder) {
            this.inverseOrder = inverseOrder;
        }

        public boolean isPageSizeEnabled() {
            return pageSizeEnabled;
        }

        public void setPageSizeEnabled(boolean pageSizeEnabled) {
            this.pageSizeEnabled = pageSizeEnabled;
        }

        public boolean isStartEnabled() {
            return startEnabled;
        }

        public void setStartEnabled(boolean startEnabled) {
            this.startEnabled = startEnabled;
        }

        public boolean isOrderEnabled() {
            return orderEnabled;
        }

        public void setOrderEnabled(boolean orderEnabled) {
            this.orderEnabled = orderEnabled;
        }

        public boolean isPreviousEnabled() {
            return previousEnabled;
        }

        public void setPreviousEnabled(boolean previousEnabled) {
            this.previousEnabled = previousEnabled;
        }

        public boolean isNextEnabled() {
            return nextEnabled;
        }

        public void setNextEnabled(boolean nextEnabled) {
            this.nextEnabled = nextEnabled;
        }

        public boolean isPageCountEnabled() {
            return pageCountEnabled;
        }

        public void setPageCountEnabled(boolean pageCountEnabled) {
            this.pageCountEnabled = pageCountEnabled;
        }

        public boolean isAutoCount() {
            return autoCount;
        }

        public void setAutoCount(boolean autoCount) {
            this.autoCount = autoCount;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<?> getResult() {
            return result;
        }

        public void setResult(List<?> result) {
            this.result = result;
        }

        public List<?> getRes() {
            return res;
        }

        public void setRes(List<?> res) {
            this.res = res;
        }
    }

    public static class ResultlistBean {
        /**
         * ID : 10706
         * ZPHMC : 融创中国2017遇见招募宣讲会
         * ZPHSJ : 2017-05-12 19:00
         * SHZT : 2
         * ZPHDD : 呈贡校区信自100报告厅
         * ZPHTYPE : 1
         */

        private String ID;
        private String ZPHMC;
        private String ZPHSJ;
        private String SHZT;
        private String ZPHDD;
        private String ZPHTYPE;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getZPHMC() {
            return ZPHMC;
        }

        public void setZPHMC(String ZPHMC) {
            this.ZPHMC = ZPHMC;
        }

        public String getZPHSJ() {
            return ZPHSJ;
        }

        public void setZPHSJ(String ZPHSJ) {
            this.ZPHSJ = ZPHSJ;
        }

        public String getSHZT() {
            return SHZT;
        }

        public void setSHZT(String SHZT) {
            this.SHZT = SHZT;
        }

        public String getZPHDD() {
            return ZPHDD;
        }

        public void setZPHDD(String ZPHDD) {
            this.ZPHDD = ZPHDD;
        }

        public String getZPHTYPE() {
            return ZPHTYPE;
        }

        public void setZPHTYPE(String ZPHTYPE) {
            this.ZPHTYPE = ZPHTYPE;
        }
    }
}
