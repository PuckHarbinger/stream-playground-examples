import countries.*;
var countries=new Countries().getAll();
//1. Returns whether there is at least one country with the word _island_ in its name ignoring case.
countries.stream().anyMatch(country -> country.getName().toLowerCase().contains("island"));

//2. Returns the first country name that contains the word _island_ ignoring case.
countries.stream().filter(country -> country.getName().toLowerCase().contains("island")).limit(1).forEach(System.out::println);

//3. Prints each country name in which the first and the last letters are the same ignoring case.
countries.stream().filter(country->country.getName().charAt(0)==country.getName().charAt("length-1")).map(Country::getName).forEach(System.out::println);

//4. Prints the populations of the first ten least populous countries (required intermediate operation: [`stream`](https:docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html#sorted()), [`limit`](https:docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html#limit(long)))).
countries.stream().map(Country::getPopulation).sorted().limit(10).forEach(System.out::println);

//5. Prints the names of the first ten least populous countries (required intermediate operation: [`sorted`](https:docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html#sorted(java.util.Comparator)), [`limit`](https:docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html#limit(long)))).
countries.stream().map(Country::getPopulation).sorted().limit(10).forEach(System.out::println);

//6. Returns summary statistics about the number of country name translations associated with each country (required intermediate operation: [`mapToInt`](https:docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html#mapToInt(java.util.function.ToIntFunction)), [`summaryStatistics`](https:docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/IntStream.html#summaryStatistics())).
countries.stream().mapToInt(Country::getTranslations).summaryStatistics();

//7. Prints the names of countries in the ascending order of the number of timezones.
countries.stream().flatMap(country -> country.getTimezones().stream()).distinct().count().forEach(System.out::println);

//8. Prints the number of timezones for each country in the form _name_`: `_population_, in the ascending order of the number of timezones.
countries.stream().map(Country::getTimezones).sorted().count();

//9. Returns the number of countries with no Spanish country name translation (the Spanish language is identified by the language code `es`).
countries.stream().filter(country->country.getName()!="es").map(Country::getTranslations).forEach(System.out::println);

//10. Prints the names of countries with `null` area.
countries.stream().filter(country->country.getArea().compareTo(0)).map(Country::getArea).forEach(System.out::println);

//11. Prints all distinct language tags of country name translations sorted in alphabetical order (required intermediate operation: [`flatMap`](https:docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html#flatMap(java.util.function.Function))).
countries.stream().distinct().sorted().forEach(System.out::println);

//12. Returns the average length of country names.
countries.stream().map(Country::getName).average();

//13. Prints all distinct regions of the countries with `null` area.
countries.stream().filter(country->country.getArea()=="null").flatMap(country -> country.getRegion().stream()).distinct().forEach(System.out::println);

//14. Returns the largest country with non-`null` area.
countries.stream().filter(country->country.getArea()!=null).mapToLong(Country::getArea).max().get();

//15. Prints the names of countries with a non-`null` area below 10 (requires the use of [`BigDecimal.TEN`](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html#TEN)).
countries.stream().filter(country->country.getArea()<10).BigDecimal.TEN(Country::getArea);

//16. Prints all distinct timezones of European and Asian countries.
countries.stream().filter(country->country.getRegion()==Region.EUROPE||country.getRegion()==Region.ASIA).flatMap(country -> country.getTimezones().stream()).distinct().forEach(System.out::println);
