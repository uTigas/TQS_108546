package geocoding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {

    @Mock
    ISimpleHttpClient client = new TqsBasicHttpClient();

    AddressResolverService resolver;

    @BeforeEach
    void setUp(){
        resolver = new AddressResolverService(client);
    }

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException {
        
        when(client.doHttpGet(anyString())).thenReturn("{" +
        "\"info\": {" +
        "    \"statuscode\": 0," +
        "    \"copyright\": {" +
        "      \"text\": \"© 2024 MapQuest, Inc.\"," +
        "      \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\"," +
        "      \"imageAltText\": \"© 2024 MapQuest, Inc.\"" +
        "    }," +
        "    \"messages\": []" +
        "  }," +
        "  \"options\": {" +
        "    \"maxResults\": 1," +
        "    \"ignoreLatLngInput\": false" +
        "  }," +
        "  \"results\": [" +
        "    {" +
        "      \"providedLocation\": {" +
        "        \"latLng\": {" +
        "          \"lat\": 40.63436," +
        "          \"lng\": -8.65616" +
        "        }" +
        "      }," +
        "      \"locations\": [" +
        "        {" +
        "          \"street\": \"Avenida da Universidade\"," +
        "          \"adminArea6\": \"Aveiro\"," +
        "          \"adminArea6Type\": \"Neighborhood\"," +
        "          \"adminArea5\": \"Aveiro\"," +
        "          \"adminArea5Type\": \"City\"," +
        "          \"adminArea4\": \"Aveiro\"," +
        "          \"adminArea4Type\": \"County\"," +
        "          \"adminArea3\": \"\"," +
        "          \"adminArea3Type\": \"State\"," +
        "          \"adminArea1\": \"PT\"," +
        "          \"adminArea1Type\": \"Country\"," +
        "          \"postalCode\": \"3810-489\"," +
        "          \"geocodeQualityCode\": \"B1AAA\"," +
        "          \"geocodeQuality\": \"STREET\"," +
        "          \"dragPoint\": false," +
        "          \"sideOfStreet\": \"L\"," +
        "          \"linkId\": \"0\"," +
        "          \"unknownInput\": \"\"," +
        "          \"type\": \"s\"," +
        "          \"latLng\": {" +
        "            \"lat\": 40.63437," +
        "            \"lng\": -8.65625" +
        "          }," +
        "          \"displayLatLng\": {" +
        "            \"lat\": 40.63437," +
        "            \"lng\": -8.65625" +
        "          }," +
        "          \"mapUrl\": \"\"" +
        "        }" +
        "      ]" +
        "    }" +
        "  ]" +
        "}");
        Optional<Address> result = resolver.findAddressForLocation(40.63436, -8.65616);

        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");
        verify(client,times(1)).doHttpGet(anyString());
        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        when(client.doHttpGet(anyString())).thenReturn("{\n" + //
                        "  \"info\": {\n" + //
                        "    \"statuscode\": 400,\n" + //
                        "    \"copyright\": {\n" + //
                        "      \"text\": \"© 2024 MapQuest, Inc.\",\n" + //
                        "      \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\",\n" + //
                        "      \"imageAltText\": \"© 2024 MapQuest, Inc.\"\n" + //
                        "    },\n" + //
                        "    \"messages\": [\n" + //
                        "      \"Illegal argument from request: Invalid LatLng specified.\"\n" + //
                        "    ]\n" + //
                        "  },\n" + //
                        "  \"options\": {\n" + //
                        "    \"maxResults\": 1,\n" + //
                        "    \"ignoreLatLngInput\": false\n" + //
                        "  },\n" + //
                        "  \"results\": [\n" + //
                        "    {\n" + //
                        "      \"providedLocation\": {},\n" + //
                        "      \"locations\": []\n" + //
                        "    }\n" + //
                        "  ]\n" + //
                        "}");


        Optional<Address> result = resolver.findAddressForLocation(-361,-361);
        // verify no valid result
        assertFalse( result.isPresent());
        verify(client,times(1)).doHttpGet(anyString());

    }

    @Test
    public void whenNoConection_thenThrowsException() throws IOException, URISyntaxException, ParseException {
        when(client.doHttpGet(anyString())).thenThrow(IOException.class);
        assertThrows(IOException.class,() -> resolver.findAddressForLocation(0, 0));
    }
}
