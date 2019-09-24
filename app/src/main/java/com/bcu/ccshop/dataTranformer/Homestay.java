package com.bcu.ccshop.dataTranformer;

public class Homestay {
    /**
	* 民宿编号
	*/
    private String hId;

    /**
	* 民宿名称
	*/
    private String hName;

    /**
	* 民宿主图片
	*/
    private String hTitleImage;

    /**
	* 民宿坐标 (经度;纬度)
	*/
    private String hPosition;

    /**
	* 联系电话
	*/
    private String hPhone;

    /**
	* 价格
	*/
    private String hPrice;

    /**
	* 房间数
	*/
    private String hRoom;

    /**
	* 厅数
	*/
    private String hHall;

    /**
	* 卫生间数量
	*/
    private String hToilet;

    /**
	* 朝向
	*/
    private String hAspect;

    /**
	* 房屋大小（平米）
	*/
    private String hSize;

    /**
	* 可容纳人数
	*/
    private String hPeople;

    /**
	* 床数
	*/
    private String hBedCount;

    /**
	* 标签（多个用；隔开）
	*/
    private String hTag;

    /**
	* 当前状态（-1 不可用  0 被预定  1可用）
	*/
    private String hStatus;

    /**
	* 民宿详细图片 (;分隔)
	*/
    private String hDetailImage;

    /**
	* 民俗详细描述
	*/
    private String hDescription;

    /**
	* 备注
	*/
    private String hRemark;

    /**
     * 民宿地址
     */
    private String hAddress;




    public String gethAddress() {
        return hAddress;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethTitleImage() {
        return hTitleImage;
    }

    public void sethTitleImage(String hTitleImage) {
        this.hTitleImage = hTitleImage;
    }

    public String gethPosition() {
        return hPosition;
    }

    public void sethPosition(String hPosition) {
        this.hPosition = hPosition;
    }

    public String gethPhone() {
        return hPhone;
    }

    public void sethPhone(String hPhone) {
        this.hPhone = hPhone;
    }

    public String gethPrice() {
        return hPrice;
    }

    public void sethPrice(String hPrice) {
        this.hPrice = hPrice;
    }

    public String gethRoom() {
        return hRoom;
    }

    public void sethRoom(String hRoom) {
        this.hRoom = hRoom;
    }

    public String gethHall() {
        return hHall;
    }

    public void sethHall(String hHall) {
        this.hHall = hHall;
    }

    public String gethToilet() {
        return hToilet;
    }

    public void sethToilet(String hToilet) {
        this.hToilet = hToilet;
    }

    public String gethAspect() {
        return hAspect;
    }

    public void sethAspect(String hAspect) {
        this.hAspect = hAspect;
    }

    public String gethSize() {
        return hSize;
    }

    public void sethSize(String hSize) {
        this.hSize = hSize;
    }

    public String gethPeople() {
        return hPeople;
    }

    public void sethPeople(String hPeople) {
        this.hPeople = hPeople;
    }

    public String gethBedCount() {
        return hBedCount;
    }

    public void sethBedCount(String hBedCount) {
        this.hBedCount = hBedCount;
    }

    public String gethTag() {
        return hTag;
    }

    public void sethTag(String hTag) {
        this.hTag = hTag;
    }

    public String gethStatus() {
        return hStatus;
    }

    public void sethStatus(String hStatus) {
        this.hStatus = hStatus;
    }

    public String gethDetailImage() {
        return hDetailImage;
    }

    public void sethDetailImage(String hDetailImage) {
        this.hDetailImage = hDetailImage;
    }

    public String gethDescription() {
        return hDescription;
    }

    public void sethDescription(String hDescription) {
        this.hDescription = hDescription;
    }

    public String gethRemark() {
        return hRemark;
    }

    public void sethRemark(String hRemark) {
        this.hRemark = hRemark;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hId=").append(hId);
        sb.append(", hName=").append(hName);
        sb.append(", hTitleImage=").append(hTitleImage);
        sb.append(", hPosition=").append(hPosition);
        sb.append(", hPhone=").append(hPhone);
        sb.append(", hPrice=").append(hPrice);
        sb.append(", hRoom=").append(hRoom);
        sb.append(", hHall=").append(hHall);
        sb.append(", hToilet=").append(hToilet);
        sb.append(", hAspect=").append(hAspect);
        sb.append(", hSize=").append(hSize);
        sb.append(", hPeople=").append(hPeople);
        sb.append(", hBedCount=").append(hBedCount);
        sb.append(", hTag=").append(hTag);
        sb.append(", hStatus=").append(hStatus);
        sb.append(", hDetailImage=").append(hDetailImage);
        sb.append(", hDescription=").append(hDescription);
        sb.append(", hRemark=").append(hRemark);
        sb.append("]");
        return sb.toString();
    }
}