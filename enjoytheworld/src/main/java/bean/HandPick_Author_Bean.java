package bean;

/**
 * Created by Sadewangzi on 2016/7/26.
 */
public class HandPick_Author_Bean {
    /**
     * {
     "id": 12,
     "icon": "http://img.wdjimg.com/image/video/26c9ad3b00588814400b3c88151d3f03_0_0.jpeg",
     "name": "OX studio 公牛体育影视工作室",
     "description": "中国第一品牌体育影视工作室！独特的创意，精致的视频质量，带给你不一样的传播视角。",
     "link": "http://weibo.com/u/1589179524",
     "latestReleaseTime": 1469462400000,
     "videoNum": 4,
     "adTrack": null
     }
     */
    private String id;
    private String icon;
    private String name;
    private String description;
    private String link;
    private String latestReleaseTime;
    private String videoNum;
    private String adTrack;

    public HandPick_Author_Bean() {
    }

    public HandPick_Author_Bean(String id, String icon, String name, String description, String link, String latestReleaseTime, String videoNum, String adTrack) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.link = link;
        this.latestReleaseTime = latestReleaseTime;
        this.videoNum = videoNum;
        this.adTrack = adTrack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLatestReleaseTime() {
        return latestReleaseTime;
    }

    public void setLatestReleaseTime(String latestReleaseTime) {
        this.latestReleaseTime = latestReleaseTime;
    }

    public String getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(String videoNum) {
        this.videoNum = videoNum;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }
}
