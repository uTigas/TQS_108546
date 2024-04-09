package tqs.hw1.fs_webApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.repository.ConnectionRepository;

@SpringBootTest
public class ConnectionServiceTest {

    @Mock
    private ConnectionRepository connectionRepository;

    @InjectMocks
    private ConnectionService connectionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindDistinctOriginCities() {
        List<String> expectedCities = new ArrayList<>();
        expectedCities.add("City1");
        expectedCities.add("City2");
        when(connectionRepository.findDistinctOriginCities()).thenReturn(expectedCities);

        List<String> result = connectionService.findDistinctOriginCities();

        assertEquals(expectedCities, result);
    }

    @Test
    public void testFindDistinctDestinationCities() {
        List<String> expectedCities = new ArrayList<>();
        expectedCities.add("City3");
        expectedCities.add("City4");
        when(connectionRepository.findDistinctDestinationCities()).thenReturn(expectedCities);

        List<String> result = connectionService.findDistinctDestinationCities();

        assertEquals(expectedCities, result);
    }

    @Test
    public void testFindConnectionsFromTo() {
        String origin = "Origin";
        String destination = "Destination";
        List<Connection> expectedConnections = new ArrayList<>();
        expectedConnections.add(new Connection());
        when(connectionRepository.findConnectionsFromTo(origin, destination)).thenReturn(expectedConnections);

        List<Connection> result = connectionService.findConnectionsFromTo(origin, destination);

        assertEquals(expectedConnections, result);
    }

    @Test
    public void testFindConnectionsFromToDateFrom() {
        String origin = "Origin";
        String destination = "Destination";
        String dateFrom = "01/01/2022";
        LocalDate localDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        long expectedTimestamp = localDate.atStartOfDay().toInstant(java.time.ZoneOffset.UTC).getEpochSecond();
        List<Connection> expectedConnections = new ArrayList<>();
        expectedConnections.add(new Connection());
        when(connectionRepository.findConnectionsFromToDateFrom(origin, destination, expectedTimestamp))
                .thenReturn(expectedConnections);

        List<Connection> result = connectionService.findConnectionsFromToDateFrom(origin, destination, dateFrom);

        assertEquals(expectedConnections, result);
    }

}
