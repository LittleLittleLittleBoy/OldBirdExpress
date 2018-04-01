package cn.edu.nju.laoniao.oldbirdexpress.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 订单信息
 * Created by liweimin on 2018/3/30.
 */

public class Order {


    /**
     * 订单编号
     */
    private String orderid;
    /**
     * 发布的用户名
     */
    private String user;
    /**
     * 领取人的用户名
     */
    private String recipients;
    /**
     * 发布人的学校
     */
    private String school;
    /**
     * 快递领取地点
     */
    private String fromPlace;
    /**
     * 快递送达地点
     */
    private String toPlace;
    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;
    /**
     * 送货时间的最小值
     */
    private LocalTime fromTime;
    /**
     * 送货时间的最大值
     */
    private LocalTime toTime;
    /**
     * 订单价格
     */
    private double price;
    /**
     * 快递详情
     */
    private String detail;
    /**
     * 备注信息
     */
    private String note;
    /**
     * 订单当前状态
     * 0未认领
     * 1已接单
     * 2已取件
     * 3已送达
     */
    private int type;



    public Order(String orderid, String user, String recipients, String school, String fromPlace, String toPlace, LocalDateTime createTime, LocalTime fromTime, LocalTime toTime, double price, String detail, String note, int type) {
        this.orderid = orderid;
        this.user = user;
        this.recipients = recipients;
        this.school = school;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.createTime = createTime;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.price = price;
        this.detail = detail;
        this.note = note;
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
