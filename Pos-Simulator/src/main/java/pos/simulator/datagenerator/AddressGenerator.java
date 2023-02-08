
package pos.simulator.datagenerator;

import org.springframework.stereotype.Service;
import project.commons.domain.DeliveryAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AddressGenerator {
    List<String> addressLines = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    List<String> states = new ArrayList<>();
    public String getRandomAddressLine(){
        addressLines.add("32/32, Patel Nagar (w)");
        addressLines.add("12-13-197/403-405, Tarnaka");
        addressLines.add("215");
        addressLines.add("F 227");
        addressLines.add("8/10 Stringer Street, 4th Floor");
        addressLines.add("Conservation Education Ce, Nr Filmcity, Goregaon");
        addressLines.add("1/1a, Shah Niwas, Zakaria Road, Malad(w)");
        addressLines.add(" Todi Estate,sun Mill Cpd, Lower Parel");
        addressLines.add("#32-210, Shapurnagar");
        addressLines.add("M 25, Part 2, Main Market, Greater Kailash");
        Random random = new Random();
        return addressLines.get(random.nextInt(10));
    }
    public String getRandomCity(){
        cities.add("Delhi");
        cities.add("Hyderabad");
        cities.add("Mumbai");
        cities.add("Chennai");
        cities.add(" Karnataka");
        cities.add("Karnal");
        cities.add("Amabala");
        cities.add("Rohini");
        cities.add("Srinagar");
        cities.add("Ganganagar");
        Random random = new Random();
        return cities.get(random.nextInt(10));
    }

    public String getRandomState(){
        states.add("Haryana");
        states.add("Punjab");
        states.add("Gujrat");
        states.add("Karnataka");
        states.add("Telangana");
        states.add("Uttar Pradesh");
        states.add("Himachal");
        states.add("Rajasthan");
        states.add("Goa");
        states.add("Madhya Pradesh");
        Random random = new Random();
        return states.get(random.nextInt(10));
    }
    public DeliveryAddress getDeliveryAddress() {
        Random random = new Random();
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setAddressLine(getRandomAddressLine());
        deliveryAddress.setCity(getRandomCity());
        deliveryAddress.setState(getRandomState());
        deliveryAddress.setPinCode(random.nextInt(999999));
        deliveryAddress.setContactNumber(random.nextInt(999999999));
        return deliveryAddress;
    }

}
