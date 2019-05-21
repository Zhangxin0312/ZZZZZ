package com.bw.myproduct.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GouBeans {
    @Id(autoincrement = true)
    private  Long id;
    private String message;
    private String status;
        private  boolean flag;
        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;
        private  int num;
        @Generated(hash = 2061717599)
        public GouBeans(Long id, String message, String status, boolean flag,
                int commodityId, String commodityName, int count, String pic, int price,
                int num) {
            this.id = id;
            this.message = message;
            this.status = status;
            this.flag = flag;
            this.commodityId = commodityId;
            this.commodityName = commodityName;
            this.count = count;
            this.pic = pic;
            this.price = price;
            this.num = num;
        }
        @Generated(hash = 1472319425)
        public GouBeans() {
        }
        public Long getId() {
            return this.id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public String getMessage() {
            return this.message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getStatus() {
            return this.status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public boolean getFlag() {
            return this.flag;
        }
        public void setFlag(boolean flag) {
            this.flag = flag;
        }
        public int getCommodityId() {
            return this.commodityId;
        }
        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }
        public String getCommodityName() {
            return this.commodityName;
        }
        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }
        public int getCount() {
            return this.count;
        }
        public void setCount(int count) {
            this.count = count;
        }
        public String getPic() {
            return this.pic;
        }
        public void setPic(String pic) {
            this.pic = pic;
        }
        public int getPrice() {
            return this.price;
        }
        public void setPrice(int price) {
            this.price = price;
        }
        public int getNum() {
            return this.num;
        }
        public void setNum(int num) {
            this.num = num;
        }

}
