package cz.muni.fi.pa165.bookingmanager.dto;

/**
 *
 * @author ivet
 */
public class ChangeImageDTO {
    private Long id;
    private byte[] image;
    private String imageMimeType;

    public String getImageMimeType() {
        return imageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        this.imageMimeType = imageMimeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
