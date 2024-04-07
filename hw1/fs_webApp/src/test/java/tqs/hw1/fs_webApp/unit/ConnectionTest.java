package tqs.hw1.fs_webApp.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tqs.hw1.fs_webApp.data.entity.Connection;

class ConnectionTest {

	@Test
	void testDistanceBetweenCities(){
		assertEquals(Connection.haversine(new double[] { 50, 0},new double[] { -10, 15}),6828.94, 3); //3kms acceptable margin
	}

}
