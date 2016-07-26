package bean;

/**
 * Created by admin on 2016/7/26.
 */
public class FindMainItem {
    String image;
    String id;
    String tltle;
    public FindMainItem(String image, String id, String tltle) {
        this.image = image;
        this.id = id;
        this.tltle = tltle;
    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public String getTltle() {
        return tltle;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTltle(String tltle) {
        this.tltle = tltle;
    }

    @Override
    public String toString() {
        return "FindMainItem{" +
                "image='" + image + '\'' +
                ", id='" + id + '\'' +
                ", tltle='" + tltle + '\'' +
                '}';
    }
}
