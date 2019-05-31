package com.bw.movie.bean;

import java.util.List;

/**
 * @Author：dell
 * @E-mail： 15001194794@163.com
 * @Date：2019/5/16 17:22
 * @Description：描述信息
 */
public class PingLunBean {


    /**
     * result : [{"commentContent":"，，，，，，，，","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-04-27/20190427111301.png","commentId":283,"commentTime":1557733973000,"commentUserId":973,"commentUserName":"2323","greatNum":3,"hotComment":0,"isGreat":0},{"commentContent":"啊啊啊","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-04-27/20190427111301.png","commentId":282,"commentTime":1557404101000,"commentUserId":973,"commentUserName":"2323","greatNum":3,"hotComment":0,"isGreat":0},{"commentContent":"几点接你","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-11/20190511224538.jpg","commentId":281,"commentTime":1556419288000,"commentUserId":942,"commentUserName":"田吉","greatNum":7,"hotComment":0,"isGreat":0},{"commentContent":"，，","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-04-27/20190427111301.png","commentId":280,"commentTime":1556335410000,"commentUserId":973,"commentUserName":"2323","greatNum":5,"hotComment":0,"isGreat":0},{"commentContent":"有好多话","commentHeadPic":"http://mobile.bwstudent.com/images/movie/head_pic/2019-05-11/20190511224538.jpg","commentId":279,"commentTime":1556250964000,"commentUserId":942,"commentUserName":"田吉","greatNum":6,"hotComment":0,"isGreat":0}]
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
         * commentContent : ，，，，，，，，
         * commentHeadPic : http://mobile.bwstudent.com/images/movie/head_pic/2019-04-27/20190427111301.png
         * commentId : 283
         * commentTime : 1557733973000
         * commentUserId : 973
         * commentUserName : 2323
         * greatNum : 3
         * hotComment : 0
         * isGreat : 0
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int hotComment;
        private int isGreat;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getHotComment() {
            return hotComment;
        }

        public void setHotComment(int hotComment) {
            this.hotComment = hotComment;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }
    }
}
