package tqs.hw1.fs_webApp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Seat;
import tqs.hw1.fs_webApp.data.repository.SeatRepository;

@ExtendWith(MockitoExtension.class)
class SeatServiceTest {

    @Mock
    private SeatRepository seatRepositoryMock;

    private SeatService seatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seatService = new SeatService();
        seatService.repo = seatRepositoryMock;

    }

    @Test
    void findAvailableSeats_ReturnsAvailableSeats_WhenConnectionExists() {
        Connection connection = new Connection();
        List<Seat> expectedSeats = new ArrayList<>();
        when(seatRepositoryMock.findAvailableSeat(eq(connection))).thenReturn(expectedSeats);

        List<Seat> result = seatService.findAvailable(connection);

        assertEquals(expectedSeats, result);
    }

}
