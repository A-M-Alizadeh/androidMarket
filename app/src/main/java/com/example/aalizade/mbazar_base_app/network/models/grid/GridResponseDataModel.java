package com.example.aalizade.mbazar_base_app.network.models.grid;

import java.util.List;

/**
 * Created by sbayatani on 4/8/2018.
 */

public class GridResponseDataModel {
        private Integer page;
        private Integer records;
        private List<RowGridModel> rows;
        private Integer total;
        private Object userdata;

        public GridResponseDataModel(Integer page, Integer records, List<RowGridModel> rows, Integer total, Object userdata) {
            this.page = page;
            this.records = records;
            this.rows = rows;
            this.total = total;
            this.userdata = userdata;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getRecords() {
            return records;
        }

        public void setRecords(Integer records) {
            this.records = records;
        }

        public List<RowGridModel> getRows() {
            return rows;
        }

        public void setRows(List<RowGridModel> rows) {
            this.rows = rows;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Object getUserdata() {
            return userdata;
        }

        public void setUserdata(Object userdata) {
            this.userdata = userdata;
        }

        @Override
        public String toString() {
            return "GridResponseDataModel{" +
                    "page=" + page +
                    ", records=" + records +
                    ", rows=" + rows +
                    ", total=" + total +
                    ", userdata=" + userdata +
                    '}';
        }


}
