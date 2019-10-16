package info.tomfi.alexa.skills.shabbattimes.request.handlers;

import static info.tomfi.alexa.skills.shabbattimes.tools.GlobalEnums.Attributes;
import static info.tomfi.alexa.skills.shabbattimes.tools.GlobalEnums.BundleKeys;
import static info.tomfi.alexa.skills.shabbattimes.tools.GlobalEnums.Intents;
import static info.tomfi.alexa.skills.shabbattimes.tools.LocalizationUtils.getBundleFromAttribures;
import static info.tomfi.alexa.skills.shabbattimes.tools.LocalizationUtils.getFromBundle;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import info.tomfi.alexa.skills.shabbattimes.annotation.IncludeRequestHandler;
import info.tomfi.alexa.skills.shabbattimes.country.Country;
import info.tomfi.alexa.skills.shabbattimes.country.CountryFactory;
import info.tomfi.alexa.skills.shabbattimes.enums.Slots;
import info.tomfi.alexa.skills.shabbattimes.exception.NoCountrySlotException;
import info.tomfi.alexa.skills.shabbattimes.exception.NoJsonFileException;

@IncludeRequestHandler
public final class CountrySelectedIntentHandler implements IntentRequestHandler
{
    @Override
    public boolean canHandle(final HandlerInput input, final IntentRequest intent)
    {
        return intent.getIntent().getName().equals(Intents.COUNTRY_SELECTED.name);
    }

    @Override
    public Optional<Response> handle(final HandlerInput input, final IntentRequest intent) throws NoCountrySlotException, NoJsonFileException
    {
        final Slot countrySlot = intent.getIntent().getSlots().get(Slots.COUNTRY.getName());
        if (countrySlot == null)
        {
            throw new NoCountrySlotException("No country slot found.");
        }
        final Country country = CountryFactory.getCountry(countrySlot.getValue());

        final Map<String, Object> attribs = input.getAttributesManager().getSessionAttributes();
        attribs.put(Attributes.COUNTRY.name, country.getAbbreviation());
        input.getAttributesManager().setSessionAttributes(attribs);

        final ResourceBundle bundle = getBundleFromAttribures(input.getAttributesManager().getRequestAttributes());
        return input.getResponseBuilder()
            .withSpeech(String.format(getFromBundle(bundle, BundleKeys.CITIES_IN_COUNTRY_FMT), country.getName(), country.getPrettyCityNames()))
            .withReprompt(getFromBundle(bundle, BundleKeys.DEFAULT_ASK_FOR_CITY))
            .withShouldEndSession(false)
            .build();
    }

}
