package sampleData;
import cz.muni.fi.pa165.bookingmanager.entity.Hotel;
import cz.muni.fi.pa165.bookingmanager.service.HotelService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author matus
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade
{
    @Autowired
    HotelService hotelService;
    
    public static final String JPEG = "image/jpeg";
     
    Date date;

    @Override
    public void loadData() throws IOException {
        this.createDate();
        Hotel hotel = hotel("Voronez","Brno","Luxusny hotel v Brne", "null", null, date);
    }
    
    private void createDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 5);
        this.date = calendar.getTime();
    }
    
    private Hotel hotel(String name, String address, String description, String imageFile, String mimeType, Date date) throws IOException {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setImage(readImage(imageFile));
        hotel.setImageMimeType(mimeType);
        hotel.setLastUpdateDay(date);
        hotelService.createHotel(hotel);
        return hotel;
    }
    
      private byte[] readImage(String file) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/" + file)) {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
