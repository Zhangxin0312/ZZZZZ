package com.bw.movie.bean;

import java.util.List;

public class RecodeBean {

    /**
     * result : [{"amount":1,"beginTime":"20:00:00","cinemaName":"CGV影城（清河店）","createTime":1557997555000,"endTime":"21:48:00","id":12087,"movieName":"江湖儿女","orderId":"20190516170555240","price":0.28,"screeningHall":"7号厅","status":1,"userId":1300},{"amount":1,"beginTime":"20:00:00","cinemaName":"CGV影城（清河店）","createTime":1557997467000,"endTime":"21:48:00","id":12085,"movieName":"江湖儿女","orderId":"20190516170427039","price":0.28,"screeningHall":"7号厅","status":1,"userId":1300},{"amount":2,"beginTime":"20:00:00","cinemaName":"CGV影城（清河店）","createTime":1557997350000,"endTime":"21:48:00","id":12083,"movieName":"江湖儿女","orderId":"20190516170230880","price":0.28,"screeningHall":"7号厅","status":1,"userId":1300},{"amount":2,"beginTime":"20:00:00","cinemaName":"CGV影城（清河店）","createTime":1557995656000,"endTime":"21:48:00","id":12077,"movieName":"江湖儿女","orderId":"20190516163416914","price":0.28,"screeningHall":"7号厅","status":1,"userId":1300}]
     * message : 请求成功
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
         * amount : 1
         * beginTime : 20:00:00
         * cinemaName : CGV影城（清河店）
         * createTime : 1557997555000
         * endTime : 21:48:00
         * id : 12087
         * movieName : 江湖儿女
         * orderId : 20190516170555240
         * price : 0.28
         * screeningHall : 7号厅
         * status : 1
         * userId : 1300
         */

        private int amount;
        private String beginTime;
        private String cinemaName;
        private long createTime;
        private String endTime;
        private int id;
        private String movieName;
        private String orderId;
        private double price;
        private String screeningHall;
        private int status;
        private int userId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getCinemaName() {
            return cinemaName;
        }

        public void setCinemaName(String cinemaName) {
            this.cinemaName = cinemaName;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getScreeningHall() {
            return screeningHall;
        }

        public void setScreeningHall(String screeningHall) {
            this.screeningHall = screeningHall;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
