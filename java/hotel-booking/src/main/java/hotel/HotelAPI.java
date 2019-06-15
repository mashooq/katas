package hotel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HotelAPI {
    private HashMap<String, String> companyEmployees = new HashMap<>();
    private HashMap<String, Set<CompanyPolicy>> companyPolicies = new HashMap<>();

    public boolean book(String hotelId, String employeeId, String roomType) {
        String companyId = companyEmployees.get(employeeId);
        if (companyPolicies.containsKey(companyId))
            return companyPolicies.get(companyId).contains(new CompanyPolicy(hotelId, roomType));

        return false;
    }

    public HotelAPI addCompanyPolicy(String companyId, String hotelId, String roomType) {
        Set<CompanyPolicy> hotels = companyPolicies.get(companyId);
        if (hotels == null) {
            hotels = new HashSet<>();
            companyPolicies.put(companyId, hotels);
        }
        hotels.add(new CompanyPolicy(hotelId, roomType));
        return this;
    }

    public HotelAPI addCompanyEmployee(String companyId, String employeeId) {
        this.companyEmployees.put(employeeId, companyId);
        return this;
    }

}

class CompanyPolicy {
    final String hotelId;
    final String roomType;

    CompanyPolicy(String hotelId, String roomType) {
        this.hotelId = hotelId;
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyPolicy that = (CompanyPolicy) o;
        return hotelId.equals(that.hotelId) &&
                roomType.equals(that.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, roomType);
    }
}
