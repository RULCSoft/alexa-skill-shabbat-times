package info.tomfi.alexa.skills.shabbattimes.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum helper for identifying the incoming request types.
 *
 * @author Tomer Figenblat {@literal <tomer.figenblat@gmail.com>}
 */
@RequiredArgsConstructor
public enum Intents
{
    CANCEL("AMAZON.CancelIntent"),
    COUNTRY_SELECTED("CountrySelected"),
    FALLBACK("AMAZON.FallbackIntent"),
    GET_CITY("GetCityIntent"),
    HELP("AMAZON.HelpIntent"),
    NO("AMAZON.NoIntent"),
    STOP("AMAZON.StopIntent"),
    THANKS("ThanksIntent"),
    YES("AMAZON.YesIntent");

    @Getter private final String name;
}
