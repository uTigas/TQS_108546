package tqs.hw1.fs_webApp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import tqs.hw1.fs_webApp.data.entity.Terminal;

@SpringBootTest
public class TicketManagerServiceTest {

    @Mock
    private TerminalService terminalService;

    @InjectMocks
    private TicketManagerService ticketManagerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllTerminals_ReturnsListOfTerminals_WhenTerminalsExist() {

        List<Terminal> expectedTerminals = new ArrayList<>();
        expectedTerminals.add(new Terminal());
        when(terminalService.getAllTerminals()).thenReturn(expectedTerminals);

        List<Terminal> result = ticketManagerService.getAllTerminals();

        assertEquals(expectedTerminals, result);
    }

    @Test
    public void createTicket_WithValidConnections_ReturnsValidReservation(){

    }

}
