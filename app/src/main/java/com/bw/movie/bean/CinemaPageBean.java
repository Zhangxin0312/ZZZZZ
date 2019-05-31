package com.bw.movie.bean;

import java.util.List;

public class CinemaPageBean {

    /**
     * result : [{"address":"海淀区复兴路69号五棵松卓展时代百货五层东侧","commentTotal":0,"distance":0,"followCinema":0,"id":13,"logo":"http://mobile.bwstudent.com/images/movie/logo/bjalclgj.jpg","name":"北京耀莱成龙国际影城（五棵松店）"},{"address":"朝阳区广顺北大街16号望京华彩商业中心B1","commentTotal":0,"distance":0,"followCinema":0,"id":10,"logo":"http://mobile.bwstudent.com/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院"},{"address":"海淀区远大路1号金源时代购物中心5层东首","commentTotal":0,"distance":0,"followCinema":0,"id":11,"logo":"http://mobile.bwstudent.com/images/movie/logo/xmgj.jpg","name":"星美国际影城"},{"address":"海淀区中关村广场购物中心津乐汇三层（鼎好一期西侧）","commentTotal":0,"distance":0,"followCinema":0,"id":12,"logo":"http://mobile.bwstudent.com/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店"},{"address":"北京市育知东路30号华联商厦4层","commentTotal":0,"distance":0,"followCinema":0,"id":20,"logo":"http://mobile.bwstudent.com/images/movie/logo/wmyc.jpg","name":"北京沃美影城（回龙观店）"},{"address":"北京海淀区海淀区清河中街68号五彩城购物中心东区7层","commentTotal":0,"distance":0,"followCinema":0,"id":22,"logo":"http://mobile.bwstudent.com/images/movie/logo/CGVyc.jpg","name":"CGV影城（清河店）"},{"address":"北京市朝阳区十八里店西直河商业中心东融国际影城","commentTotal":0,"distance":0,"followCinema":0,"id":21,"logo":"http://mobile.bwstudent.com/images/movie/logo/drgjyc.jpg","name":"东融国际影城西直河店"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 海淀区复兴路69号五棵松卓展时代百货五层东侧
         * commentTotal : 0
         * distance : 0
         * followCinema : 0
         * id : 13
         * logo : http://mobile.bwstudent.com/images/movie/logo/bjalclgj.jpg
         * name : 北京耀莱成龙国际影城（五棵松店）
         */

        private String address;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String logo;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(int followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
