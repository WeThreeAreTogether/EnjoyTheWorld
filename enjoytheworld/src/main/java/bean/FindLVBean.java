package bean;

import java.util.List;

/**
 * Created by admin on 2016/7/26.
 */
public class FindLVBean {

    /**
     * dataType : VideoBeanForClientV1
     * id : 7910
     * title : 美国总统选举竞争唱出来
     * description : 被玩的总统候选人们。不过这位小哥已经非常厚道了，不熟悉美国大选的朋友，可以通过这几分钟的视频快速恶补一下。From Paint
     * provider : {"name":"YouTube","alias":"youtube","icon":"http://img.wdjimg.com/image/video/fa20228bc5b921e837156923a58713f6_256_256.png"}
     * category : 音乐
     * author : null
     * playUrl : http://baobab.wandoujia.com/api/v1/playUrl?vid=7910&editionType=default
     * duration : 289
     * releaseTime : 1467717777000
     * playInfo : [{"height":480,"width":854,"name":"标清","type":"normal","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=7910&editionType=normal"},{"height":720,"width":1280,"name":"高清","type":"high","url":"http://baobab.wandoujia.com/api/v1/playUrl?vid=7910&editionType=high"}]
     * consumption : {"collectionCount":172,"shareCount":172,"replyCount":12}
     * campaign : null
     * waterMarks : null
     * adTrack : null
     * tags : []
     * type : NORMAL
     * idx : 0
     * shareAdTrack : null
     * favoriteAdTrack : null
     * webAdTrack : null
     * date : 1467717777000
     * promotion : null
     * label : null
     * coverForFeed : http://img.wdjimg.com/image/video/b1a59de877c074e0b349f4109f0136ef_0_0.jpeg
     * coverForDetail : http://img.wdjimg.com/image/video/b1a59de877c074e0b349f4109f0136ef_0_0.jpeg
     * coverBlurred : http://img.wdjimg.com/image/video/9da3819e12b4054a2d9099fefdaad55c_0_0.jpeg
     * coverForSharing : null
     * webUrlForWeibo : http://wandou.im/2gev2h
     * rawWebUrl : http://www.wandoujia.com/eyepetizer/detail.html?vid=7910
     */

    private String dataType;
    private int id;
    private String title;
    private String description;
    /**
     * name : YouTube
     * alias : youtube
     * icon : http://img.wdjimg.com/image/video/fa20228bc5b921e837156923a58713f6_256_256.png
     */

    private ProviderBean provider;
    private String category;
    private Object author;
    private String playUrl;
    private int duration;
    private long releaseTime;
    /**
     * collectionCount : 172
     * shareCount : 172
     * replyCount : 12
     */

    private ConsumptionBean consumption;
    private Object campaign;
    private Object waterMarks;
    private Object adTrack;
    private String type;
    private int idx;
    private Object shareAdTrack;
    private Object favoriteAdTrack;
    private Object webAdTrack;
    private long date;
    private Object promotion;
    private Object label;
    private String coverForFeed;
    private String coverForDetail;
    private String coverBlurred;
    private Object coverForSharing;
    private String webUrlForWeibo;
    private String rawWebUrl;
    /**
     * height : 480
     * width : 854
     * name : 标清
     * type : normal
     * url : http://baobab.wandoujia.com/api/v1/playUrl?vid=7910&editionType=normal
     */

    private List<PlayInfoBean> playInfo;
    private List<?> tags;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public ConsumptionBean getConsumption() {
        return consumption;
    }

    public void setConsumption(ConsumptionBean consumption) {
        this.consumption = consumption;
    }

    public Object getCampaign() {
        return campaign;
    }

    public void setCampaign(Object campaign) {
        this.campaign = campaign;
    }

    public Object getWaterMarks() {
        return waterMarks;
    }

    public void setWaterMarks(Object waterMarks) {
        this.waterMarks = waterMarks;
    }

    public Object getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(Object adTrack) {
        this.adTrack = adTrack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Object getShareAdTrack() {
        return shareAdTrack;
    }

    public void setShareAdTrack(Object shareAdTrack) {
        this.shareAdTrack = shareAdTrack;
    }

    public Object getFavoriteAdTrack() {
        return favoriteAdTrack;
    }

    public void setFavoriteAdTrack(Object favoriteAdTrack) {
        this.favoriteAdTrack = favoriteAdTrack;
    }

    public Object getWebAdTrack() {
        return webAdTrack;
    }

    public void setWebAdTrack(Object webAdTrack) {
        this.webAdTrack = webAdTrack;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public String getCoverForFeed() {
        return coverForFeed;
    }

    public void setCoverForFeed(String coverForFeed) {
        this.coverForFeed = coverForFeed;
    }

    public String getCoverForDetail() {
        return coverForDetail;
    }

    public void setCoverForDetail(String coverForDetail) {
        this.coverForDetail = coverForDetail;
    }

    public String getCoverBlurred() {
        return coverBlurred;
    }

    public void setCoverBlurred(String coverBlurred) {
        this.coverBlurred = coverBlurred;
    }

    public Object getCoverForSharing() {
        return coverForSharing;
    }

    public void setCoverForSharing(Object coverForSharing) {
        this.coverForSharing = coverForSharing;
    }

    public String getWebUrlForWeibo() {
        return webUrlForWeibo;
    }

    public void setWebUrlForWeibo(String webUrlForWeibo) {
        this.webUrlForWeibo = webUrlForWeibo;
    }

    public String getRawWebUrl() {
        return rawWebUrl;
    }

    public void setRawWebUrl(String rawWebUrl) {
        this.rawWebUrl = rawWebUrl;
    }

    public List<PlayInfoBean> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<PlayInfoBean> playInfo) {
        this.playInfo = playInfo;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    public static class ProviderBean {
        private String name;
        private String alias;
        private String icon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class ConsumptionBean {
        private int collectionCount;
        private int shareCount;
        private int replyCount;

        public int getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }
    }

    public static class PlayInfoBean {
        private int height;
        private int width;
        private String name;
        private String type;
        private String url;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
