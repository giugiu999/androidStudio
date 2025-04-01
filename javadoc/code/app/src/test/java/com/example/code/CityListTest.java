package com.example.code;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
public class CityListTest {

    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }
    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }
    @Test
    public void testadd() {
        CityList cityList=mockCityList();
        assertEquals(1,cityList.getCities().size());
        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);
        assertEquals(2,cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }
    @Test
    public void testdup(){
        CityList cityList = mockCityList();

        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);
        assertThrows(IllegalArgumentException.class,()->{
            cityList.add(city);
        });
    }
    @Test
    public void testGetCities() {
        CityList cityList = mockCityList();

        assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        assertEquals(0, city.compareTo(cityList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities().get(1))); 
    }
    @Test
    public void testhas(){
        City city = new City("2", "2");
        CityList citylist=mockCityList();
        assertTrue(citylist.hasCity(mockCity()));
        assertFalse(citylist.hasCity(city));
    }
    @Test
    public void testdel(){
        CityList cityList=mockCityList();
        assertEquals(1,cityList.getCities().size());
        cityList.delete(mockCity());
        assertEquals(0,cityList.getCities().size());
        assertFalse(cityList.getCities().contains(mockCity()));
    }
    @Test
    public void testcnt(){
        CityList cityList=mockCityList();
        assertEquals(1, cityList.countCities());
        City city = new City("2", "2");
        cityList.add(city);
        assertEquals(2, cityList.countCities());
        cityList.delete(city);
        assertEquals(1, cityList.countCities());
    }

}