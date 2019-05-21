package com.bw.myproduct.bean;

import java.util.List;

public class PingBean {

    /**
     * message : 查询成功
     * result : [{"commodityId":32,"content":"很好","createTime":1551997249000,"headPic":"http://172.17.8.100/images/small/default/user.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-07/2349620190307162049.png","nickName":"rainbow","userId":32},{"commodityId":32,"content":"121","createTime":1551979210000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-07/20190307112048.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-07/5316220190307112010.jpg","nickName":"213","userId":895},{"commodityId":32,"content":"挺好的","createTime":1551893632000,"headPic":"http://172.17.8.100/images/small/default/user.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-06/1863920190306113352.jpg","nickName":"rainbow","userId":32},{"commodityId":32,"content":"啦啦啦啦啦","createTime":1551822587000,"headPic":"http://172.17.8.100/images/small/default/user.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-05/2704820190305154947.png","nickName":"m2_8Zp22","userId":1539},{"commodityId":32,"content":"挺好的","createTime":1551814820000,"headPic":"http://172.17.8.100/images/small/default/user.jpg","image":"","nickName":"rainbow","userId":32},{"commodityId":32,"content":"123","createTime":1551805769000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-07/20190307112048.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-05/2170420190305110929.jpg","nickName":"213","userId":895},{"commodityId":32,"content":"","createTime":1551798523000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-06/20190306201017.jpg","image":"http://172.17.8.100/images/small/comment_pic/2019-03-05/3156220190305090843.jpg","nickName":"222","userId":600},{"commodityId":32,"content":"666","createTime":1551494379000,"headPic":"http://172.17.8.100/images/small/head_pic/2019-04-06/20190406183954.png","image":"http://172.17.8.100/images/small/comment_pic/2019-03-01/1735420190301203939.png","nickName":"马大帅","userId":756}]
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
         * commodityId : 32
         * content : 很好
         * createTime : 1551997249000
         * headPic : http://172.17.8.100/images/small/default/user.jpg
         * image : http://172.17.8.100/images/small/comment_pic/2019-03-07/2349620190307162049.png
         * nickName : rainbow
         * userId : 32
         */

        private int commodityId;
        private String content;
        private long createTime;
        private String headPic;
        private String image;
        private String nickName;
        private int userId;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
