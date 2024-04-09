package tqs.hw1.fs_webApp.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Seat;
import tqs.hw1.fs_webApp.data.repository.SeatRepository;

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
    void findAvailableSeats_ReturnsPageOfAvailableSeats_WhenConnectionExists() {
        Connection connection = new Connection();
        Page<Seat> expectedPage = mock(Page.class);
        when(seatRepositoryMock.findAvailableSeat(any(PageRequest.class), eq(connection.getId()))).thenReturn(expectedPage);

        Page<Seat> result = seatService.findAvailable(connection);

        assertEquals(expectedPage, result);
    }

}
