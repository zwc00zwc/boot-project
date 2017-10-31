package core.domain.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/21.
 */
public class Member {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户显示名")
    private String displayName;
    @ApiModelProperty(value = "头像地址")
    private String imgUrl;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "用户地址")
    private String address;
    @ApiModelProperty(value = "性别")
    private Short sex;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "状态")
    private Short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}
