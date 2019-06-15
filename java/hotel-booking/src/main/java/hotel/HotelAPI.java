package hotel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HotelAPI {
    private HashMap<String, String> companyEmployees = new HashMap<>();
    private HashMap<String, Set<String>> companyPolicies = new HashMap<>();

    public boolean book(String hotelId, String employeeId) {
        String companyId = companyEmployees.get(employeeId);
        if (companyPolicies.containsKey(companyId))
            return companyPolicies.get(companyId).contains(hotelId);

        return false;
    }

    public HotelAPI addCompanyPolicy(String companyId, String hotelId) {
        Set<String> hotels = companyPolicies.get(companyId);
        if (hotels == null) {
            hotels = new HashSet<>();
            companyPolicies.put(companyId, hotels);
        }
        hotels.add(hotelId);
        return this;
    }

    public HotelAPI addCompanyEmployee(String companyId, String employeeId) {
        this.companyEmployees.put(employeeId, companyId);
        return this;
    }

}
