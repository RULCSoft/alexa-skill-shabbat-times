package info.tomfi.alexa.skills.shabbattimes.tools;

import static org.assertj.core.api.Assertions.assertThat;

import static info.tomfi.alexa.skills.shabbattimes.enums.Attributes.L10N_BUNDLE;
import static info.tomfi.alexa.skills.shabbattimes.enums.BundleKeys.DEFAULT_OK;
import static info.tomfi.alexa.skills.shabbattimes.tools.LocalizationUtils.getBundleFromAttribures;
import static info.tomfi.alexa.skills.shabbattimes.tools.LocalizationUtils.getFromBundle;

import java.util.HashMap;
import java.util.ResourceBundle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.val;

public final class LocalizationUtilsTest
{
    private static ResourceBundle bundle;

    @BeforeAll
    public static void initialize()
    {
        bundle = ResourceBundle.getBundle("locales/TestResponses");
    }

    @Test
    @DisplayName("test the retrieval of the bundle object from a the attributes map")
    public void getBundleFromAttribures_fakeAttributes_getBundle()
    {
        val attributes = new HashMap<String, Object>();
        attributes.put(L10N_BUNDLE.getName(), bundle);
        assertThat(getBundleFromAttribures(attributes).hashCode()).isEqualTo(bundle.hashCode());
    }

    @Test
    @DisplayName("test the retrieval of localized strings from bundle resource properties file")
    public void getFromBundle_testPropertiesFile_getCorrectValue()
    {
        assertThat(getFromBundle(bundle, DEFAULT_OK)).isEqualTo("testValue");
    }
}
