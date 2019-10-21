package info.tomfi.alexa.skills.shabbattimes.tools;

import static lombok.AccessLevel.PRIVATE;

import java.util.Optional;

import com.amazon.ask.model.Slot;

import info.tomfi.alexa.skills.shabbattimes.city.City;
import info.tomfi.alexa.skills.shabbattimes.country.Country;
import info.tomfi.alexa.skills.shabbattimes.country.CountryFactory;
import info.tomfi.alexa.skills.shabbattimes.enums.CountryInfo;
import info.tomfi.alexa.skills.shabbattimes.exception.NoCityFoundException;
import info.tomfi.alexa.skills.shabbattimes.exception.NoCityInCountryException;
import lombok.NoArgsConstructor;
import lombok.val;

/**
 * Utility class for locating a city within the backend country and cities data.
 *
 * @author Tomer Figenblat {@literal <tomer.figenblat@gmail.com>}
 */
@NoArgsConstructor(access = PRIVATE)
public final class CityLocator
{
    /**
     * A static tool for creating the country and city objects.
     *
     * @param countrySlot the Slot holding the country value collected from the customer.
     * @param citySlot the Slot holding the city value collected from the customer.
     * @return the {@link info.tomfi.alexa.skills.shabbattimes.city.City} object.
     * @throws NoCityFoundException when an unknown city was provided with no country specification.
     * @throws NoCityInCountryException when an unknown city within a country was encountered.
     */
    public static City getByCityAndCountry(final Slot countrySlot, final Slot citySlot)
        throws NoCityFoundException, NoCityInCountryException
    {
        if (countrySlot.getValue() == null)
        {
            return getByCity(citySlot);
        }
        val country = CountryFactory.getCountry(countrySlot.getValue());
        val cityOpt = findCityInCountry(country, citySlot.getValue());
        if (cityOpt.isPresent())
        {
            return cityOpt.get();
        }
        throw new NoCityInCountryException(String.format("no city %s in %s.", citySlot.getValue(), countrySlot.getValue()));
    }

    private static City getByCity(final Slot citySlot)
    {
        for (val member : CountryInfo.values())
        {
            val country = CountryFactory.getCountry(member);
            val cityOpt = findCityInCountry(country, citySlot.getValue());
            if (cityOpt.isPresent())
            {
                return cityOpt.get();
            }
        }
        throw new NoCityFoundException(String.format("city %s not found", citySlot.getValue()));
    }

    private static Optional<City> findCityInCountry(final Country country, final String cityName)
    {
        val cities = country.iterator();
        while (cities.hasNext())
        {
            val currentCity = cities.next();
            val aliases = currentCity.iterator();
            while (aliases.hasNext())
            {
                val currentAlias = aliases.next();
                if (currentAlias.equalsIgnoreCase(cityName))
                {
                    return Optional.of(currentCity);
                }
            }
        }
        return Optional.empty();
    }
}
