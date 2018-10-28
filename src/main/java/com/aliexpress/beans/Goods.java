package com.aliexpress.beans;

public class Goods {

    private int id;
    private String name;
    private String ename;
    private String tagids;
    private String cost;
    private String weight;
    private String kind;
    private String supplier;
    private String remarks;
    private String idpic;// 主图片
    private String idpics;
    private String description;
    private String createtime;
    private String upatetime;
    private int status;//1 正常 2 停产 3 清货 4 待编辑 5新品 6爆款
    private String declarezh;
    private String declareen;
    private String shop;
    private String multiproduct;

    public String getDeclarezh() {
        return declarezh;
    }

    public void setDeclarezh(String declarezh) {
        this.declarezh = declarezh;
    }

    public String getDeclareen() {
        return declareen;
    }

    public void setDeclareen(String declareen) {
        this.declareen = declareen;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getIdpic() {
        return idpic;
    }

    public void setIdpic(String idpic) {
        this.idpic = idpic;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTagids() {
        return tagids;
    }

    public void setTagids(String tagids) {
        this.tagids = tagids;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIdpics() {
        return idpics;
    }

    public void setIdpics(String idpics) {
        this.idpics = idpics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpatetime() {
        return upatetime;
    }

    public void setUpatetime(String upatetime) {
        this.upatetime = upatetime;
    }

    public String getMultiproduct() {
        return multiproduct;
    }

    public void setMultiproduct(String multiproduct) {
        this.multiproduct = multiproduct;
    }
}
