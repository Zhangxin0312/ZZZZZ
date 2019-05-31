package com.bw.movie.bean;

public class XiangBean {

    /**
     * result : {"director":"庄文强","duration":"130分钟","fare":0.15,"id":20,"imageUrl":"http://mobile.bwstudent.com/images/movie/stills/ws/ws1.jpg","name":"无双","placeOrigin":"中国大陆,中国香港","starring":"周润发,郭富城,张静初,冯文娟,廖启智","summary":"以代号\u201c画家\u201d（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而\u201c画家\u201d和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而\u201c画家\u201d的真实身份却让众人意想不到\u2026\u2026"}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * director : 庄文强
         * duration : 130分钟
         * fare : 0.15
         * id : 20
         * imageUrl : http://mobile.bwstudent.com/images/movie/stills/ws/ws1.jpg
         * name : 无双
         * placeOrigin : 中国大陆,中国香港
         * starring : 周润发,郭富城,张静初,冯文娟,廖启智
         * summary : 以代号“画家”（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而“画家”和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而“画家”的真实身份却让众人意想不到……
         */

        private String director;
        private String duration;
        private double fare;
        private int id;
        private String imageUrl;
        private String name;
        private String placeOrigin;
        private String starring;
        private String summary;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public double getFare() {
            return fare;
        }

        public void setFare(double fare) {
            this.fare = fare;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceOrigin() {
            return placeOrigin;
        }

        public void setPlaceOrigin(String placeOrigin) {
            this.placeOrigin = placeOrigin;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
