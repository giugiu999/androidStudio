# LAB 06 Instructions

1. Clone this repository and open it (`code` folder) in **Android Studio**.

2. Create a new class `CityList` under `com.example.code`  
Write Javadoc comments for this class.

```java
/**
 * This is a class that keeps track of a list of city objects
 */
public class CityList {
}
```

3. Declare a list to hold the city objects. Import `java.util.List` and `java.util.ArrayList`.

```java
private List<City> cities = new ArrayList<>();
```

4. Implement a method to add city objects to this list. If a city already exists, throw an Exception. Write Javadoc comments with `@param` tag.

```java
/**
 * This adds a city to the list if the city does not exist
 * @param city
 *      This is a candidate city to add
 */
public void add(City city) {
    if (cities.contains(city)) {
        throw new IllegalArgumentException();
    }
    cities.add(city);
}
```
`contains` method internally calls `Object.equals()` when comparing objects. Since we are comparing `City` objects, we need to implement a custom logic to compare two `City` objects.

```java
public class City{
    private String name;
    private String province;

    // constructor and getters/setters

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City cityObj = (City) o;
        return this.name.equals(cityObj.getName()) && this.province.equals(cityObj.getProvince());
    }
}
```

5. Create another method to get a list of city objects sorted according to the city name. Include Javadoc comments with `@return` tag. Import `java.util.Collections`.

```java
/**
 * This returns a sorted list of cities
 * @return
 *      Return the sorted list
 */
public List<City> getCities() {
    List<City> list = cities;
    Collections.sort(list);
    return list;
}
```

6. In the `CityList` class the `Collections.sort(list)` shows an error. We want to sort the name of the cities, but we are trying to sort the city objects. To sort an Object by its property, we have to make the Object implement the `Comparable` interface and override the `compareTo()` method. Lists (and arrays) of objects that implement `Comparable` interface can be sorted automatically by `Collections.sort()`. We also need to implement the method `compareTo()`. All wrapper classes and `String` class implement `Comparable` interface. Wrapper classes are compared by their values, and strings are compared lexicographically. To learn more: [Java Comparable Interface](https://howtodoinjava.com/java/collections/java-comparable-interface/)


7. Go to the `City` class and ensure it looks like the following with `Comparable<city>` interface and `compareTo()` method implementation:

```java
package com.example.code;

public class City implements Comparable<City> {
    private String name;
    private String province;

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public int compareTo(City city) {
        return this.name.compareTo(city.getName());
    }
}
```

8. Now the error disappears, and you can sort city objects by city names. If two cities are equal, the `compareTo()` method returns `0`.

9. Final `CityList` class:

```java
package com.example.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps track of a list of city objects
 */
public class CityList {
    private List<City> cities = new ArrayList<>();

    /**
     * This adds a city to the list if the city does not exist
     * @param city
     *      This is a candidate city to add
     */
    public void add(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     * @return
     *      Return the sorted list
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }
}
```
10. Select **Tools -> Generate Javadoc** to create HTML documentation from your code and Javadoc comments. Select **Module app** and specify the output directory.

11. If errors generating Javadoc, add the following line under the `dependencies` of app-level build.gradle:
    ```java
    implementation(files("${android.sdkDirectory}/platforms/${android.compileSdkVersion}/android.jar"))
    ```
12. **[Second Approach]** If more errors while following step 19 then remove the line from step 19 and follow the steps:  
Select **View -> Tool Windows -> Projects**, then click the **Android** tab and choose **Project** from the list.  
Expand **External libraries -> Android API `<API NUMBER>`**.  
Copy the path for `android.jar`, and add it under the `dependencies` the `app`-level `build.gradle` (Example `path` for Windows):

```gradle
implementation(files("C:/Users/[Your_USERNAME]/AppData/Local/Android/Sdk/platforms/android-35/android.jar"))
```

13. Resync the project and generate Javadoc again. Sometimes selecting **Whole Project** or **Custom scope -> Project files** works if **Module app** does not work.

14. Now let’s move to writing unit tests. We will use junit 5 for this lab because it has more modern testing features. By default junit 4 is included in every project. To use junit 5 you’ll have to include the following lines in `build.gradle.kts(Module:app)`
```
testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
```
Sync the gradle to get the dependencies.

15. Under `com.example.code(test)` folder, create a class `CityListTest` to test the functionalities of the `CityList` class.

16. Create two private methods for creating a mock city object and adding it to `CityList`.

```java
package com.example.code;

public class CityListTest {

    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }
}
```

17. Write a test for the `add()` method which is in `CityList` class. Write it after the `mockCity()` method. Add city objects using `add()` method and check if the addition is successful by `assertEquals()` and `assertTrue()`. We need to add `@Test` before any test method to identify it as a junit test. Necessary imports: `import static org.junit.Assert.assertEquals`, `import static org.junit.Assert.assertThrows`, `import static org.junit.Assert.assertTrue`, and `import org.junit.Test;`

```java
@Test
public void testAdd() {
    CityList cityList = mockCityList();

    assertEquals(1, cityList.getCities().size());

    City city = new City("Regina", "Saskatchewan");
    cityList.add(city);

    assertEquals(2, cityList.getCities().size());
    assertTrue(cityList.getCities().contains(city));
}
```

18. Write another test to check for exceptions when adding a duplicate city.

```java
@Test
public void testAddException() {
    CityList cityList = mockCityList();

    City city = new City("Yellowknife", "Northwest Territories");
    cityList.add(city);

    assertThrows(IllegalArgumentException.class, () -> {
        cityList.add(city);
    });
}
```

19. Write a test method for `getCities()` in the `CityList` class.

```java
@Test
public void testGetCities() {
    CityList cityList = mockCityList();

    assertEquals(0, mockCity().compareTo(cityList.getCities().get(0)));

    City city = new City("Charlottetown", "Prince Edward Island");
    cityList.add(city);

    assertEquals(0, city.compareTo(cityList.getCities().get(0)));
    assertEquals(0, mockCity().compareTo(cityList.getCities().get(1)));
}
```

20. Run the tests by right-clicking on the test folder **[com.example.code(test)]** and selecting **Run ‘Tests in code’**.  
All tests should pass.

21.	The tests can be written before the real implementation of the `CityList` class. We can implement all the tests first and they must fail as there is no implementation of `CityList` class and its methods. Then we can give implementation of the class and methods. Then our tests would pass. This is called **test-driven development (TDD)**. You can also follow this method.

22. Final `CityListTest` class:

```java
package com.example.code;

import static org.junit.Assert.assertEquals;
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
    public void testAdd() {
        CityList cityList = mockCityList();

        assertEquals(1, cityList.getCities().size());

        City city = new City("Regina", "Saskatchewan");
        cityList.add(city);

        assertEquals(2, cityList.getCities().size());
        assertTrue(cityList.getCities().contains(city));
    }

    @Test
    public void testAddException() {
        CityList cityList = mockCityList();

        City city = new City("Yellowknife", "Northwest Territories");
        cityList.add(city);

        assertThrows(IllegalArgumentException.class, () -> {
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
}
```
23. . If you get this error section of gradle “Test events not received”, then add the following lines to `android{...}` section in `build.gradle.kts(Module: app).

```
android {
...
    tasks.withType<Test>{
        useJUnitPlatform()
    }
}
```
