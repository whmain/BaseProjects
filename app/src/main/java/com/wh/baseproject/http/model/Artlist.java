package com.wh.baseproject.http.model;

import java.util.List;

/**
 * @author PC-WangHao on 2019/12/30 13:29.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class Artlist {
    private List<Content> datas;


    public class Content {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
